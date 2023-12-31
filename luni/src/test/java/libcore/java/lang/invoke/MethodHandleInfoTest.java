/*
 * Copyright (C) 2016 The Android Open Source Project
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

package libcore.java.lang.invoke;

import junit.framework.TestCase;

import java.lang.invoke.MethodHandleInfo;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandleInfo.*;

public class MethodHandleInfoTest extends TestCase {
    public void test_toString() {
        final MethodType type = MethodType.methodType(String.class, String.class);
        String string = MethodHandleInfo.toString(REF_invokeVirtual, String.class, "concat",  type);
        assertEquals("invokeVirtual java.lang.String.concat:(String)String", string);

        try {
            MethodHandleInfo.toString(-1, String.class, "concat", type);
            fail();
        } catch (IllegalArgumentException expected) {
        }

        try {
            MethodHandleInfo.toString(REF_invokeVirtual, String.class, null, type);
            fail();
        } catch (NullPointerException expected) {
        }

        try {
            MethodHandleInfo.toString(REF_invokeVirtual, null, "concat", type);
            fail();
        } catch (NullPointerException expected) {
        }

        try {
            MethodHandleInfo.toString(REF_invokeVirtual, String.class, "concat", null);
            fail();
        } catch (NullPointerException expected) {
        }
    }

    public void test_constants() {
        final int [] REF_CONSTANTS = {
            0,                                     // Not used
            MethodHandleInfo.REF_getField,         // 1
            MethodHandleInfo.REF_getStatic,        // 2
            MethodHandleInfo.REF_putField,         // 3
            MethodHandleInfo.REF_putStatic,        // 4
            MethodHandleInfo.REF_invokeVirtual,    // 5
            MethodHandleInfo.REF_invokeStatic,     // 6
            MethodHandleInfo.REF_invokeSpecial,    // 7
            MethodHandleInfo.REF_newInvokeSpecial, // 8
            MethodHandleInfo.REF_invokeInterface,  // 9
        };
        assertEquals(10, REF_CONSTANTS.length);
        for (int i = 0; i < REF_CONSTANTS.length; ++i) {
            assertEquals(i, REF_CONSTANTS[i]);
        }
    }

    public void test_referenceKindToString() {
        assertEquals("getField", referenceKindToString(REF_getField));
        assertEquals("getStatic", referenceKindToString(REF_getStatic));
        assertEquals("putField", referenceKindToString(REF_putField));
        assertEquals("putStatic", referenceKindToString(REF_putStatic));
        assertEquals("invokeVirtual", referenceKindToString(REF_invokeVirtual));
        assertEquals("invokeStatic", referenceKindToString(REF_invokeStatic));
        assertEquals("invokeSpecial", referenceKindToString(REF_invokeSpecial));
        assertEquals("newInvokeSpecial", referenceKindToString(REF_newInvokeSpecial));
        assertEquals("invokeInterface", referenceKindToString(REF_invokeInterface));

        try {
            referenceKindToString(-1);
            fail();
        } catch (IllegalArgumentException expected) {
        }

        try {
            referenceKindToString(256);
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    public void test_refKindIsField() {
        assertTrue(MethodHandleInfo.refKindIsField(MethodHandleInfo.REF_getField));
        assertTrue(MethodHandleInfo.refKindIsField(MethodHandleInfo.REF_getStatic));
        assertTrue(MethodHandleInfo.refKindIsField(MethodHandleInfo.REF_putField));
        assertTrue(MethodHandleInfo.refKindIsField(MethodHandleInfo.REF_putStatic));

        assertFalse(MethodHandleInfo.refKindIsField(MethodHandleInfo.REF_invokeVirtual));
        assertFalse(MethodHandleInfo.refKindIsField(MethodHandleInfo.REF_invokeStatic));
        assertFalse(MethodHandleInfo.refKindIsField(MethodHandleInfo.REF_invokeSpecial));
        assertFalse(MethodHandleInfo.refKindIsField(MethodHandleInfo.REF_newInvokeSpecial));
        assertFalse(MethodHandleInfo.refKindIsField(MethodHandleInfo.REF_invokeInterface));
    }

    public void test_refKindIsValid() {
        assertTrue(MethodHandleInfo.refKindIsValid(MethodHandleInfo.REF_getField));
        assertTrue(MethodHandleInfo.refKindIsValid(MethodHandleInfo.REF_getStatic));
        assertTrue(MethodHandleInfo.refKindIsValid(MethodHandleInfo.REF_putField));
        assertTrue(MethodHandleInfo.refKindIsValid(MethodHandleInfo.REF_putStatic));
        assertTrue(MethodHandleInfo.refKindIsValid(MethodHandleInfo.REF_invokeVirtual));
        assertTrue(MethodHandleInfo.refKindIsValid(MethodHandleInfo.REF_invokeStatic));
        assertTrue(MethodHandleInfo.refKindIsValid(MethodHandleInfo.REF_invokeSpecial));
        assertTrue(MethodHandleInfo.refKindIsValid(MethodHandleInfo.REF_newInvokeSpecial));
        assertTrue(MethodHandleInfo.refKindIsValid(MethodHandleInfo.REF_invokeInterface));
    }

    public void test_refKindName() {
        assertEquals("getField", MethodHandleInfo.refKindName(MethodHandleInfo.REF_getField));
        assertEquals("getStatic", MethodHandleInfo.refKindName(MethodHandleInfo.REF_getStatic));
        assertEquals("putField", MethodHandleInfo.refKindName(MethodHandleInfo.REF_putField));
        assertEquals("putStatic", MethodHandleInfo.refKindName(MethodHandleInfo.REF_putStatic));
        assertEquals("invokeVirtual",
                     MethodHandleInfo.refKindName(MethodHandleInfo.REF_invokeVirtual));
        assertEquals("invokeStatic",
                     MethodHandleInfo.refKindName(MethodHandleInfo.REF_invokeStatic));
        assertEquals("invokeSpecial",
                     MethodHandleInfo.refKindName(MethodHandleInfo.REF_invokeSpecial));
        assertEquals("newInvokeSpecial",
                     MethodHandleInfo.refKindName(MethodHandleInfo.REF_newInvokeSpecial));
        assertEquals("invokeInterface",
                     MethodHandleInfo.refKindName(MethodHandleInfo.REF_invokeInterface));
    }
}
