/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libcore.java.net;

import static android.system.OsConstants.AF_INET;
import static android.system.OsConstants.AF_INET6;
import static android.system.OsConstants.SOCK_DGRAM;

import static java.util.stream.Collectors.joining;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import android.system.ErrnoException;
import android.system.Os;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketImpl;
import java.net.SocketOption;
import java.net.SocketOptions;
import java.net.StandardSocketOptions;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import libcore.junit.junit3.TestCaseWithRules;
import libcore.junit.util.ResourceLeakageDetector;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.rules.TestRule;

@RunWith(JUnit4.class)
public class SocketTest {
    @Rule
    public TestRule resourceLeakageDetectorRule = ResourceLeakageDetector.getRule();

    // This hostname is required to resolve to 127.0.0.1 and ::1 for all tests to pass.
    private static final String ALL_LOOPBACK_HOSTNAME = "loopback46.unittest.grpc.io";

    private static final InetAddress[] ALL_LOOPBACK_ADDRESSES = {
        Inet4Address.LOOPBACK,
        Inet6Address.LOOPBACK
    };

    {
        sortAddresses(ALL_LOOPBACK_ADDRESSES);
    }

    // From net/inet_ecn.h
    private static final int INET_ECN_MASK = 0x3;

    // See http://b/2980559.
    @Test
    public void test_close() throws Exception {
        Socket s = new Socket();
        s.close();
        // Closing a closed socket does nothing.
        s.close();
    }

    /**
     * Our getLocalAddress and getLocalPort currently use getsockname(3).
     * This means they give incorrect results on closed sockets (as well
     * as requiring an unnecessary call into native code).
     */
    @Test
    public void test_getLocalAddress_after_close() throws Exception {
        Socket s = new Socket();
        try {
            // Bind to an ephemeral local port.
            s.bind(new InetSocketAddress("localhost", 0));
            assertTrue(s.getLocalAddress().toString(), s.getLocalAddress().isLoopbackAddress());
            // What local port did we get?
            int localPort = s.getLocalPort();
            assertTrue(localPort > 0);
            // Now close the socket...
            s.close();
            // The RI returns the ANY address but the original local port after close.
            assertTrue(s.getLocalAddress().isAnyLocalAddress());
            assertEquals(localPort, s.getLocalPort());
        } finally {
            s.close();
        }
    }

    // http://code.google.com/p/android/issues/detail?id=7935
    @Test
    public void test_newSocket_connection_refused() throws Exception {
        try {
            new Socket("localhost", 80);
            fail("connection should have been refused");
        } catch (ConnectException expected) {
        }
    }

    // http://code.google.com/p/android/issues/detail?id=3123
    // http://code.google.com/p/android/issues/detail?id=1933
    @Test
    public void test_socketLocalAndRemoteAddresses() throws Exception {
        checkSocketLocalAndRemoteAddresses(false);
        checkSocketLocalAndRemoteAddresses(true);
    }

    public void checkSocketLocalAndRemoteAddresses(boolean setOptions) throws Exception {
        InetAddress host = InetAddress.getLocalHost();

        // Open a local server port.
        ServerSocketChannel ssc = ServerSocketChannel.open();
        InetSocketAddress listenAddr = new InetSocketAddress(host, 0);
        ssc.socket().bind(listenAddr, 0);
        ServerSocket ss = ssc.socket();

        // Open a socket to the local port.
        SocketChannel out = SocketChannel.open();
        out.configureBlocking(false);
        if (setOptions) {
            out.socket().setTcpNoDelay(false);
        }
        InetSocketAddress addr = new InetSocketAddress(host, ssc.socket().getLocalPort());
        out.connect(addr);
        while (!out.finishConnect()) {
            Thread.sleep(1);
        }

        SocketChannel in = ssc.accept();
        if (setOptions) {
            in.socket().setTcpNoDelay(false);
        }

        InetSocketAddress listenAddress = (InetSocketAddress) in.socket().getLocalSocketAddress();
        InetSocketAddress outRemoteAddress = (InetSocketAddress) out.socket().getRemoteSocketAddress();
        InetSocketAddress outLocalAddress = (InetSocketAddress) out.socket().getLocalSocketAddress();
        InetSocketAddress inLocalAddress = (InetSocketAddress) in.socket().getLocalSocketAddress();
        InetSocketAddress inRemoteAddress = (InetSocketAddress) in.socket().getRemoteSocketAddress();
        System.err.println("listenAddress: " + listenAddr);
        System.err.println("inLocalAddress: " + inLocalAddress);
        System.err.println("inRemoteAddress: " + inRemoteAddress);
        System.err.println("outLocalAddress: " + outLocalAddress);
        System.err.println("outRemoteAddress: " + outRemoteAddress);

        assertEquals(outRemoteAddress.getPort(), ss.getLocalPort());
        assertEquals(inLocalAddress.getPort(), ss.getLocalPort());
        assertEquals(inRemoteAddress.getPort(), outLocalAddress.getPort());

        assertEquals(inLocalAddress.getAddress(), ss.getInetAddress());
        assertEquals(inRemoteAddress.getAddress(), ss.getInetAddress());
        assertEquals(outLocalAddress.getAddress(), ss.getInetAddress());
        assertEquals(outRemoteAddress.getAddress(), ss.getInetAddress());

        assertFalse(ssc.socket().isClosed());
        assertTrue(ssc.socket().isBound());
        assertTrue(in.isConnected());
        assertTrue(in.socket().isConnected());
        assertTrue(out.socket().isConnected());
        assertTrue(out.isConnected());

        in.close();
        out.close();
        ssc.close();

        assertTrue(ssc.socket().isClosed());
        assertTrue(ssc.socket().isBound());
        assertFalse(in.isConnected());
        assertFalse(in.socket().isConnected());
        assertFalse(out.socket().isConnected());
        assertFalse(out.isConnected());

        assertNull(in.socket().getRemoteSocketAddress());
        assertNull(out.socket().getRemoteSocketAddress());

        // As per docs and RI - server socket local address methods continue to return the bind()
        // addresses even after close().
        assertEquals(listenAddress, ssc.socket().getLocalSocketAddress());

        // As per docs and RI - socket local address methods return the wildcard address before
        // bind() and after close(), but the port will be the same as it was before close().
        InetSocketAddress inLocalAddressAfterClose =
                (InetSocketAddress) in.socket().getLocalSocketAddress();
        assertTrue(inLocalAddressAfterClose.getAddress().isAnyLocalAddress());
        assertEquals(inLocalAddress.getPort(), inLocalAddressAfterClose.getPort());

        InetSocketAddress outLocalAddressAfterClose =
                (InetSocketAddress) out.socket().getLocalSocketAddress();
        assertTrue(outLocalAddressAfterClose.getAddress().isAnyLocalAddress());
        assertEquals(outLocalAddress.getPort(), outLocalAddressAfterClose.getPort());
    }

    private static class MySocketImpl extends SocketImpl {
        public int option;
        public Object value;

        public boolean createCalled;
        public boolean createStream;

        public MySocketImpl() { super(); }
        @Override protected void accept(SocketImpl arg0) throws IOException { }
        @Override protected int available() throws IOException { return 0; }
        @Override protected void bind(InetAddress arg0, int arg1) throws IOException { }
        @Override protected void close() throws IOException { }
        @Override protected void connect(String arg0, int arg1) throws IOException { }
        @Override protected void connect(InetAddress arg0, int arg1) throws IOException { }
        @Override protected void connect(SocketAddress arg0, int arg1) throws IOException { }
        @Override protected InputStream getInputStream() throws IOException { return null; }
        @Override protected OutputStream getOutputStream() throws IOException { return null; }
        @Override protected void listen(int arg0) throws IOException { }
        @Override protected void sendUrgentData(int arg0) throws IOException { }
        public Object getOption(int arg0) throws SocketException { return null; }

        @Override protected void create(boolean isStream) throws IOException {
            this.createCalled = true;
            this.createStream = isStream;
        }

        public void setOption(int option, Object value) throws SocketException {
            this.option = option;
            this.value = value;
        }

        public <T> void setSuperOption(SocketOption<T> option, T value) throws IOException {
            super.setOption(option, value);
        }
    }

    private static class MySocket extends Socket {
        public MySocket(SocketImpl impl) throws SocketException {
            super(impl);
        }
    }

    // SocketOptions.setOption has weird behavior for setSoLinger/SO_LINGER.
    // This test ensures we do what the RI does.
    @Test
    public void test_SocketOptions_setOption() throws Exception {
        MySocketImpl impl = new MySocketImpl();
        Socket s = new MySocket(impl);

        // Check that, as per the SocketOptions.setOption documentation, we pass false rather
        // than -1 to the SocketImpl when setSoLinger is called with the first argument false.
        s.setSoLinger(false, -1);
        assertEquals(Boolean.FALSE, (Boolean) impl.value);
        // We also check that SocketImpl.create was called. SocketChannelImpl.SocketAdapter
        // subclasses Socket, and whether or not to call SocketImpl.create is the main behavioral
        // difference.
        assertEquals(true, impl.createCalled);
        s.setSoLinger(false, 0);
        assertEquals(Boolean.FALSE, (Boolean) impl.value);
        s.setSoLinger(false, 1);
        assertEquals(Boolean.FALSE, (Boolean) impl.value);

        // Check that otherwise, we pass down an Integer.
        s.setSoLinger(true, 0);
        assertEquals(Integer.valueOf(0), (Integer) impl.value);
        s.setSoLinger(true, 1);
        assertEquals(Integer.valueOf(1), (Integer) impl.value);

        // API test for SocketImpl.setOption(SocketOption, Object).
        // The value isn't sent to the kernel, because the mock intercepts the value in this test.
        setAndAssertOption(impl, StandardSocketOptions.SO_KEEPALIVE,
                SocketOptions.SO_KEEPALIVE, true);
        setAndAssertOption(impl, StandardSocketOptions.SO_SNDBUF,
                SocketOptions.SO_SNDBUF, 1);
        setAndAssertOption(impl, StandardSocketOptions.SO_RCVBUF,
                SocketOptions.SO_RCVBUF, 2);
        setAndAssertOption(impl, StandardSocketOptions.SO_REUSEADDR,
                SocketOptions.SO_REUSEADDR, true);
        setAndAssertOption(impl, StandardSocketOptions.SO_LINGER,
                SocketOptions.SO_LINGER, 3);
        setAndAssertOption(impl, StandardSocketOptions.IP_TOS,
                SocketOptions.IP_TOS, 4);
        setAndAssertOption(impl, StandardSocketOptions.TCP_NODELAY,
                SocketOptions.TCP_NODELAY, true);
    }

    private static void setAndAssertOption(MySocketImpl sockImpl, SocketOption option,
            int optionInt, Object value) throws IOException {
        sockImpl.setSuperOption(option, value);
        assertEquals(sockImpl.option, optionInt);
        assertEquals(sockImpl.value, value);
    }

    @Test
    public void test_setTrafficClass() throws Exception {
        try (Socket s = new Socket()) {
            for (int i = 0; i <= 255; ++i) {
                s.setTrafficClass(i);

                // b/30909505
                // Linux does not set ECN bits for IP_TOS, but sets for IPV6_TCLASS. We should
                // accept either output.
                int actual = s.getTrafficClass();
                assertTrue(i == actual || // IPV6_TCLASS
                        (actual == (i & ~INET_ECN_MASK))); // IP_TOS: ECN bits should be 0
            }
        }
    }

    @Test
    public void testReadAfterClose() throws Exception {
        MockServer server = new MockServer();
        server.enqueue(new byte[]{5, 3}, 0);
        Socket socket = new Socket("localhost", server.port);
        InputStream in = socket.getInputStream();
        assertEquals(5, in.read());
        assertEquals(3, in.read());
        assertEquals(-1, in.read());
        assertEquals(-1, in.read());
        socket.close();
        in.close();

        /*
         * Rather astonishingly, read() doesn't throw even though the stream is
         * closed. This is consistent with the RI's behavior.
         */
        assertEquals(-1, in.read());
        assertEquals(-1, in.read());

        server.shutdown();
    }

    @Test
    public void testWriteAfterClose() throws Exception {
        MockServer server = new MockServer();
        server.enqueue(new byte[0], 3);
        Socket socket = new Socket("localhost", server.port);
        OutputStream out = socket.getOutputStream();
        out.write(5);
        out.write(3);
        socket.close();
        out.close();

        try {
            out.write(9);
            fail();
        } catch (IOException expected) {
        }

        server.shutdown();
    }

    // http://b/5534202
    @Test
    public void testAvailable() throws Exception {
        for (int i = 0; i < 100; i++) {
            assertAvailableReturnsZeroAfterSocketReadsAllData();
            System.out.println("Success on rep " + i);
        }
    }

    private void assertAvailableReturnsZeroAfterSocketReadsAllData() throws Exception {
        final byte[] data = "foo".getBytes();
        final ServerSocket serverSocket = new ServerSocket(0);

        new Thread() {
            @Override public void run() {
                try {
                    Socket socket = serverSocket.accept();
                    socket.getOutputStream().write(data);
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        Socket socket = new Socket("localhost", serverSocket.getLocalPort());
        byte[] readBuffer = new byte[128];
        InputStream in = socket.getInputStream();
        int total = 0;
        // to prevent available() from cheating after EOF, stop reading before -1 is returned
        while (total < data.length) {
            total += in.read(readBuffer);
        }
        assertEquals(0, in.available());

        socket.close();
        serverSocket.close();
    }

    @Test
    public void testInitialState() throws Exception {
        Socket s = new Socket();
        try {
            assertFalse(s.isBound());
            assertFalse(s.isClosed());
            assertFalse(s.isConnected());
            assertEquals(-1, s.getLocalPort());
            assertTrue(s.getLocalAddress().isAnyLocalAddress());
            assertNull(s.getLocalSocketAddress());
            assertNull(s.getInetAddress());
            assertEquals(0, s.getPort());
            assertNull(s.getRemoteSocketAddress());
            assertFalse(s.getReuseAddress());
            assertNull(s.getChannel());
        } finally {
            s.close();
        }
    }

    @Test
    public void testStateAfterClose() throws Exception {
        Socket s = new Socket();
        s.bind(new InetSocketAddress(Inet4Address.getLocalHost(), 0));
        InetSocketAddress boundAddress = (InetSocketAddress) s.getLocalSocketAddress();
        s.close();

        assertTrue(s.isBound());
        assertTrue(s.isClosed());
        assertFalse(s.isConnected());
        assertTrue(s.getLocalAddress().isAnyLocalAddress());
        assertEquals(boundAddress.getPort(), s.getLocalPort());

        InetSocketAddress localAddressAfterClose = (InetSocketAddress) s.getLocalSocketAddress();
        assertTrue(localAddressAfterClose.getAddress().isAnyLocalAddress());
        assertEquals(boundAddress.getPort(), localAddressAfterClose.getPort());
    }

    @Test
    public void testCloseDuringConnect() throws Exception {
        // This address is reserved for documentation: should never be reachable and therefore
        // is expected to produce block behavior when attempting to connect().
        final InetSocketAddress unreachableIp = new InetSocketAddress("192.0.2.0", 80);

        final Socket s = new Socket();

        // A Callable that executes a connect() that should block and ultimately throw an exception.
        // Inverts usual behavior for code: expected to *return* a throwable for analysis if one is
        // thrown, but throws an Error if no exception is thrown.
        Callable<Throwable> connectWorker = () -> {
            try {
                // This method should not return naturally.
                s.connect(unreachableIp, 0 /* infinite */);
                throw new AssertionError(
                        "connect() to address(" + unreachableIp + ") did not block as required");
            } catch (Exception exception) {
                // Return the exception so that it can be inspected.
                return exception;
            }
        };
        Future<Throwable> connectResult =
                Executors.newSingleThreadScheduledExecutor().submit(connectWorker);

        // Wait sufficient time for the connectWorker thread to run and start connect().
        Thread.sleep(2000);

        // Check for unexpected early termination. We require an environment where connect() will
        // block forever with the specified IP and we can fail early if we detect the block hasn't
        // happened.
        if (connectResult.isDone()) {
            // We expect an ExecutionError here. If not something has gone wrong with the test
            // logic.
            Throwable error = connectResult.get();
            throw new AssertionError("Unexpected result from connectWorker", error);
        }

        // Close the socket that connectWorker should currently be blocked in connect().
        s.close();

        // connectWorker should unblock so get() should obtain the exception that we expect to be
        // thrown.
        Throwable result = connectResult.get(2000, TimeUnit.MILLISECONDS);
        if (result instanceof SocketException) {
            if (result.getMessage().contains("Socket closed")) {
                // This is the only case we accept.
                return;
            }
            throw new AssertionError(
                    "Unexpected SocketException message: " + result.getMessage(), result);
        } else {
            throw new AssertionError("Unexpected exception encountered", result);
        }
    }

    // http://b/29092095
    @Test
    public void testSocketWithProxySet() throws Exception {
        ProxySelector ps = ProxySelector.getDefault();
        try {
            ProxySelector.setDefault(new ProxySelector() {
                @Override
                public List<Proxy> select(URI uri) {
                    fail("ProxySelector#select was called");
                    return null; // unreachable.
                }

                @Override
                public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
                    fail("ProxySelector#connectFail was called");
                }
            });

            ServerSocket server = new ServerSocket(0);
            Socket client = new Socket(InetAddress.getLocalHost(), server.getLocalPort());
            client.close();
            server.close();
        } finally {
            ProxySelector.setDefault(ps);
        }
    }


    // b/25805791 + b/26470377
    @Test
    public void testFileDescriptorStaysSame() throws Exception {
        // SocketImplementation FileDescriptor object shouldn't change after calling
        // bind (and many other methods).
        Socket s = new Socket();

        // There's an assumption that newly created
        // socket has a non-null file-descriptor (b/26169052, b/26084000)
        FileDescriptor fd1 = s.getFileDescriptor$();
        assertNotNull(fd1);
        int fd1Val = fd1.getInt$();
        assertEquals(-1, fd1Val);

        s.bind(new InetSocketAddress(InetAddress.getByName("0.0.0.0"), 0));
        FileDescriptor fd2 = s.getFileDescriptor$();
        assertSame(fd1, fd2);
        int fd2Val = fd2.getInt$();
        assertTrue(fd1Val != fd2Val); // The actual fd should be different

        s.close();
        FileDescriptor fd3 = s.getFileDescriptor$();
        assertSame(fd1, fd3);
        assertFalse(fd3.valid());
    }

    static class MockServer {
        private ExecutorService executor;
        private ServerSocket serverSocket;
        private int port = -1;

        MockServer() throws IOException {
            executor = Executors.newCachedThreadPool();
            serverSocket = new ServerSocket(0);
            serverSocket.setReuseAddress(true);
            port = serverSocket.getLocalPort();
        }

        public Future<byte[]> enqueue(final byte[] sendBytes, final int receiveByteCount)
                throws IOException {
            return executor.submit(new Callable<byte[]>() {
                @Override public byte[] call() throws Exception {
                    Socket socket = serverSocket.accept();
                    OutputStream out = socket.getOutputStream();
                    out.write(sendBytes);

                    InputStream in = socket.getInputStream();
                    byte[] result = new byte[receiveByteCount];
                    int total = 0;
                    while (total < receiveByteCount) {
                        total += in.read(result, total, result.length - total);
                    }
                    socket.close();
                    return result;
                }
            });
        }

        public void shutdown() throws IOException {
            serverSocket.close();
            executor.shutdown();
        }
    }

    // b/26354315
    @Test
    public void testDoNotCallCloseFromSocketCtor() {
        // Original openJdk7 Socket implementation may call Socket#close() inside a constructor.
        // In this case, classes that extend Socket wont be fully constructed when they
        // receive #close() call. This test makes sure this won't happen

        // Extend Socket
        class SocketThatFailOnClose extends Socket {
            public SocketThatFailOnClose(String host, int port)
                throws UnknownHostException, IOException {
                super(host, port);
            }
            public SocketThatFailOnClose(InetAddress address, int port) throws IOException {
                super(address, port);
            }
            public SocketThatFailOnClose(String host, int port, InetAddress localAddr,
                                         int localPort) throws IOException {
                super(host, port, localAddr, localPort);
            }
            public SocketThatFailOnClose(InetAddress address, int port, InetAddress localAddr,
                                         int localPort) throws IOException {
                super(address, port, localAddr, localPort);
            }
            public SocketThatFailOnClose(String host, int port, boolean stream) throws IOException {
                super(host, port, stream);
            }
            public SocketThatFailOnClose(InetAddress host, int port, boolean stream)
                throws IOException {
                super(host, port, stream);
            }

            @Override
            public void close() {
                fail("Do not call close from the Socket constructor");
            }
        }

        // Test all Socket ctors
        try {
            new SocketThatFailOnClose("localhost", 1);
            fail();
        } catch(IOException expected) {}
        try {
            new SocketThatFailOnClose(InetAddress.getLocalHost(), 1);
            fail();
        } catch(IOException expected) {}
        try {
            new SocketThatFailOnClose("localhost", 1, null, 0);
            fail();
        } catch(IOException expected) {}
        try {
            new SocketThatFailOnClose(InetAddress.getLocalHost(), 1, null, 0);
            fail();
        } catch(IOException expected) {}
        try {
            new SocketThatFailOnClose("localhost", 1, true);
            fail();
        } catch(IOException expected) {}
        try {
            new SocketThatFailOnClose(InetAddress.getLocalHost(), 1, true);
            fail();
        } catch(IOException expected) {}
    }

    // b/30007735
    @Ignore("b/292238663")
    @Test
    public void testSocketTestAllAddresses() throws Exception {
        checkLoopbackHost();

        // Socket Ctor should try all sockets.
        //
        // This test creates server sockets bound to 127.0.0.1 and ::1, and connects using a
        // hostname that resolves to both addresses. We should be able to connect to the server
        // socket in either setup.
        final int port = 9999;
        for (InetAddress addr : ALL_LOOPBACK_ADDRESSES) {
            try (ServerSocket ss = new ServerSocket(port, 0, addr)) {
                new Thread(() -> {
                    try {
                        ss.accept();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

                assertTrue(canConnect(ALL_LOOPBACK_HOSTNAME, port));
            }
        }
    }

    private static int compareInetAddress(InetAddress lhs, InetAddress rhs) {
        return Arrays.compare(lhs.getAddress(), rhs.getAddress());
    }

    private static void sortAddresses(InetAddress[] addresses) {
        Arrays.sort(addresses, (InetAddress lhs, InetAddress rhs) -> compareInetAddress(lhs, rhs));
    }

    private static boolean allUniqueLoopbackAddresses(InetAddress[] addresses) {
        for (InetAddress a : addresses) {
            if (!a.isLoopbackAddress()) {
                return false;
            }
        }
        return addresses.length <= 1 || (addresses.length == 2 && addresses[0] != addresses[1]);
    }

    /** Confirm the supplied hostname maps to only loopback addresses, both IPv4 and IPv6. */
    private static void checkLoopbackHost() throws UnknownHostException {
        // b/202426043 retry a few times since DNS maybe prone to being dropped or slow in
        // responding and we have no control over the query or cache timeouts here.
        final int WAIT_MILLIS = 2000;
        for (int triesLeft = 2; triesLeft >= 0; --triesLeft) {
            InetAddress[] addresses = InetAddress.getAllByName(ALL_LOOPBACK_HOSTNAME);
            sortAddresses(addresses);
            if (Arrays.equals(ALL_LOOPBACK_ADDRESSES, addresses)) {
                return;
            }

            if (triesLeft == 0 || addresses.length > 2 || !allUniqueLoopbackAddresses(addresses)) {
                fail("Expected " + Arrays.toString(ALL_LOOPBACK_ADDRESSES) +
                     ", got " + Arrays.toString(addresses));
            }

            try {
                Thread.sleep(WAIT_MILLIS);
            } catch (InterruptedException e) {
                fail("Test interrupted");
            }
        }
    }

    private static boolean canConnect(String host, int port) {
        try(Socket sock = new Socket(host, port)) {
            return sock.isConnected();
        } catch (IOException e) {
            return false;
        }
    }
}
