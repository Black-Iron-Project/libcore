/* Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.tests.java.lang;

import junit.framework.TestCase;

public class Character_UnicodeBlockTest extends TestCase {

    static final class UnassignedRange {
        private int start;
        private int end;

        UnassignedRange(int start, int end) {
            if (start >= end || end < 0) {
                throw new IllegalArgumentException("Bad range: " + start + ":" + end);
            }
            this.start = start;
            this.end = end;
        }

        int start() {
            return start;
        }

        int end() {
            return end;
        }

        boolean isInCharRange() {
            return end <= Character.MAX_VALUE;
        }

        @Override
        public String toString() {
            return "0x" + Integer.toHexString(start) + ":0x" + Integer.toHexString(end);
        }
    }

    static final UnassignedRange [] UNASSIGNED_RANGES = {
        new UnassignedRange(0x0870, 0x08a0 - 1),
        new UnassignedRange(0x2fe0, 0x2ff0 - 1),
        new UnassignedRange(0x10200, 0x10280 - 1),
        new UnassignedRange(0x103e0, 0x10400 - 1),
        new UnassignedRange(0x10570, 0x10600 - 1),
        new UnassignedRange(0x10780, 0x10800 - 1),
        new UnassignedRange(0x108b0, 0x108e0 - 1),
        new UnassignedRange(0x10940, 0x10980 - 1),
        new UnassignedRange(0x10aa0, 0x10ac0 - 1),
        new UnassignedRange(0x10bb0, 0x10c00 - 1),
        new UnassignedRange(0x10c50, 0x10c80 - 1),
        new UnassignedRange(0x10d40, 0x10e60 - 1),
        new UnassignedRange(0x10ec0, 0x10f00 - 1),
        new UnassignedRange(0x10f70, 0x10fb0 - 1),
        new UnassignedRange(0x11250, 0x11280 - 1),
        new UnassignedRange(0x11380, 0x11400 - 1),
        new UnassignedRange(0x114e0, 0x11580 - 1),
        new UnassignedRange(0x116d0, 0x11700 - 1),
        new UnassignedRange(0x11740, 0x11800 - 1),
        new UnassignedRange(0x11850, 0x118a0 - 1),
        new UnassignedRange(0x11960, 0x119a0 - 1),
        new UnassignedRange(0x11ab0, 0x11ac0 - 1),
        new UnassignedRange(0x11b00, 0x11c00 - 1),
        new UnassignedRange(0x11cc0, 0x11d00 - 1),
        new UnassignedRange(0x11db0, 0x11ee0 - 1),
        new UnassignedRange(0x11f00, 0x11fb0 - 1),
        new UnassignedRange(0x12550, 0x13000 - 1),
        new UnassignedRange(0x13440, 0x14400 - 1),
        new UnassignedRange(0x14680, 0x16800 - 1),
        new UnassignedRange(0x16a70, 0x16ad0 - 1),
        new UnassignedRange(0x16b90, 0x16e40 - 1),
        new UnassignedRange(0x16ea0, 0x16f00 - 1),
        new UnassignedRange(0x16fa0, 0x16fe0 - 1),
        new UnassignedRange(0x18d90, 0x1b000 - 1),
        new UnassignedRange(0x1b300, 0x1bc00 - 1),
        new UnassignedRange(0x1bcb0, 0x1d000 - 1),
        new UnassignedRange(0x1d250, 0x1d2e0 - 1),
        new UnassignedRange(0x1d380, 0x1d400 - 1),
        new UnassignedRange(0x1dab0, 0x1e000 - 1),
        new UnassignedRange(0x1e030, 0x1e100 - 1),
        new UnassignedRange(0x1e150, 0x1e2c0 - 1),
        new UnassignedRange(0x1e300, 0x1e800 - 1),
        new UnassignedRange(0x1e8e0, 0x1e900 - 1),
        new UnassignedRange(0x1e960, 0x1ec70 - 1),
        new UnassignedRange(0x1ecc0, 0x1ed00 - 1),
        new UnassignedRange(0x1ed50, 0x1ee00 - 1),
        new UnassignedRange(0x1ef00, 0x1f000 - 1),
        new UnassignedRange(0x1fc00, 0x20000 - 1),
        new UnassignedRange(0x2a6e0, 0x2a700 - 1),
        new UnassignedRange(0x2ebf0, 0x2f800 - 1),
        new UnassignedRange(0x2fa20, 0x30000 - 1),
        new UnassignedRange(0x31350, 0xe0000 - 1),
        new UnassignedRange(0xe0080, 0xe0100 - 1),
        new UnassignedRange(0xe01f0, 0xf0000 - 1),
    };


    public void test_ofC() {
        assertEquals(Character.UnicodeBlock.BASIC_LATIN, Character.UnicodeBlock.of((char) 0x0));
        assertEquals(Character.UnicodeBlock.BASIC_LATIN, Character.UnicodeBlock.of((char) 0x7f));
        assertEquals(Character.UnicodeBlock.LATIN_1_SUPPLEMENT, Character.UnicodeBlock.of((char) 0x80));
        assertEquals(Character.UnicodeBlock.LATIN_1_SUPPLEMENT, Character.UnicodeBlock.of((char) 0xff));
        assertEquals(Character.UnicodeBlock.LATIN_EXTENDED_A, Character.UnicodeBlock.of((char) 0x100));
        assertEquals(Character.UnicodeBlock.LATIN_EXTENDED_A, Character.UnicodeBlock.of((char) 0x17f));
        assertEquals(Character.UnicodeBlock.LATIN_EXTENDED_B, Character.UnicodeBlock.of((char) 0x180));
        assertEquals(Character.UnicodeBlock.LATIN_EXTENDED_B, Character.UnicodeBlock.of((char) 0x24f));
        assertEquals(Character.UnicodeBlock.IPA_EXTENSIONS, Character.UnicodeBlock.of((char) 0x250));
        assertEquals(Character.UnicodeBlock.IPA_EXTENSIONS, Character.UnicodeBlock.of((char) 0x2af));
        assertEquals(Character.UnicodeBlock.SPACING_MODIFIER_LETTERS, Character.UnicodeBlock.of((char) 0x2b0));
        assertEquals(Character.UnicodeBlock.SPACING_MODIFIER_LETTERS, Character.UnicodeBlock.of((char) 0x2ff));
        assertEquals(Character.UnicodeBlock.COMBINING_DIACRITICAL_MARKS, Character.UnicodeBlock.of((char) 0x300));
        assertEquals(Character.UnicodeBlock.COMBINING_DIACRITICAL_MARKS, Character.UnicodeBlock.of((char) 0x36f));
        assertEquals(Character.UnicodeBlock.GREEK, Character.UnicodeBlock.of((char) 0x370));
        assertEquals(Character.UnicodeBlock.GREEK, Character.UnicodeBlock.of((char) 0x3ff));
        assertEquals(Character.UnicodeBlock.CYRILLIC, Character.UnicodeBlock.of((char) 0x400));
        assertEquals(Character.UnicodeBlock.CYRILLIC, Character.UnicodeBlock.of((char) 0x4ff));
        assertEquals(Character.UnicodeBlock.CYRILLIC_SUPPLEMENTARY, Character.UnicodeBlock.of((char) 0x500));
        assertEquals(Character.UnicodeBlock.CYRILLIC_SUPPLEMENTARY, Character.UnicodeBlock.of((char) 0x52f));
        assertEquals(Character.UnicodeBlock.ARMENIAN, Character.UnicodeBlock.of((char) 0x530));
        assertEquals(Character.UnicodeBlock.ARMENIAN, Character.UnicodeBlock.of((char) 0x58f));
        assertEquals(Character.UnicodeBlock.HEBREW, Character.UnicodeBlock.of((char) 0x590));
        assertEquals(Character.UnicodeBlock.HEBREW, Character.UnicodeBlock.of((char) 0x5ff));
        assertEquals(Character.UnicodeBlock.ARABIC, Character.UnicodeBlock.of((char) 0x600));
        assertEquals(Character.UnicodeBlock.ARABIC, Character.UnicodeBlock.of((char) 0x6ff));
        assertEquals(Character.UnicodeBlock.SYRIAC, Character.UnicodeBlock.of((char) 0x700));
        assertEquals(Character.UnicodeBlock.SYRIAC, Character.UnicodeBlock.of((char) 0x74f));
        assertEquals(Character.UnicodeBlock.THAANA, Character.UnicodeBlock.of((char) 0x780));
        assertEquals(Character.UnicodeBlock.THAANA, Character.UnicodeBlock.of((char) 0x7bf));
        assertEquals(Character.UnicodeBlock.DEVANAGARI, Character.UnicodeBlock.of((char) 0x900));
        assertEquals(Character.UnicodeBlock.DEVANAGARI, Character.UnicodeBlock.of((char) 0x97f));
        assertEquals(Character.UnicodeBlock.BENGALI, Character.UnicodeBlock.of((char) 0x980));
        assertEquals(Character.UnicodeBlock.BENGALI, Character.UnicodeBlock.of((char) 0x9ff));
        assertEquals(Character.UnicodeBlock.GURMUKHI, Character.UnicodeBlock.of((char) 0xa00));
        assertEquals(Character.UnicodeBlock.GURMUKHI, Character.UnicodeBlock.of((char) 0xa7f));
        assertEquals(Character.UnicodeBlock.GUJARATI, Character.UnicodeBlock.of((char) 0xa80));
        assertEquals(Character.UnicodeBlock.GUJARATI, Character.UnicodeBlock.of((char) 0xaff));
        assertEquals(Character.UnicodeBlock.ORIYA, Character.UnicodeBlock.of((char) 0xb00));
        assertEquals(Character.UnicodeBlock.ORIYA, Character.UnicodeBlock.of((char) 0xb7f));
        assertEquals(Character.UnicodeBlock.TAMIL, Character.UnicodeBlock.of((char) 0xb80));
        assertEquals(Character.UnicodeBlock.TAMIL, Character.UnicodeBlock.of((char) 0xbff));
        assertEquals(Character.UnicodeBlock.TELUGU, Character.UnicodeBlock.of((char) 0xc00));
        assertEquals(Character.UnicodeBlock.TELUGU, Character.UnicodeBlock.of((char) 0xc7f));
        assertEquals(Character.UnicodeBlock.KANNADA, Character.UnicodeBlock.of((char) 0xc80));
        assertEquals(Character.UnicodeBlock.KANNADA, Character.UnicodeBlock.of((char) 0xcff));
        assertEquals(Character.UnicodeBlock.MALAYALAM, Character.UnicodeBlock.of((char) 0xd00));
        assertEquals(Character.UnicodeBlock.MALAYALAM, Character.UnicodeBlock.of((char) 0xd7f));
        assertEquals(Character.UnicodeBlock.SINHALA, Character.UnicodeBlock.of((char) 0xd80));
        assertEquals(Character.UnicodeBlock.SINHALA, Character.UnicodeBlock.of((char) 0xdff));
        assertEquals(Character.UnicodeBlock.THAI, Character.UnicodeBlock.of((char) 0xe00));
        assertEquals(Character.UnicodeBlock.THAI, Character.UnicodeBlock.of((char) 0xe7f));
        assertEquals(Character.UnicodeBlock.LAO, Character.UnicodeBlock.of((char) 0xe80));
        assertEquals(Character.UnicodeBlock.LAO, Character.UnicodeBlock.of((char) 0xeff));
        assertEquals(Character.UnicodeBlock.TIBETAN, Character.UnicodeBlock.of((char) 0xf00));
        assertEquals(Character.UnicodeBlock.TIBETAN, Character.UnicodeBlock.of((char) 0xfff));
        assertEquals(Character.UnicodeBlock.MYANMAR, Character.UnicodeBlock.of((char) 0x1000));
        assertEquals(Character.UnicodeBlock.MYANMAR, Character.UnicodeBlock.of((char) 0x109f));
        assertEquals(Character.UnicodeBlock.GEORGIAN, Character.UnicodeBlock.of((char) 0x10a0));
        assertEquals(Character.UnicodeBlock.GEORGIAN, Character.UnicodeBlock.of((char) 0x10ff));
        assertEquals(Character.UnicodeBlock.HANGUL_JAMO, Character.UnicodeBlock.of((char) 0x1100));
        assertEquals(Character.UnicodeBlock.HANGUL_JAMO, Character.UnicodeBlock.of((char) 0x11ff));
        assertEquals(Character.UnicodeBlock.ETHIOPIC, Character.UnicodeBlock.of((char) 0x1200));
        assertEquals(Character.UnicodeBlock.ETHIOPIC, Character.UnicodeBlock.of((char) 0x137f));
        assertEquals(Character.UnicodeBlock.CHEROKEE, Character.UnicodeBlock.of((char) 0x13a0));
        assertEquals(Character.UnicodeBlock.CHEROKEE, Character.UnicodeBlock.of((char) 0x13ff));
        assertEquals(Character.UnicodeBlock.UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS, Character.UnicodeBlock.of((char) 0x1400));
        assertEquals(Character.UnicodeBlock.UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS, Character.UnicodeBlock.of((char) 0x167f));
        assertEquals(Character.UnicodeBlock.OGHAM, Character.UnicodeBlock.of((char) 0x1680));
        assertEquals(Character.UnicodeBlock.OGHAM, Character.UnicodeBlock.of((char) 0x169f));
        assertEquals(Character.UnicodeBlock.RUNIC, Character.UnicodeBlock.of((char) 0x16a0));
        assertEquals(Character.UnicodeBlock.RUNIC, Character.UnicodeBlock.of((char) 0x16ff));
        assertEquals(Character.UnicodeBlock.TAGALOG, Character.UnicodeBlock.of((char) 0x1700));
        assertEquals(Character.UnicodeBlock.TAGALOG, Character.UnicodeBlock.of((char) 0x171f));
        assertEquals(Character.UnicodeBlock.HANUNOO, Character.UnicodeBlock.of((char) 0x1720));
        assertEquals(Character.UnicodeBlock.HANUNOO, Character.UnicodeBlock.of((char) 0x173f));
        assertEquals(Character.UnicodeBlock.BUHID, Character.UnicodeBlock.of((char) 0x1740));
        assertEquals(Character.UnicodeBlock.BUHID, Character.UnicodeBlock.of((char) 0x175f));
        assertEquals(Character.UnicodeBlock.TAGBANWA, Character.UnicodeBlock.of((char) 0x1760));
        assertEquals(Character.UnicodeBlock.TAGBANWA, Character.UnicodeBlock.of((char) 0x177f));
        assertEquals(Character.UnicodeBlock.KHMER, Character.UnicodeBlock.of((char) 0x1780));
        assertEquals(Character.UnicodeBlock.KHMER, Character.UnicodeBlock.of((char) 0x17ff));
        assertEquals(Character.UnicodeBlock.MONGOLIAN, Character.UnicodeBlock.of((char) 0x1800));
        assertEquals(Character.UnicodeBlock.MONGOLIAN, Character.UnicodeBlock.of((char) 0x18af));
        assertEquals(Character.UnicodeBlock.LIMBU, Character.UnicodeBlock.of((char) 0x1900));
        assertEquals(Character.UnicodeBlock.LIMBU, Character.UnicodeBlock.of((char) 0x194f));
        assertEquals(Character.UnicodeBlock.TAI_LE, Character.UnicodeBlock.of((char) 0x1950));
        assertEquals(Character.UnicodeBlock.TAI_LE, Character.UnicodeBlock.of((char) 0x197f));
        assertEquals(Character.UnicodeBlock.KHMER_SYMBOLS, Character.UnicodeBlock.of((char) 0x19e0));
        assertEquals(Character.UnicodeBlock.KHMER_SYMBOLS, Character.UnicodeBlock.of((char) 0x19ff));
        assertEquals(Character.UnicodeBlock.PHONETIC_EXTENSIONS, Character.UnicodeBlock.of((char) 0x1d00));
        assertEquals(Character.UnicodeBlock.PHONETIC_EXTENSIONS, Character.UnicodeBlock.of((char) 0x1d7f));
        assertEquals(Character.UnicodeBlock.LATIN_EXTENDED_ADDITIONAL, Character.UnicodeBlock.of((char) 0x1e00));
        assertEquals(Character.UnicodeBlock.LATIN_EXTENDED_ADDITIONAL, Character.UnicodeBlock.of((char) 0x1eff));
        assertEquals(Character.UnicodeBlock.GREEK_EXTENDED, Character.UnicodeBlock.of((char) 0x1f00));
        assertEquals(Character.UnicodeBlock.GREEK_EXTENDED, Character.UnicodeBlock.of((char) 0x1fff));
        assertEquals(Character.UnicodeBlock.GENERAL_PUNCTUATION, Character.UnicodeBlock.of((char) 0x2000));
        assertEquals(Character.UnicodeBlock.GENERAL_PUNCTUATION, Character.UnicodeBlock.of((char) 0x206f));
        assertEquals(Character.UnicodeBlock.SUPERSCRIPTS_AND_SUBSCRIPTS, Character.UnicodeBlock.of((char) 0x2070));
        assertEquals(Character.UnicodeBlock.SUPERSCRIPTS_AND_SUBSCRIPTS, Character.UnicodeBlock.of((char) 0x209f));
        assertEquals(Character.UnicodeBlock.CURRENCY_SYMBOLS, Character.UnicodeBlock.of((char) 0x20a0));
        assertEquals(Character.UnicodeBlock.CURRENCY_SYMBOLS, Character.UnicodeBlock.of((char) 0x20cf));
        assertEquals(Character.UnicodeBlock.COMBINING_MARKS_FOR_SYMBOLS, Character.UnicodeBlock.of((char) 0x20d0));
        assertEquals(Character.UnicodeBlock.COMBINING_MARKS_FOR_SYMBOLS, Character.UnicodeBlock.of((char) 0x20ff));
        assertEquals(Character.UnicodeBlock.LETTERLIKE_SYMBOLS, Character.UnicodeBlock.of((char) 0x2100));
        assertEquals(Character.UnicodeBlock.LETTERLIKE_SYMBOLS, Character.UnicodeBlock.of((char) 0x214f));
        assertEquals(Character.UnicodeBlock.NUMBER_FORMS, Character.UnicodeBlock.of((char) 0x2150));
        assertEquals(Character.UnicodeBlock.NUMBER_FORMS, Character.UnicodeBlock.of((char) 0x218f));
        assertEquals(Character.UnicodeBlock.ARROWS, Character.UnicodeBlock.of((char) 0x2190));
        assertEquals(Character.UnicodeBlock.ARROWS, Character.UnicodeBlock.of((char) 0x21ff));
        assertEquals(Character.UnicodeBlock.MATHEMATICAL_OPERATORS, Character.UnicodeBlock.of((char) 0x2200));
        assertEquals(Character.UnicodeBlock.MATHEMATICAL_OPERATORS, Character.UnicodeBlock.of((char) 0x22ff));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_TECHNICAL, Character.UnicodeBlock.of((char) 0x2300));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_TECHNICAL, Character.UnicodeBlock.of((char) 0x23ff));
        assertEquals(Character.UnicodeBlock.CONTROL_PICTURES, Character.UnicodeBlock.of((char) 0x2400));
        assertEquals(Character.UnicodeBlock.CONTROL_PICTURES, Character.UnicodeBlock.of((char) 0x243f));
        assertEquals(Character.UnicodeBlock.OPTICAL_CHARACTER_RECOGNITION, Character.UnicodeBlock.of((char) 0x2440));
        assertEquals(Character.UnicodeBlock.OPTICAL_CHARACTER_RECOGNITION, Character.UnicodeBlock.of((char) 0x245f));
        assertEquals(Character.UnicodeBlock.ENCLOSED_ALPHANUMERICS, Character.UnicodeBlock.of((char) 0x2460));
        assertEquals(Character.UnicodeBlock.ENCLOSED_ALPHANUMERICS, Character.UnicodeBlock.of((char) 0x24ff));
        assertEquals(Character.UnicodeBlock.BOX_DRAWING, Character.UnicodeBlock.of((char) 0x2500));
        assertEquals(Character.UnicodeBlock.BOX_DRAWING, Character.UnicodeBlock.of((char) 0x257f));
        assertEquals(Character.UnicodeBlock.BLOCK_ELEMENTS, Character.UnicodeBlock.of((char) 0x2580));
        assertEquals(Character.UnicodeBlock.BLOCK_ELEMENTS, Character.UnicodeBlock.of((char) 0x259f));
        assertEquals(Character.UnicodeBlock.GEOMETRIC_SHAPES, Character.UnicodeBlock.of((char) 0x25a0));
        assertEquals(Character.UnicodeBlock.GEOMETRIC_SHAPES, Character.UnicodeBlock.of((char) 0x25ff));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_SYMBOLS, Character.UnicodeBlock.of((char) 0x2600));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_SYMBOLS, Character.UnicodeBlock.of((char) 0x26ff));
        assertEquals(Character.UnicodeBlock.DINGBATS, Character.UnicodeBlock.of((char) 0x2700));
        assertEquals(Character.UnicodeBlock.DINGBATS, Character.UnicodeBlock.of((char) 0x27bf));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A, Character.UnicodeBlock.of((char) 0x27c0));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A, Character.UnicodeBlock.of((char) 0x27ef));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTAL_ARROWS_A, Character.UnicodeBlock.of((char) 0x27f0));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTAL_ARROWS_A, Character.UnicodeBlock.of((char) 0x27ff));
        assertEquals(Character.UnicodeBlock.BRAILLE_PATTERNS, Character.UnicodeBlock.of((char) 0x2800));
        assertEquals(Character.UnicodeBlock.BRAILLE_PATTERNS, Character.UnicodeBlock.of((char) 0x28ff));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTAL_ARROWS_B, Character.UnicodeBlock.of((char) 0x2900));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTAL_ARROWS_B, Character.UnicodeBlock.of((char) 0x297f));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B, Character.UnicodeBlock.of((char) 0x2980));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B, Character.UnicodeBlock.of((char) 0x29ff));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTAL_MATHEMATICAL_OPERATORS, Character.UnicodeBlock.of((char) 0x2a00));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTAL_MATHEMATICAL_OPERATORS, Character.UnicodeBlock.of((char) 0x2aff));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_SYMBOLS_AND_ARROWS, Character.UnicodeBlock.of((char) 0x2b00));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_SYMBOLS_AND_ARROWS, Character.UnicodeBlock.of((char) 0x2bff));
        assertEquals(Character.UnicodeBlock.CJK_RADICALS_SUPPLEMENT, Character.UnicodeBlock.of((char) 0x2e80));
        assertEquals(Character.UnicodeBlock.CJK_RADICALS_SUPPLEMENT, Character.UnicodeBlock.of((char) 0x2eff));
        assertEquals(Character.UnicodeBlock.KANGXI_RADICALS, Character.UnicodeBlock.of((char) 0x2f00));
        assertEquals(Character.UnicodeBlock.KANGXI_RADICALS, Character.UnicodeBlock.of((char) 0x2fdf));
        assertEquals(Character.UnicodeBlock.IDEOGRAPHIC_DESCRIPTION_CHARACTERS, Character.UnicodeBlock.of((char) 0x2ff0));
        assertEquals(Character.UnicodeBlock.IDEOGRAPHIC_DESCRIPTION_CHARACTERS, Character.UnicodeBlock.of((char) 0x2fff));
        assertEquals(Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION, Character.UnicodeBlock.of((char) 0x3000));
        assertEquals(Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION, Character.UnicodeBlock.of((char) 0x303f));
        assertEquals(Character.UnicodeBlock.HIRAGANA, Character.UnicodeBlock.of((char) 0x3040));
        assertEquals(Character.UnicodeBlock.HIRAGANA, Character.UnicodeBlock.of((char) 0x309f));
        assertEquals(Character.UnicodeBlock.KATAKANA, Character.UnicodeBlock.of((char) 0x30a0));
        assertEquals(Character.UnicodeBlock.KATAKANA, Character.UnicodeBlock.of((char) 0x30ff));
        assertEquals(Character.UnicodeBlock.BOPOMOFO, Character.UnicodeBlock.of((char) 0x3100));
        assertEquals(Character.UnicodeBlock.BOPOMOFO, Character.UnicodeBlock.of((char) 0x312f));
        assertEquals(Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO, Character.UnicodeBlock.of((char) 0x3130));
        assertEquals(Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO, Character.UnicodeBlock.of((char) 0x318f));
        assertEquals(Character.UnicodeBlock.KANBUN, Character.UnicodeBlock.of((char) 0x3190));
        assertEquals(Character.UnicodeBlock.KANBUN, Character.UnicodeBlock.of((char) 0x319f));
        assertEquals(Character.UnicodeBlock.BOPOMOFO_EXTENDED, Character.UnicodeBlock.of((char) 0x31a0));
        assertEquals(Character.UnicodeBlock.BOPOMOFO_EXTENDED, Character.UnicodeBlock.of((char) 0x31bf));
        assertEquals(Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS, Character.UnicodeBlock.of((char) 0x31f0));
        assertEquals(Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS, Character.UnicodeBlock.of((char) 0x31ff));
        assertEquals(Character.UnicodeBlock.ENCLOSED_CJK_LETTERS_AND_MONTHS, Character.UnicodeBlock.of((char) 0x3200));
        assertEquals(Character.UnicodeBlock.ENCLOSED_CJK_LETTERS_AND_MONTHS, Character.UnicodeBlock.of((char) 0x32ff));
        assertEquals(Character.UnicodeBlock.CJK_COMPATIBILITY, Character.UnicodeBlock.of((char) 0x3300));
        assertEquals(Character.UnicodeBlock.CJK_COMPATIBILITY, Character.UnicodeBlock.of((char) 0x33ff));
        assertEquals(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A, Character.UnicodeBlock.of((char) 0x3400));
        assertEquals(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A, Character.UnicodeBlock.of((char) 0x4dbf));
        assertEquals(Character.UnicodeBlock.YIJING_HEXAGRAM_SYMBOLS, Character.UnicodeBlock.of((char) 0x4dc0));
        assertEquals(Character.UnicodeBlock.YIJING_HEXAGRAM_SYMBOLS, Character.UnicodeBlock.of((char) 0x4dff));
        assertEquals(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS, Character.UnicodeBlock.of((char) 0x4e00));
        assertEquals(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS, Character.UnicodeBlock.of((char) 0x9fff));
        assertEquals(Character.UnicodeBlock.YI_SYLLABLES, Character.UnicodeBlock.of((char) 0xa000));
        assertEquals(Character.UnicodeBlock.YI_SYLLABLES, Character.UnicodeBlock.of((char) 0xa48f));
        assertEquals(Character.UnicodeBlock.YI_RADICALS, Character.UnicodeBlock.of((char) 0xa490));
        assertEquals(Character.UnicodeBlock.YI_RADICALS, Character.UnicodeBlock.of((char) 0xa4cf));
        assertEquals(Character.UnicodeBlock.HANGUL_SYLLABLES, Character.UnicodeBlock.of((char) 0xac00));
        assertEquals(Character.UnicodeBlock.HANGUL_SYLLABLES, Character.UnicodeBlock.of((char) 0xd7af));
        assertEquals(Character.UnicodeBlock.HIGH_SURROGATES, Character.UnicodeBlock.of((char) 0xd800));
        assertEquals(Character.UnicodeBlock.HIGH_SURROGATES, Character.UnicodeBlock.of((char) 0xdb7f));
        assertEquals(Character.UnicodeBlock.HIGH_PRIVATE_USE_SURROGATES, Character.UnicodeBlock.of((char) 0xdb80));
        assertEquals(Character.UnicodeBlock.HIGH_PRIVATE_USE_SURROGATES, Character.UnicodeBlock.of((char) 0xdbff));
        assertEquals(Character.UnicodeBlock.LOW_SURROGATES, Character.UnicodeBlock.of((char) 0xdc00));
        assertEquals(Character.UnicodeBlock.LOW_SURROGATES, Character.UnicodeBlock.of((char) 0xdfff));
        assertEquals(Character.UnicodeBlock.PRIVATE_USE_AREA, Character.UnicodeBlock.of((char) 0xe000));
        assertEquals(Character.UnicodeBlock.PRIVATE_USE_AREA, Character.UnicodeBlock.of((char) 0xf8ff));
        assertEquals(Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS, Character.UnicodeBlock.of((char) 0xf900));
        assertEquals(Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS, Character.UnicodeBlock.of((char) 0xfaff));
        assertEquals(Character.UnicodeBlock.ALPHABETIC_PRESENTATION_FORMS, Character.UnicodeBlock.of((char) 0xfb00));
        assertEquals(Character.UnicodeBlock.ALPHABETIC_PRESENTATION_FORMS, Character.UnicodeBlock.of((char) 0xfb4f));
        assertEquals(Character.UnicodeBlock.ARABIC_PRESENTATION_FORMS_A, Character.UnicodeBlock.of((char) 0xfb50));
        assertEquals(Character.UnicodeBlock.ARABIC_PRESENTATION_FORMS_A, Character.UnicodeBlock.of((char) 0xfdff));
        assertEquals(Character.UnicodeBlock.VARIATION_SELECTORS, Character.UnicodeBlock.of((char) 0xfe00));
        assertEquals(Character.UnicodeBlock.VARIATION_SELECTORS, Character.UnicodeBlock.of((char) 0xfe0f));
        assertEquals(Character.UnicodeBlock.COMBINING_HALF_MARKS, Character.UnicodeBlock.of((char) 0xfe20));
        assertEquals(Character.UnicodeBlock.COMBINING_HALF_MARKS, Character.UnicodeBlock.of((char) 0xfe2f));
        assertEquals(Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS, Character.UnicodeBlock.of((char) 0xfe30));
        assertEquals(Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS, Character.UnicodeBlock.of((char) 0xfe4f));
        assertEquals(Character.UnicodeBlock.SMALL_FORM_VARIANTS, Character.UnicodeBlock.of((char) 0xfe50));
        assertEquals(Character.UnicodeBlock.SMALL_FORM_VARIANTS, Character.UnicodeBlock.of((char) 0xfe6f));
        assertEquals(Character.UnicodeBlock.ARABIC_PRESENTATION_FORMS_B, Character.UnicodeBlock.of((char) 0xfe70));
        assertEquals(Character.UnicodeBlock.ARABIC_PRESENTATION_FORMS_B, Character.UnicodeBlock.of((char) 0xfeff));
        assertEquals(Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS, Character.UnicodeBlock.of((char) 0xff00));
        assertEquals(Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS, Character.UnicodeBlock.of((char) 0xffef));
        assertEquals(Character.UnicodeBlock.SPECIALS, Character.UnicodeBlock.of((char) 0xfff0));
        assertEquals(Character.UnicodeBlock.SPECIALS, Character.UnicodeBlock.of((char) 0xffff));

        // Blocks added in 1.8
        assertEquals(Character.UnicodeBlock.ARABIC_EXTENDED_A, Character.UnicodeBlock.of((char) 0x08a0));
        assertEquals(Character.UnicodeBlock.ARABIC_EXTENDED_A, Character.UnicodeBlock.of((char) 0x08ff));
        assertEquals(Character.UnicodeBlock.SUNDANESE_SUPPLEMENT, Character.UnicodeBlock.of((char) 0x1cc0));
        assertEquals(Character.UnicodeBlock.SUNDANESE_SUPPLEMENT, Character.UnicodeBlock.of((char) 0x1ccf));
        assertEquals(Character.UnicodeBlock.MEETEI_MAYEK_EXTENSIONS, Character.UnicodeBlock.of((char) 0xaae0));
        assertEquals(Character.UnicodeBlock.MEETEI_MAYEK_EXTENSIONS, Character.UnicodeBlock.of((char) 0xaaff));

        // Blocks updated for 17
        assertEquals(Character.UnicodeBlock.SYRIAC_SUPPLEMENT, Character.UnicodeBlock.of((char) 0x0860));
        assertEquals(Character.UnicodeBlock.SYRIAC_SUPPLEMENT, Character.UnicodeBlock.of((char) 0x086f));
        assertEquals(Character.UnicodeBlock.COMBINING_DIACRITICAL_MARKS_EXTENDED, Character.UnicodeBlock.of((char) 0x1ab0));
        assertEquals(Character.UnicodeBlock.COMBINING_DIACRITICAL_MARKS_EXTENDED, Character.UnicodeBlock.of((char) 0x1abf));
        assertEquals(Character.UnicodeBlock.CYRILLIC_EXTENDED_C, Character.UnicodeBlock.of((char) 0x1c80));
        assertEquals(Character.UnicodeBlock.CYRILLIC_EXTENDED_C, Character.UnicodeBlock.of((char) 0x1c8f));
        assertEquals(Character.UnicodeBlock.GEORGIAN_EXTENDED, Character.UnicodeBlock.of((char) 0x1c90));
        assertEquals(Character.UnicodeBlock.GEORGIAN_EXTENDED, Character.UnicodeBlock.of((char) 0x1cbf));
        assertEquals(Character.UnicodeBlock.MYANMAR_EXTENDED_B, Character.UnicodeBlock.of((char) 0xa9e0));
        assertEquals(Character.UnicodeBlock.MYANMAR_EXTENDED_B, Character.UnicodeBlock.of((char) 0xa9ff));
        assertEquals(Character.UnicodeBlock.LATIN_EXTENDED_E, Character.UnicodeBlock.of((char) 0xab30));
        assertEquals(Character.UnicodeBlock.LATIN_EXTENDED_E, Character.UnicodeBlock.of((char) 0xab6f));
        assertEquals(Character.UnicodeBlock.CHEROKEE_SUPPLEMENT, Character.UnicodeBlock.of((char) 0xab70));
        assertEquals(Character.UnicodeBlock.CHEROKEE_SUPPLEMENT, Character.UnicodeBlock.of((char) 0xabbf));

        // Negative test: Test unassigned ranges
        for (UnassignedRange range : UNASSIGNED_RANGES) {
            if (!range.isInCharRange()) {
                continue;
            }
            assertEquals(
                    "Range start populated for " + range,
                    null,
                    Character.UnicodeBlock.of((char) range.start()));
            assertEquals(
                    "Range end populated for " + range,
                    null,
                    Character.UnicodeBlock.of((char) range.end()));
        }
    }

    public void test_ofI() {
        assertEquals(Character.UnicodeBlock.BASIC_LATIN, Character.UnicodeBlock.of(0x0));
        assertEquals(Character.UnicodeBlock.BASIC_LATIN, Character.UnicodeBlock.of(0x7f));
        assertEquals(Character.UnicodeBlock.LATIN_1_SUPPLEMENT, Character.UnicodeBlock.of(0x80));
        assertEquals(Character.UnicodeBlock.LATIN_1_SUPPLEMENT, Character.UnicodeBlock.of(0xff));
        assertEquals(Character.UnicodeBlock.LATIN_EXTENDED_A, Character.UnicodeBlock.of(0x100));
        assertEquals(Character.UnicodeBlock.LATIN_EXTENDED_A, Character.UnicodeBlock.of(0x17f));
        assertEquals(Character.UnicodeBlock.LATIN_EXTENDED_B, Character.UnicodeBlock.of(0x180));
        assertEquals(Character.UnicodeBlock.LATIN_EXTENDED_B, Character.UnicodeBlock.of(0x24f));
        assertEquals(Character.UnicodeBlock.IPA_EXTENSIONS, Character.UnicodeBlock.of(0x250));
        assertEquals(Character.UnicodeBlock.IPA_EXTENSIONS, Character.UnicodeBlock.of(0x2af));
        assertEquals(Character.UnicodeBlock.SPACING_MODIFIER_LETTERS, Character.UnicodeBlock.of(0x2b0));
        assertEquals(Character.UnicodeBlock.SPACING_MODIFIER_LETTERS, Character.UnicodeBlock.of(0x2ff));
        assertEquals(Character.UnicodeBlock.COMBINING_DIACRITICAL_MARKS, Character.UnicodeBlock.of(0x300));
        assertEquals(Character.UnicodeBlock.COMBINING_DIACRITICAL_MARKS, Character.UnicodeBlock.of(0x36f));
        assertEquals(Character.UnicodeBlock.GREEK, Character.UnicodeBlock.of(0x370));
        assertEquals(Character.UnicodeBlock.GREEK, Character.UnicodeBlock.of(0x3ff));
        assertEquals(Character.UnicodeBlock.CYRILLIC, Character.UnicodeBlock.of(0x400));
        assertEquals(Character.UnicodeBlock.CYRILLIC, Character.UnicodeBlock.of(0x4ff));
        assertEquals(Character.UnicodeBlock.CYRILLIC_SUPPLEMENTARY, Character.UnicodeBlock.of(0x500));
        assertEquals(Character.UnicodeBlock.CYRILLIC_SUPPLEMENTARY, Character.UnicodeBlock.of(0x52f));
        assertEquals(Character.UnicodeBlock.ARMENIAN, Character.UnicodeBlock.of(0x530));
        assertEquals(Character.UnicodeBlock.ARMENIAN, Character.UnicodeBlock.of(0x58f));
        assertEquals(Character.UnicodeBlock.HEBREW, Character.UnicodeBlock.of(0x590));
        assertEquals(Character.UnicodeBlock.HEBREW, Character.UnicodeBlock.of(0x5ff));
        assertEquals(Character.UnicodeBlock.ARABIC, Character.UnicodeBlock.of(0x600));
        assertEquals(Character.UnicodeBlock.ARABIC, Character.UnicodeBlock.of(0x6ff));
        assertEquals(Character.UnicodeBlock.SYRIAC, Character.UnicodeBlock.of(0x700));
        assertEquals(Character.UnicodeBlock.SYRIAC, Character.UnicodeBlock.of(0x74f));
        assertEquals(Character.UnicodeBlock.THAANA, Character.UnicodeBlock.of(0x780));
        assertEquals(Character.UnicodeBlock.THAANA, Character.UnicodeBlock.of(0x7bf));
        assertEquals(Character.UnicodeBlock.DEVANAGARI, Character.UnicodeBlock.of(0x900));
        assertEquals(Character.UnicodeBlock.DEVANAGARI, Character.UnicodeBlock.of(0x97f));
        assertEquals(Character.UnicodeBlock.BENGALI, Character.UnicodeBlock.of(0x980));
        assertEquals(Character.UnicodeBlock.BENGALI, Character.UnicodeBlock.of(0x9ff));
        assertEquals(Character.UnicodeBlock.GURMUKHI, Character.UnicodeBlock.of(0xa00));
        assertEquals(Character.UnicodeBlock.GURMUKHI, Character.UnicodeBlock.of(0xa7f));
        assertEquals(Character.UnicodeBlock.GUJARATI, Character.UnicodeBlock.of(0xa80));
        assertEquals(Character.UnicodeBlock.GUJARATI, Character.UnicodeBlock.of(0xaff));
        assertEquals(Character.UnicodeBlock.ORIYA, Character.UnicodeBlock.of(0xb00));
        assertEquals(Character.UnicodeBlock.ORIYA, Character.UnicodeBlock.of(0xb7f));
        assertEquals(Character.UnicodeBlock.TAMIL, Character.UnicodeBlock.of(0xb80));
        assertEquals(Character.UnicodeBlock.TAMIL, Character.UnicodeBlock.of(0xbff));
        assertEquals(Character.UnicodeBlock.TELUGU, Character.UnicodeBlock.of(0xc00));
        assertEquals(Character.UnicodeBlock.TELUGU, Character.UnicodeBlock.of(0xc7f));
        assertEquals(Character.UnicodeBlock.KANNADA, Character.UnicodeBlock.of(0xc80));
        assertEquals(Character.UnicodeBlock.KANNADA, Character.UnicodeBlock.of(0xcff));
        assertEquals(Character.UnicodeBlock.MALAYALAM, Character.UnicodeBlock.of(0xd00));
        assertEquals(Character.UnicodeBlock.MALAYALAM, Character.UnicodeBlock.of(0xd7f));
        assertEquals(Character.UnicodeBlock.SINHALA, Character.UnicodeBlock.of(0xd80));
        assertEquals(Character.UnicodeBlock.SINHALA, Character.UnicodeBlock.of(0xdff));
        assertEquals(Character.UnicodeBlock.THAI, Character.UnicodeBlock.of(0xe00));
        assertEquals(Character.UnicodeBlock.THAI, Character.UnicodeBlock.of(0xe7f));
        assertEquals(Character.UnicodeBlock.LAO, Character.UnicodeBlock.of(0xe80));
        assertEquals(Character.UnicodeBlock.LAO, Character.UnicodeBlock.of(0xeff));
        assertEquals(Character.UnicodeBlock.TIBETAN, Character.UnicodeBlock.of(0xf00));
        assertEquals(Character.UnicodeBlock.TIBETAN, Character.UnicodeBlock.of(0xfff));
        assertEquals(Character.UnicodeBlock.MYANMAR, Character.UnicodeBlock.of(0x1000));
        assertEquals(Character.UnicodeBlock.MYANMAR, Character.UnicodeBlock.of(0x109f));
        assertEquals(Character.UnicodeBlock.GEORGIAN, Character.UnicodeBlock.of(0x10a0));
        assertEquals(Character.UnicodeBlock.GEORGIAN, Character.UnicodeBlock.of(0x10ff));
        assertEquals(Character.UnicodeBlock.HANGUL_JAMO, Character.UnicodeBlock.of(0x1100));
        assertEquals(Character.UnicodeBlock.HANGUL_JAMO, Character.UnicodeBlock.of(0x11ff));
        assertEquals(Character.UnicodeBlock.ETHIOPIC, Character.UnicodeBlock.of(0x1200));
        assertEquals(Character.UnicodeBlock.ETHIOPIC, Character.UnicodeBlock.of(0x137f));
        assertEquals(Character.UnicodeBlock.CHEROKEE, Character.UnicodeBlock.of(0x13a0));
        assertEquals(Character.UnicodeBlock.CHEROKEE, Character.UnicodeBlock.of(0x13ff));
        assertEquals(Character.UnicodeBlock.UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS, Character.UnicodeBlock.of(0x1400));
        assertEquals(Character.UnicodeBlock.UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS, Character.UnicodeBlock.of(0x167f));
        assertEquals(Character.UnicodeBlock.OGHAM, Character.UnicodeBlock.of(0x1680));
        assertEquals(Character.UnicodeBlock.OGHAM, Character.UnicodeBlock.of(0x169f));
        assertEquals(Character.UnicodeBlock.RUNIC, Character.UnicodeBlock.of(0x16a0));
        assertEquals(Character.UnicodeBlock.RUNIC, Character.UnicodeBlock.of(0x16ff));
        assertEquals(Character.UnicodeBlock.TAGALOG, Character.UnicodeBlock.of(0x1700));
        assertEquals(Character.UnicodeBlock.TAGALOG, Character.UnicodeBlock.of(0x171f));
        assertEquals(Character.UnicodeBlock.HANUNOO, Character.UnicodeBlock.of(0x1720));
        assertEquals(Character.UnicodeBlock.HANUNOO, Character.UnicodeBlock.of(0x173f));
        assertEquals(Character.UnicodeBlock.BUHID, Character.UnicodeBlock.of(0x1740));
        assertEquals(Character.UnicodeBlock.BUHID, Character.UnicodeBlock.of(0x175f));
        assertEquals(Character.UnicodeBlock.TAGBANWA, Character.UnicodeBlock.of(0x1760));
        assertEquals(Character.UnicodeBlock.TAGBANWA, Character.UnicodeBlock.of(0x177f));
        assertEquals(Character.UnicodeBlock.KHMER, Character.UnicodeBlock.of(0x1780));
        assertEquals(Character.UnicodeBlock.KHMER, Character.UnicodeBlock.of(0x17ff));
        assertEquals(Character.UnicodeBlock.MONGOLIAN, Character.UnicodeBlock.of(0x1800));
        assertEquals(Character.UnicodeBlock.MONGOLIAN, Character.UnicodeBlock.of(0x18af));
        assertEquals(Character.UnicodeBlock.LIMBU, Character.UnicodeBlock.of(0x1900));
        assertEquals(Character.UnicodeBlock.LIMBU, Character.UnicodeBlock.of(0x194f));
        assertEquals(Character.UnicodeBlock.TAI_LE, Character.UnicodeBlock.of(0x1950));
        assertEquals(Character.UnicodeBlock.TAI_LE, Character.UnicodeBlock.of(0x197f));
        assertEquals(Character.UnicodeBlock.KHMER_SYMBOLS, Character.UnicodeBlock.of(0x19e0));
        assertEquals(Character.UnicodeBlock.KHMER_SYMBOLS, Character.UnicodeBlock.of(0x19ff));
        assertEquals(Character.UnicodeBlock.PHONETIC_EXTENSIONS, Character.UnicodeBlock.of(0x1d00));
        assertEquals(Character.UnicodeBlock.PHONETIC_EXTENSIONS, Character.UnicodeBlock.of(0x1d7f));
        assertEquals(Character.UnicodeBlock.LATIN_EXTENDED_ADDITIONAL, Character.UnicodeBlock.of(0x1e00));
        assertEquals(Character.UnicodeBlock.LATIN_EXTENDED_ADDITIONAL, Character.UnicodeBlock.of(0x1eff));
        assertEquals(Character.UnicodeBlock.GREEK_EXTENDED, Character.UnicodeBlock.of(0x1f00));
        assertEquals(Character.UnicodeBlock.GREEK_EXTENDED, Character.UnicodeBlock.of(0x1fff));
        assertEquals(Character.UnicodeBlock.GENERAL_PUNCTUATION, Character.UnicodeBlock.of(0x2000));
        assertEquals(Character.UnicodeBlock.GENERAL_PUNCTUATION, Character.UnicodeBlock.of(0x206f));
        assertEquals(Character.UnicodeBlock.SUPERSCRIPTS_AND_SUBSCRIPTS, Character.UnicodeBlock.of(0x2070));
        assertEquals(Character.UnicodeBlock.SUPERSCRIPTS_AND_SUBSCRIPTS, Character.UnicodeBlock.of(0x209f));
        assertEquals(Character.UnicodeBlock.CURRENCY_SYMBOLS, Character.UnicodeBlock.of(0x20a0));
        assertEquals(Character.UnicodeBlock.CURRENCY_SYMBOLS, Character.UnicodeBlock.of(0x20cf));
        assertEquals(Character.UnicodeBlock.COMBINING_MARKS_FOR_SYMBOLS, Character.UnicodeBlock.of(0x20d0));
        assertEquals(Character.UnicodeBlock.COMBINING_MARKS_FOR_SYMBOLS, Character.UnicodeBlock.of(0x20ff));
        assertEquals(Character.UnicodeBlock.LETTERLIKE_SYMBOLS, Character.UnicodeBlock.of(0x2100));
        assertEquals(Character.UnicodeBlock.LETTERLIKE_SYMBOLS, Character.UnicodeBlock.of(0x214f));
        assertEquals(Character.UnicodeBlock.NUMBER_FORMS, Character.UnicodeBlock.of(0x2150));
        assertEquals(Character.UnicodeBlock.NUMBER_FORMS, Character.UnicodeBlock.of(0x218f));
        assertEquals(Character.UnicodeBlock.ARROWS, Character.UnicodeBlock.of(0x2190));
        assertEquals(Character.UnicodeBlock.ARROWS, Character.UnicodeBlock.of(0x21ff));
        assertEquals(Character.UnicodeBlock.MATHEMATICAL_OPERATORS, Character.UnicodeBlock.of(0x2200));
        assertEquals(Character.UnicodeBlock.MATHEMATICAL_OPERATORS, Character.UnicodeBlock.of(0x22ff));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_TECHNICAL, Character.UnicodeBlock.of(0x2300));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_TECHNICAL, Character.UnicodeBlock.of(0x23ff));
        assertEquals(Character.UnicodeBlock.CONTROL_PICTURES, Character.UnicodeBlock.of(0x2400));
        assertEquals(Character.UnicodeBlock.CONTROL_PICTURES, Character.UnicodeBlock.of(0x243f));
        assertEquals(Character.UnicodeBlock.OPTICAL_CHARACTER_RECOGNITION, Character.UnicodeBlock.of(0x2440));
        assertEquals(Character.UnicodeBlock.OPTICAL_CHARACTER_RECOGNITION, Character.UnicodeBlock.of(0x245f));
        assertEquals(Character.UnicodeBlock.ENCLOSED_ALPHANUMERICS, Character.UnicodeBlock.of(0x2460));
        assertEquals(Character.UnicodeBlock.ENCLOSED_ALPHANUMERICS, Character.UnicodeBlock.of(0x24ff));
        assertEquals(Character.UnicodeBlock.BOX_DRAWING, Character.UnicodeBlock.of(0x2500));
        assertEquals(Character.UnicodeBlock.BOX_DRAWING, Character.UnicodeBlock.of(0x257f));
        assertEquals(Character.UnicodeBlock.BLOCK_ELEMENTS, Character.UnicodeBlock.of(0x2580));
        assertEquals(Character.UnicodeBlock.BLOCK_ELEMENTS, Character.UnicodeBlock.of(0x259f));
        assertEquals(Character.UnicodeBlock.GEOMETRIC_SHAPES, Character.UnicodeBlock.of(0x25a0));
        assertEquals(Character.UnicodeBlock.GEOMETRIC_SHAPES, Character.UnicodeBlock.of(0x25ff));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_SYMBOLS, Character.UnicodeBlock.of(0x2600));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_SYMBOLS, Character.UnicodeBlock.of(0x26ff));
        assertEquals(Character.UnicodeBlock.DINGBATS, Character.UnicodeBlock.of(0x2700));
        assertEquals(Character.UnicodeBlock.DINGBATS, Character.UnicodeBlock.of(0x27bf));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A, Character.UnicodeBlock.of(0x27c0));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A, Character.UnicodeBlock.of(0x27ef));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTAL_ARROWS_A, Character.UnicodeBlock.of(0x27f0));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTAL_ARROWS_A, Character.UnicodeBlock.of(0x27ff));
        assertEquals(Character.UnicodeBlock.BRAILLE_PATTERNS, Character.UnicodeBlock.of(0x2800));
        assertEquals(Character.UnicodeBlock.BRAILLE_PATTERNS, Character.UnicodeBlock.of(0x28ff));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTAL_ARROWS_B, Character.UnicodeBlock.of(0x2900));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTAL_ARROWS_B, Character.UnicodeBlock.of(0x297f));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B, Character.UnicodeBlock.of(0x2980));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B, Character.UnicodeBlock.of(0x29ff));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTAL_MATHEMATICAL_OPERATORS, Character.UnicodeBlock.of(0x2a00));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTAL_MATHEMATICAL_OPERATORS, Character.UnicodeBlock.of(0x2aff));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_SYMBOLS_AND_ARROWS, Character.UnicodeBlock.of(0x2b00));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_SYMBOLS_AND_ARROWS, Character.UnicodeBlock.of(0x2bff));
        assertEquals(Character.UnicodeBlock.CJK_RADICALS_SUPPLEMENT, Character.UnicodeBlock.of(0x2e80));
        assertEquals(Character.UnicodeBlock.CJK_RADICALS_SUPPLEMENT, Character.UnicodeBlock.of(0x2eff));
        assertEquals(Character.UnicodeBlock.KANGXI_RADICALS, Character.UnicodeBlock.of(0x2f00));
        assertEquals(Character.UnicodeBlock.KANGXI_RADICALS, Character.UnicodeBlock.of(0x2fdf));
        assertEquals(Character.UnicodeBlock.IDEOGRAPHIC_DESCRIPTION_CHARACTERS, Character.UnicodeBlock.of(0x2ff0));
        assertEquals(Character.UnicodeBlock.IDEOGRAPHIC_DESCRIPTION_CHARACTERS, Character.UnicodeBlock.of(0x2fff));
        assertEquals(Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION, Character.UnicodeBlock.of(0x3000));
        assertEquals(Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION, Character.UnicodeBlock.of(0x303f));
        assertEquals(Character.UnicodeBlock.HIRAGANA, Character.UnicodeBlock.of(0x3040));
        assertEquals(Character.UnicodeBlock.HIRAGANA, Character.UnicodeBlock.of(0x309f));
        assertEquals(Character.UnicodeBlock.KATAKANA, Character.UnicodeBlock.of(0x30a0));
        assertEquals(Character.UnicodeBlock.KATAKANA, Character.UnicodeBlock.of(0x30ff));
        assertEquals(Character.UnicodeBlock.BOPOMOFO, Character.UnicodeBlock.of(0x3100));
        assertEquals(Character.UnicodeBlock.BOPOMOFO, Character.UnicodeBlock.of(0x312f));
        assertEquals(Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO, Character.UnicodeBlock.of(0x3130));
        assertEquals(Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO, Character.UnicodeBlock.of(0x318f));
        assertEquals(Character.UnicodeBlock.KANBUN, Character.UnicodeBlock.of(0x3190));
        assertEquals(Character.UnicodeBlock.KANBUN, Character.UnicodeBlock.of(0x319f));
        assertEquals(Character.UnicodeBlock.BOPOMOFO_EXTENDED, Character.UnicodeBlock.of(0x31a0));
        assertEquals(Character.UnicodeBlock.BOPOMOFO_EXTENDED, Character.UnicodeBlock.of(0x31bf));
        assertEquals(Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS, Character.UnicodeBlock.of(0x31f0));
        assertEquals(Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS, Character.UnicodeBlock.of(0x31ff));
        assertEquals(Character.UnicodeBlock.ENCLOSED_CJK_LETTERS_AND_MONTHS, Character.UnicodeBlock.of(0x3200));
        assertEquals(Character.UnicodeBlock.ENCLOSED_CJK_LETTERS_AND_MONTHS, Character.UnicodeBlock.of(0x32ff));
        assertEquals(Character.UnicodeBlock.CJK_COMPATIBILITY, Character.UnicodeBlock.of(0x3300));
        assertEquals(Character.UnicodeBlock.CJK_COMPATIBILITY, Character.UnicodeBlock.of(0x33ff));
        assertEquals(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A, Character.UnicodeBlock.of(0x3400));
        assertEquals(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A, Character.UnicodeBlock.of(0x4dbf));
        assertEquals(Character.UnicodeBlock.YIJING_HEXAGRAM_SYMBOLS, Character.UnicodeBlock.of(0x4dc0));
        assertEquals(Character.UnicodeBlock.YIJING_HEXAGRAM_SYMBOLS, Character.UnicodeBlock.of(0x4dff));
        assertEquals(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS, Character.UnicodeBlock.of(0x4e00));
        assertEquals(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS, Character.UnicodeBlock.of(0x9fff));
        assertEquals(Character.UnicodeBlock.YI_SYLLABLES, Character.UnicodeBlock.of(0xa000));
        assertEquals(Character.UnicodeBlock.YI_SYLLABLES, Character.UnicodeBlock.of(0xa48f));
        assertEquals(Character.UnicodeBlock.YI_RADICALS, Character.UnicodeBlock.of(0xa490));
        assertEquals(Character.UnicodeBlock.YI_RADICALS, Character.UnicodeBlock.of(0xa4cf));
        assertEquals(Character.UnicodeBlock.HANGUL_SYLLABLES, Character.UnicodeBlock.of(0xac00));
        assertEquals(Character.UnicodeBlock.HANGUL_SYLLABLES, Character.UnicodeBlock.of(0xd7af));
        assertEquals(Character.UnicodeBlock.HIGH_SURROGATES, Character.UnicodeBlock.of(0xd800));
        assertEquals(Character.UnicodeBlock.HIGH_SURROGATES, Character.UnicodeBlock.of(0xdb7f));
        assertEquals(Character.UnicodeBlock.HIGH_PRIVATE_USE_SURROGATES, Character.UnicodeBlock.of(0xdb80));
        assertEquals(Character.UnicodeBlock.HIGH_PRIVATE_USE_SURROGATES, Character.UnicodeBlock.of(0xdbff));
        assertEquals(Character.UnicodeBlock.LOW_SURROGATES, Character.UnicodeBlock.of(0xdc00));
        assertEquals(Character.UnicodeBlock.LOW_SURROGATES, Character.UnicodeBlock.of(0xdfff));
        assertEquals(Character.UnicodeBlock.PRIVATE_USE_AREA, Character.UnicodeBlock.of(0xe000));
        assertEquals(Character.UnicodeBlock.PRIVATE_USE_AREA, Character.UnicodeBlock.of(0xf8ff));
        assertEquals(Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS, Character.UnicodeBlock.of(0xf900));
        assertEquals(Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS, Character.UnicodeBlock.of(0xfaff));
        assertEquals(Character.UnicodeBlock.ALPHABETIC_PRESENTATION_FORMS, Character.UnicodeBlock.of(0xfb00));
        assertEquals(Character.UnicodeBlock.ALPHABETIC_PRESENTATION_FORMS, Character.UnicodeBlock.of(0xfb4f));
        assertEquals(Character.UnicodeBlock.ARABIC_PRESENTATION_FORMS_A, Character.UnicodeBlock.of(0xfb50));
        assertEquals(Character.UnicodeBlock.ARABIC_PRESENTATION_FORMS_A, Character.UnicodeBlock.of(0xfdff));
        assertEquals(Character.UnicodeBlock.VARIATION_SELECTORS, Character.UnicodeBlock.of(0xfe00));
        assertEquals(Character.UnicodeBlock.VARIATION_SELECTORS, Character.UnicodeBlock.of(0xfe0f));
        assertEquals(Character.UnicodeBlock.COMBINING_HALF_MARKS, Character.UnicodeBlock.of(0xfe20));
        assertEquals(Character.UnicodeBlock.COMBINING_HALF_MARKS, Character.UnicodeBlock.of(0xfe2f));
        assertEquals(Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS, Character.UnicodeBlock.of(0xfe30));
        assertEquals(Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS, Character.UnicodeBlock.of(0xfe4f));
        assertEquals(Character.UnicodeBlock.SMALL_FORM_VARIANTS, Character.UnicodeBlock.of(0xfe50));
        assertEquals(Character.UnicodeBlock.SMALL_FORM_VARIANTS, Character.UnicodeBlock.of(0xfe6f));
        assertEquals(Character.UnicodeBlock.ARABIC_PRESENTATION_FORMS_B, Character.UnicodeBlock.of(0xfe70));
        assertEquals(Character.UnicodeBlock.ARABIC_PRESENTATION_FORMS_B, Character.UnicodeBlock.of(0xfeff));
        assertEquals(Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS, Character.UnicodeBlock.of(0xff00));
        assertEquals(Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS, Character.UnicodeBlock.of(0xffef));
        assertEquals(Character.UnicodeBlock.SPECIALS, Character.UnicodeBlock.of(0xfff0));
        assertEquals(Character.UnicodeBlock.SPECIALS, Character.UnicodeBlock.of(0xffff));
        assertEquals(Character.UnicodeBlock.LINEAR_B_SYLLABARY, Character.UnicodeBlock.of(0x10000));
        assertEquals(Character.UnicodeBlock.LINEAR_B_SYLLABARY, Character.UnicodeBlock.of(0x1007f));
        assertEquals(Character.UnicodeBlock.LINEAR_B_IDEOGRAMS, Character.UnicodeBlock.of(0x10080));
        assertEquals(Character.UnicodeBlock.LINEAR_B_IDEOGRAMS, Character.UnicodeBlock.of(0x100ff));
        assertEquals(Character.UnicodeBlock.AEGEAN_NUMBERS, Character.UnicodeBlock.of(0x10100));
        assertEquals(Character.UnicodeBlock.AEGEAN_NUMBERS, Character.UnicodeBlock.of(0x1013f));
        assertEquals(Character.UnicodeBlock.OLD_ITALIC, Character.UnicodeBlock.of(0x10300));
        assertEquals(Character.UnicodeBlock.OLD_ITALIC, Character.UnicodeBlock.of(0x1032f));
        assertEquals(Character.UnicodeBlock.GOTHIC, Character.UnicodeBlock.of(0x10330));
        assertEquals(Character.UnicodeBlock.GOTHIC, Character.UnicodeBlock.of(0x1034f));
        assertEquals(Character.UnicodeBlock.UGARITIC, Character.UnicodeBlock.of(0x10380));
        assertEquals(Character.UnicodeBlock.UGARITIC, Character.UnicodeBlock.of(0x1039f));
        assertEquals(Character.UnicodeBlock.DESERET, Character.UnicodeBlock.of(0x10400));
        assertEquals(Character.UnicodeBlock.DESERET, Character.UnicodeBlock.of(0x1044f));
        assertEquals(Character.UnicodeBlock.SHAVIAN, Character.UnicodeBlock.of(0x10450));
        assertEquals(Character.UnicodeBlock.SHAVIAN, Character.UnicodeBlock.of(0x1047f));
        assertEquals(Character.UnicodeBlock.OSMANYA, Character.UnicodeBlock.of(0x10480));
        assertEquals(Character.UnicodeBlock.OSMANYA, Character.UnicodeBlock.of(0x104af));
        assertEquals(Character.UnicodeBlock.CYPRIOT_SYLLABARY, Character.UnicodeBlock.of(0x10800));
        assertEquals(Character.UnicodeBlock.CYPRIOT_SYLLABARY, Character.UnicodeBlock.of(0x1083f));
        assertEquals(Character.UnicodeBlock.BYZANTINE_MUSICAL_SYMBOLS, Character.UnicodeBlock.of(0x1d000));
        assertEquals(Character.UnicodeBlock.BYZANTINE_MUSICAL_SYMBOLS, Character.UnicodeBlock.of(0x1d0ff));
        assertEquals(Character.UnicodeBlock.MUSICAL_SYMBOLS, Character.UnicodeBlock.of(0x1d100));
        assertEquals(Character.UnicodeBlock.MUSICAL_SYMBOLS, Character.UnicodeBlock.of(0x1d1ff));
        assertEquals(Character.UnicodeBlock.TAI_XUAN_JING_SYMBOLS, Character.UnicodeBlock.of(0x1d300));
        assertEquals(Character.UnicodeBlock.TAI_XUAN_JING_SYMBOLS, Character.UnicodeBlock.of(0x1d35f));
        assertEquals(Character.UnicodeBlock.MATHEMATICAL_ALPHANUMERIC_SYMBOLS, Character.UnicodeBlock.of(0x1d400));
        assertEquals(Character.UnicodeBlock.MATHEMATICAL_ALPHANUMERIC_SYMBOLS, Character.UnicodeBlock.of(0x1d7ff));
        assertEquals(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B, Character.UnicodeBlock.of(0x20000));
        assertEquals(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B, Character.UnicodeBlock.of(0x2a6df));
        assertEquals(Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT, Character.UnicodeBlock.of(0x2f800));
        assertEquals(Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT, Character.UnicodeBlock.of(0x2fa1f));
        assertEquals(Character.UnicodeBlock.TAGS, Character.UnicodeBlock.of(0xe0000));
        assertEquals(Character.UnicodeBlock.TAGS, Character.UnicodeBlock.of(0xe007f));
        assertEquals(Character.UnicodeBlock.VARIATION_SELECTORS_SUPPLEMENT, Character.UnicodeBlock.of(0xe0100));
        assertEquals(Character.UnicodeBlock.VARIATION_SELECTORS_SUPPLEMENT, Character.UnicodeBlock.of(0xe01ef));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTARY_PRIVATE_USE_AREA_A, Character.UnicodeBlock.of(0xf0000));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTARY_PRIVATE_USE_AREA_A, Character.UnicodeBlock.of(0xfffff));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTARY_PRIVATE_USE_AREA_B, Character.UnicodeBlock.of(0x100000));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTARY_PRIVATE_USE_AREA_B, Character.UnicodeBlock.of(0x10ffff));

        // Blocks added in 1.8
        assertEquals(Character.UnicodeBlock.ARABIC_EXTENDED_A, Character.UnicodeBlock.of(0x08a0));
        assertEquals(Character.UnicodeBlock.ARABIC_EXTENDED_A, Character.UnicodeBlock.of(0x08ff));
        assertEquals(Character.UnicodeBlock.SUNDANESE_SUPPLEMENT, Character.UnicodeBlock.of(0x1cc0));
        assertEquals(Character.UnicodeBlock.SUNDANESE_SUPPLEMENT, Character.UnicodeBlock.of(0x1ccf));
        assertEquals(Character.UnicodeBlock.MEETEI_MAYEK_EXTENSIONS, Character.UnicodeBlock.of(0xaae0));
        assertEquals(Character.UnicodeBlock.MEETEI_MAYEK_EXTENSIONS, Character.UnicodeBlock.of(0xaaff));
        assertEquals(Character.UnicodeBlock.MEROITIC_HIEROGLYPHS, Character.UnicodeBlock.of(0x10980));
        assertEquals(Character.UnicodeBlock.MEROITIC_HIEROGLYPHS, Character.UnicodeBlock.of(0x1099f));
        assertEquals(Character.UnicodeBlock.MEROITIC_CURSIVE, Character.UnicodeBlock.of(0x109a0));
        assertEquals(Character.UnicodeBlock.MEROITIC_CURSIVE, Character.UnicodeBlock.of(0x109ff));
        assertEquals(Character.UnicodeBlock.SORA_SOMPENG, Character.UnicodeBlock.of(0x110d0));
        assertEquals(Character.UnicodeBlock.SORA_SOMPENG, Character.UnicodeBlock.of(0x110ff));
        assertEquals(Character.UnicodeBlock.CHAKMA, Character.UnicodeBlock.of(0x11100));
        assertEquals(Character.UnicodeBlock.CHAKMA, Character.UnicodeBlock.of(0x1114f));
        assertEquals(Character.UnicodeBlock.SHARADA, Character.UnicodeBlock.of(0x11180));
        assertEquals(Character.UnicodeBlock.SHARADA, Character.UnicodeBlock.of(0x111df));
        assertEquals(Character.UnicodeBlock.TAKRI, Character.UnicodeBlock.of(0x11680));
        assertEquals(Character.UnicodeBlock.TAKRI, Character.UnicodeBlock.of(0x116cf));
        assertEquals(Character.UnicodeBlock.MIAO, Character.UnicodeBlock.of(0x16f00));
        assertEquals(Character.UnicodeBlock.MIAO, Character.UnicodeBlock.of(0x16f9f));
        assertEquals(Character.UnicodeBlock.ARABIC_MATHEMATICAL_ALPHABETIC_SYMBOLS, Character.UnicodeBlock.of(0x1ee00));
        assertEquals(Character.UnicodeBlock.ARABIC_MATHEMATICAL_ALPHABETIC_SYMBOLS, Character.UnicodeBlock.of(0x1eeff));

        // Blocks updated for 17
        assertEquals(Character.UnicodeBlock.SYRIAC_SUPPLEMENT, Character.UnicodeBlock.of(0x0860));
        assertEquals(Character.UnicodeBlock.SYRIAC_SUPPLEMENT, Character.UnicodeBlock.of(0x086f));
        assertEquals(Character.UnicodeBlock.COMBINING_DIACRITICAL_MARKS_EXTENDED, Character.UnicodeBlock.of(0x1ab0));
        assertEquals(Character.UnicodeBlock.COMBINING_DIACRITICAL_MARKS_EXTENDED, Character.UnicodeBlock.of(0x1abf));
        assertEquals(Character.UnicodeBlock.CYRILLIC_EXTENDED_C, Character.UnicodeBlock.of(0x1c80));
        assertEquals(Character.UnicodeBlock.CYRILLIC_EXTENDED_C, Character.UnicodeBlock.of(0x1c8f));
        assertEquals(Character.UnicodeBlock.GEORGIAN_EXTENDED, Character.UnicodeBlock.of(0x1c90));
        assertEquals(Character.UnicodeBlock.GEORGIAN_EXTENDED, Character.UnicodeBlock.of(0x1cbf));
        assertEquals(Character.UnicodeBlock.MYANMAR_EXTENDED_B, Character.UnicodeBlock.of(0xa9e0));
        assertEquals(Character.UnicodeBlock.MYANMAR_EXTENDED_B, Character.UnicodeBlock.of(0xa9ff));
        assertEquals(Character.UnicodeBlock.LATIN_EXTENDED_E, Character.UnicodeBlock.of(0xab30));
        assertEquals(Character.UnicodeBlock.LATIN_EXTENDED_E, Character.UnicodeBlock.of(0xab6f));
        assertEquals(Character.UnicodeBlock.CHEROKEE_SUPPLEMENT, Character.UnicodeBlock.of(0xab70));
        assertEquals(Character.UnicodeBlock.CHEROKEE_SUPPLEMENT, Character.UnicodeBlock.of(0xabbf));
        assertEquals(Character.UnicodeBlock.COPTIC_EPACT_NUMBERS, Character.UnicodeBlock.of(0x102e0));
        assertEquals(Character.UnicodeBlock.COPTIC_EPACT_NUMBERS, Character.UnicodeBlock.of(0x102e0));

        assertEquals(Character.UnicodeBlock.OLD_PERMIC, Character.UnicodeBlock.of(0x10350));
        assertEquals(Character.UnicodeBlock.OLD_PERMIC, Character.UnicodeBlock.of(0x1037f));
        assertEquals(Character.UnicodeBlock.OSAGE, Character.UnicodeBlock.of(0x104b0));
        assertEquals(Character.UnicodeBlock.OSAGE, Character.UnicodeBlock.of(0x104ff));
        assertEquals(Character.UnicodeBlock.ELBASAN, Character.UnicodeBlock.of(0x10500));
        assertEquals(Character.UnicodeBlock.ELBASAN, Character.UnicodeBlock.of(0x1052f));
        assertEquals(Character.UnicodeBlock.CAUCASIAN_ALBANIAN, Character.UnicodeBlock.of(0x10530));
        assertEquals(Character.UnicodeBlock.CAUCASIAN_ALBANIAN, Character.UnicodeBlock.of(0x1056f));
        assertEquals(Character.UnicodeBlock.LINEAR_A, Character.UnicodeBlock.of(0x10600));
        assertEquals(Character.UnicodeBlock.LINEAR_A, Character.UnicodeBlock.of(0x1077f));
        assertEquals(Character.UnicodeBlock.PALMYRENE, Character.UnicodeBlock.of(0x10860));
        assertEquals(Character.UnicodeBlock.PALMYRENE, Character.UnicodeBlock.of(0x1087f));
        assertEquals(Character.UnicodeBlock.NABATAEAN, Character.UnicodeBlock.of(0x10880));
        assertEquals(Character.UnicodeBlock.NABATAEAN, Character.UnicodeBlock.of(0x108af));
        assertEquals(Character.UnicodeBlock.HATRAN, Character.UnicodeBlock.of(0x108e0));
        assertEquals(Character.UnicodeBlock.HATRAN, Character.UnicodeBlock.of(0x108ff));
        assertEquals(Character.UnicodeBlock.OLD_NORTH_ARABIAN, Character.UnicodeBlock.of(0x10a80));
        assertEquals(Character.UnicodeBlock.OLD_NORTH_ARABIAN, Character.UnicodeBlock.of(0x10a9f));
        assertEquals(Character.UnicodeBlock.MANICHAEAN, Character.UnicodeBlock.of(0x10ac0));
        assertEquals(Character.UnicodeBlock.MANICHAEAN, Character.UnicodeBlock.of(0x10aff));
        assertEquals(Character.UnicodeBlock.PSALTER_PAHLAVI, Character.UnicodeBlock.of(0x10b80));
        assertEquals(Character.UnicodeBlock.PSALTER_PAHLAVI, Character.UnicodeBlock.of(0x10baf));
        assertEquals(Character.UnicodeBlock.OLD_HUNGARIAN, Character.UnicodeBlock.of(0x10c80));
        assertEquals(Character.UnicodeBlock.OLD_HUNGARIAN, Character.UnicodeBlock.of(0x10cff));
        assertEquals(Character.UnicodeBlock.HANIFI_ROHINGYA, Character.UnicodeBlock.of(0x10d00));
        assertEquals(Character.UnicodeBlock.HANIFI_ROHINGYA, Character.UnicodeBlock.of(0x10d3f));
        assertEquals(Character.UnicodeBlock.YEZIDI, Character.UnicodeBlock.of(0x10e80));
        assertEquals(Character.UnicodeBlock.YEZIDI, Character.UnicodeBlock.of(0x10ebf));
        assertEquals(Character.UnicodeBlock.OLD_SOGDIAN, Character.UnicodeBlock.of(0x10f00));
        assertEquals(Character.UnicodeBlock.OLD_SOGDIAN, Character.UnicodeBlock.of(0x10f2f));
        assertEquals(Character.UnicodeBlock.SOGDIAN, Character.UnicodeBlock.of(0x10f30));
        assertEquals(Character.UnicodeBlock.SOGDIAN, Character.UnicodeBlock.of(0x10f6f));
        assertEquals(Character.UnicodeBlock.CHORASMIAN, Character.UnicodeBlock.of(0x10fb0));
        assertEquals(Character.UnicodeBlock.CHORASMIAN, Character.UnicodeBlock.of(0x10fdf));
        assertEquals(Character.UnicodeBlock.ELYMAIC, Character.UnicodeBlock.of(0x10fe0));
        assertEquals(Character.UnicodeBlock.ELYMAIC, Character.UnicodeBlock.of(0x10fff));
        assertEquals(Character.UnicodeBlock.MAHAJANI, Character.UnicodeBlock.of(0x11150));
        assertEquals(Character.UnicodeBlock.MAHAJANI, Character.UnicodeBlock.of(0x1117f));
        assertEquals(Character.UnicodeBlock.SINHALA_ARCHAIC_NUMBERS, Character.UnicodeBlock.of(0x111e0));
        assertEquals(Character.UnicodeBlock.SINHALA_ARCHAIC_NUMBERS, Character.UnicodeBlock.of(0x111ff));
        assertEquals(Character.UnicodeBlock.KHOJKI, Character.UnicodeBlock.of(0x11200));
        assertEquals(Character.UnicodeBlock.KHOJKI, Character.UnicodeBlock.of(0x1124f));
        assertEquals(Character.UnicodeBlock.MULTANI, Character.UnicodeBlock.of(0x11280));
        assertEquals(Character.UnicodeBlock.MULTANI, Character.UnicodeBlock.of(0x112af));
        assertEquals(Character.UnicodeBlock.KHUDAWADI, Character.UnicodeBlock.of(0x112b0));
        assertEquals(Character.UnicodeBlock.KHUDAWADI, Character.UnicodeBlock.of(0x112ff));
        assertEquals(Character.UnicodeBlock.GRANTHA, Character.UnicodeBlock.of(0x11300));
        assertEquals(Character.UnicodeBlock.GRANTHA, Character.UnicodeBlock.of(0x1137f));
        assertEquals(Character.UnicodeBlock.NEWA, Character.UnicodeBlock.of(0x11400));
        assertEquals(Character.UnicodeBlock.NEWA, Character.UnicodeBlock.of(0x1147f));
        assertEquals(Character.UnicodeBlock.TIRHUTA, Character.UnicodeBlock.of(0x11480));
        assertEquals(Character.UnicodeBlock.TIRHUTA, Character.UnicodeBlock.of(0x114df));
        assertEquals(Character.UnicodeBlock.SIDDHAM, Character.UnicodeBlock.of(0x11580));
        assertEquals(Character.UnicodeBlock.SIDDHAM, Character.UnicodeBlock.of(0x115ff));
        assertEquals(Character.UnicodeBlock.MODI, Character.UnicodeBlock.of(0x11600));
        assertEquals(Character.UnicodeBlock.MODI, Character.UnicodeBlock.of(0x1165f));
        assertEquals(Character.UnicodeBlock.MONGOLIAN_SUPPLEMENT, Character.UnicodeBlock.of(0x11660));
        assertEquals(Character.UnicodeBlock.MONGOLIAN_SUPPLEMENT, Character.UnicodeBlock.of(0x1167f));
        assertEquals(Character.UnicodeBlock.AHOM, Character.UnicodeBlock.of(0x11700));
        assertEquals(Character.UnicodeBlock.AHOM, Character.UnicodeBlock.of(0x1173f));
        assertEquals(Character.UnicodeBlock.DOGRA, Character.UnicodeBlock.of(0x11800));
        assertEquals(Character.UnicodeBlock.DOGRA, Character.UnicodeBlock.of(0x1184f));
        assertEquals(Character.UnicodeBlock.WARANG_CITI, Character.UnicodeBlock.of(0x118a0));
        assertEquals(Character.UnicodeBlock.WARANG_CITI, Character.UnicodeBlock.of(0x118ff));
        assertEquals(Character.UnicodeBlock.DIVES_AKURU, Character.UnicodeBlock.of(0x11900));
        assertEquals(Character.UnicodeBlock.DIVES_AKURU, Character.UnicodeBlock.of(0x1195f));
        assertEquals(Character.UnicodeBlock.NANDINAGARI, Character.UnicodeBlock.of(0x119a0));
        assertEquals(Character.UnicodeBlock.NANDINAGARI, Character.UnicodeBlock.of(0x119ff));
        assertEquals(Character.UnicodeBlock.ZANABAZAR_SQUARE, Character.UnicodeBlock.of(0x11a00));
        assertEquals(Character.UnicodeBlock.ZANABAZAR_SQUARE, Character.UnicodeBlock.of(0x11a4f));
        assertEquals(Character.UnicodeBlock.SOYOMBO, Character.UnicodeBlock.of(0x11a50));
        assertEquals(Character.UnicodeBlock.SOYOMBO, Character.UnicodeBlock.of(0x11aaf));
        assertEquals(Character.UnicodeBlock.PAU_CIN_HAU, Character.UnicodeBlock.of(0x11ac0));
        assertEquals(Character.UnicodeBlock.PAU_CIN_HAU, Character.UnicodeBlock.of(0x11aff));
        assertEquals(Character.UnicodeBlock.BHAIKSUKI, Character.UnicodeBlock.of(0x11c00));
        assertEquals(Character.UnicodeBlock.BHAIKSUKI, Character.UnicodeBlock.of(0x11c6f));
        assertEquals(Character.UnicodeBlock.MARCHEN, Character.UnicodeBlock.of(0x11c70));
        assertEquals(Character.UnicodeBlock.MARCHEN, Character.UnicodeBlock.of(0x11cbf));
        assertEquals(Character.UnicodeBlock.MASARAM_GONDI, Character.UnicodeBlock.of(0x11d00));
        assertEquals(Character.UnicodeBlock.MASARAM_GONDI, Character.UnicodeBlock.of(0x11d5f));
        assertEquals(Character.UnicodeBlock.GUNJALA_GONDI, Character.UnicodeBlock.of(0x11d60));
        assertEquals(Character.UnicodeBlock.GUNJALA_GONDI, Character.UnicodeBlock.of(0x11daf));
        assertEquals(Character.UnicodeBlock.MAKASAR, Character.UnicodeBlock.of(0x11ee0));
        assertEquals(Character.UnicodeBlock.MAKASAR, Character.UnicodeBlock.of(0x11eff));
        assertEquals(Character.UnicodeBlock.LISU_SUPPLEMENT, Character.UnicodeBlock.of(0x11fb0));
        assertEquals(Character.UnicodeBlock.LISU_SUPPLEMENT, Character.UnicodeBlock.of(0x11fbf));
        assertEquals(Character.UnicodeBlock.TAMIL_SUPPLEMENT, Character.UnicodeBlock.of(0x11fc0));
        assertEquals(Character.UnicodeBlock.TAMIL_SUPPLEMENT, Character.UnicodeBlock.of(0x11fff));
        assertEquals(Character.UnicodeBlock.EARLY_DYNASTIC_CUNEIFORM, Character.UnicodeBlock.of(0x12480));
        assertEquals(Character.UnicodeBlock.EARLY_DYNASTIC_CUNEIFORM, Character.UnicodeBlock.of(0x1254f));
        assertEquals(Character.UnicodeBlock.EGYPTIAN_HIEROGLYPH_FORMAT_CONTROLS, Character.UnicodeBlock.of(0x13430));
        assertEquals(Character.UnicodeBlock.EGYPTIAN_HIEROGLYPH_FORMAT_CONTROLS, Character.UnicodeBlock.of(0x1343f));
        assertEquals(Character.UnicodeBlock.ANATOLIAN_HIEROGLYPHS, Character.UnicodeBlock.of(0x14400));
        assertEquals(Character.UnicodeBlock.ANATOLIAN_HIEROGLYPHS, Character.UnicodeBlock.of(0x1467f));
        assertEquals(Character.UnicodeBlock.MRO, Character.UnicodeBlock.of(0x16a40));
        assertEquals(Character.UnicodeBlock.MRO, Character.UnicodeBlock.of(0x16a6f));
        assertEquals(Character.UnicodeBlock.BASSA_VAH, Character.UnicodeBlock.of(0x16ad0));
        assertEquals(Character.UnicodeBlock.BASSA_VAH, Character.UnicodeBlock.of(0x16aff));
        assertEquals(Character.UnicodeBlock.PAHAWH_HMONG, Character.UnicodeBlock.of(0x16b00));
        assertEquals(Character.UnicodeBlock.PAHAWH_HMONG, Character.UnicodeBlock.of(0x16b8f));
        assertEquals(Character.UnicodeBlock.MEDEFAIDRIN, Character.UnicodeBlock.of(0x16e40));
        assertEquals(Character.UnicodeBlock.MEDEFAIDRIN, Character.UnicodeBlock.of(0x16e9f));
        assertEquals(Character.UnicodeBlock.IDEOGRAPHIC_SYMBOLS_AND_PUNCTUATION, Character.UnicodeBlock.of(0x16fe0));
        assertEquals(Character.UnicodeBlock.IDEOGRAPHIC_SYMBOLS_AND_PUNCTUATION, Character.UnicodeBlock.of(0x16fff));
        assertEquals(Character.UnicodeBlock.TANGUT, Character.UnicodeBlock.of(0x17000));
        assertEquals(Character.UnicodeBlock.TANGUT, Character.UnicodeBlock.of(0x187ff));
        assertEquals(Character.UnicodeBlock.TANGUT_COMPONENTS, Character.UnicodeBlock.of(0x18800));
        assertEquals(Character.UnicodeBlock.TANGUT_COMPONENTS, Character.UnicodeBlock.of(0x18aff));
        assertEquals(Character.UnicodeBlock.KHITAN_SMALL_SCRIPT, Character.UnicodeBlock.of(0x18b00));
        assertEquals(Character.UnicodeBlock.KHITAN_SMALL_SCRIPT, Character.UnicodeBlock.of(0x18cff));
        assertEquals(Character.UnicodeBlock.TANGUT_SUPPLEMENT, Character.UnicodeBlock.of(0x18d00));
        assertEquals(Character.UnicodeBlock.TANGUT_SUPPLEMENT, Character.UnicodeBlock.of(0x18d8f));
        assertEquals(Character.UnicodeBlock.KANA_EXTENDED_A, Character.UnicodeBlock.of(0x1b100));
        assertEquals(Character.UnicodeBlock.KANA_EXTENDED_A, Character.UnicodeBlock.of(0x1b12f));
        assertEquals(Character.UnicodeBlock.SMALL_KANA_EXTENSION, Character.UnicodeBlock.of(0x1b130));
        assertEquals(Character.UnicodeBlock.SMALL_KANA_EXTENSION, Character.UnicodeBlock.of(0x1b16f));
        assertEquals(Character.UnicodeBlock.NUSHU, Character.UnicodeBlock.of(0x1b170));
        assertEquals(Character.UnicodeBlock.NUSHU, Character.UnicodeBlock.of(0x1b2ff));
        assertEquals(Character.UnicodeBlock.DUPLOYAN, Character.UnicodeBlock.of(0x1bc00));
        assertEquals(Character.UnicodeBlock.DUPLOYAN, Character.UnicodeBlock.of(0x1bc9f));
        assertEquals(Character.UnicodeBlock.SHORTHAND_FORMAT_CONTROLS, Character.UnicodeBlock.of(0x1bca0));
        assertEquals(Character.UnicodeBlock.SHORTHAND_FORMAT_CONTROLS, Character.UnicodeBlock.of(0x1bcaf));
        assertEquals(Character.UnicodeBlock.MAYAN_NUMERALS, Character.UnicodeBlock.of(0x1d2e0));
        assertEquals(Character.UnicodeBlock.MAYAN_NUMERALS, Character.UnicodeBlock.of(0x1d2ff));
        assertEquals(Character.UnicodeBlock.SUTTON_SIGNWRITING, Character.UnicodeBlock.of(0x1d800));
        assertEquals(Character.UnicodeBlock.SUTTON_SIGNWRITING, Character.UnicodeBlock.of(0x1daaf));
        assertEquals(Character.UnicodeBlock.GLAGOLITIC_SUPPLEMENT, Character.UnicodeBlock.of(0x1e000));
        assertEquals(Character.UnicodeBlock.GLAGOLITIC_SUPPLEMENT, Character.UnicodeBlock.of(0x1e02f));
        assertEquals(Character.UnicodeBlock.NYIAKENG_PUACHUE_HMONG, Character.UnicodeBlock.of(0x1e100));
        assertEquals(Character.UnicodeBlock.NYIAKENG_PUACHUE_HMONG, Character.UnicodeBlock.of(0x1e14f));
        assertEquals(Character.UnicodeBlock.WANCHO, Character.UnicodeBlock.of(0x1e2c0));
        assertEquals(Character.UnicodeBlock.WANCHO, Character.UnicodeBlock.of(0x1e2ff));
        assertEquals(Character.UnicodeBlock.MENDE_KIKAKUI, Character.UnicodeBlock.of(0x1e800));
        assertEquals(Character.UnicodeBlock.MENDE_KIKAKUI, Character.UnicodeBlock.of(0x1e8df));
        assertEquals(Character.UnicodeBlock.ADLAM, Character.UnicodeBlock.of(0x1e900));
        assertEquals(Character.UnicodeBlock.ADLAM, Character.UnicodeBlock.of(0x1e95f));
        assertEquals(Character.UnicodeBlock.INDIC_SIYAQ_NUMBERS, Character.UnicodeBlock.of(0x1ec70));
        assertEquals(Character.UnicodeBlock.INDIC_SIYAQ_NUMBERS, Character.UnicodeBlock.of(0x1ecbf));
        assertEquals(Character.UnicodeBlock.OTTOMAN_SIYAQ_NUMBERS, Character.UnicodeBlock.of(0x1ed00));
        assertEquals(Character.UnicodeBlock.OTTOMAN_SIYAQ_NUMBERS, Character.UnicodeBlock.of(0x1ed4f));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_SYMBOLS_AND_PICTOGRAPHS, Character.UnicodeBlock.of(0x1f300));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_SYMBOLS_AND_PICTOGRAPHS, Character.UnicodeBlock.of(0x1f5ff));
        assertEquals(Character.UnicodeBlock.ORNAMENTAL_DINGBATS, Character.UnicodeBlock.of(0x1f650));
        assertEquals(Character.UnicodeBlock.ORNAMENTAL_DINGBATS, Character.UnicodeBlock.of(0x1f67f));
        assertEquals(Character.UnicodeBlock.TRANSPORT_AND_MAP_SYMBOLS, Character.UnicodeBlock.of(0x1f680));
        assertEquals(Character.UnicodeBlock.TRANSPORT_AND_MAP_SYMBOLS, Character.UnicodeBlock.of(0x1f6ff));
        assertEquals(Character.UnicodeBlock.GEOMETRIC_SHAPES_EXTENDED, Character.UnicodeBlock.of(0x1f780));
        assertEquals(Character.UnicodeBlock.GEOMETRIC_SHAPES_EXTENDED, Character.UnicodeBlock.of(0x1f7ff));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTAL_ARROWS_C, Character.UnicodeBlock.of(0x1f800));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTAL_ARROWS_C, Character.UnicodeBlock.of(0x1f8ff));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTAL_SYMBOLS_AND_PICTOGRAPHS, Character.UnicodeBlock.of(0x1f900));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTAL_SYMBOLS_AND_PICTOGRAPHS, Character.UnicodeBlock.of(0x1f9ff));
        assertEquals(Character.UnicodeBlock.CHESS_SYMBOLS, Character.UnicodeBlock.of(0x1fa00));
        assertEquals(Character.UnicodeBlock.CHESS_SYMBOLS, Character.UnicodeBlock.of(0x1fa6f));
        assertEquals(Character.UnicodeBlock.SYMBOLS_AND_PICTOGRAPHS_EXTENDED_A, Character.UnicodeBlock.of(0x1fa70));
        assertEquals(Character.UnicodeBlock.SYMBOLS_AND_PICTOGRAPHS_EXTENDED_A, Character.UnicodeBlock.of(0x1faff));
        assertEquals(Character.UnicodeBlock.SYMBOLS_FOR_LEGACY_COMPUTING, Character.UnicodeBlock.of(0x1fb00));
        assertEquals(Character.UnicodeBlock.SYMBOLS_FOR_LEGACY_COMPUTING, Character.UnicodeBlock.of(0x1fbff));
        assertEquals(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_E, Character.UnicodeBlock.of(0x2b820));
        assertEquals(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_E, Character.UnicodeBlock.of(0x2ceaf));
        assertEquals(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_F, Character.UnicodeBlock.of(0x2ceb0));
        assertEquals(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_F, Character.UnicodeBlock.of(0x2ebef));
        assertEquals(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_G, Character.UnicodeBlock.of(0x30000));
        assertEquals(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_G, Character.UnicodeBlock.of(0x3134f));

        // Negative test: Test unassigned ranges
        for (UnassignedRange range : UNASSIGNED_RANGES) {
            assertEquals(
                    "Range start populated for " + range,
                    null,
                    Character.UnicodeBlock.of(range.start()));
            assertEquals(
                    "Range end populated for " + range,
                    null,
                    Character.UnicodeBlock.of(range.end()));
        }
    }

    public void test_ofIExceptions() {
        try {
            Character.UnicodeBlock.of(Character.MAX_CODE_POINT + 1);
            fail("No illegal argument exception");
        } catch (IllegalArgumentException e) {
        }
    }

    @SuppressWarnings("deprecation")
    public void test_forNameLjava_lang_String() {
        assertEquals(Character.UnicodeBlock.BASIC_LATIN, Character.UnicodeBlock.forName("BASIC_LATIN"));
        assertEquals(Character.UnicodeBlock.BASIC_LATIN, Character.UnicodeBlock.forName("Basic Latin"));
        assertEquals(Character.UnicodeBlock.BASIC_LATIN, Character.UnicodeBlock.forName("BasicLatin"));
        assertEquals(Character.UnicodeBlock.LATIN_1_SUPPLEMENT, Character.UnicodeBlock.forName("LATIN_1_SUPPLEMENT"));
        assertEquals(Character.UnicodeBlock.LATIN_1_SUPPLEMENT, Character.UnicodeBlock.forName("Latin-1 Supplement"));
        assertEquals(Character.UnicodeBlock.LATIN_1_SUPPLEMENT, Character.UnicodeBlock.forName("Latin-1Supplement"));
        assertEquals(Character.UnicodeBlock.LATIN_EXTENDED_A, Character.UnicodeBlock.forName("LATIN_EXTENDED_A"));
        assertEquals(Character.UnicodeBlock.LATIN_EXTENDED_A, Character.UnicodeBlock.forName("Latin Extended-A"));
        assertEquals(Character.UnicodeBlock.LATIN_EXTENDED_A, Character.UnicodeBlock.forName("LatinExtended-A"));
        assertEquals(Character.UnicodeBlock.LATIN_EXTENDED_B, Character.UnicodeBlock.forName("LATIN_EXTENDED_B"));
        assertEquals(Character.UnicodeBlock.LATIN_EXTENDED_B, Character.UnicodeBlock.forName("Latin Extended-B"));
        assertEquals(Character.UnicodeBlock.LATIN_EXTENDED_B, Character.UnicodeBlock.forName("LatinExtended-B"));
        assertEquals(Character.UnicodeBlock.IPA_EXTENSIONS, Character.UnicodeBlock.forName("IPA_EXTENSIONS"));
        assertEquals(Character.UnicodeBlock.IPA_EXTENSIONS, Character.UnicodeBlock.forName("IPA Extensions"));
        assertEquals(Character.UnicodeBlock.IPA_EXTENSIONS, Character.UnicodeBlock.forName("IPAExtensions"));
        assertEquals(Character.UnicodeBlock.SPACING_MODIFIER_LETTERS, Character.UnicodeBlock.forName("SPACING_MODIFIER_LETTERS"));
        assertEquals(Character.UnicodeBlock.SPACING_MODIFIER_LETTERS, Character.UnicodeBlock.forName("Spacing Modifier Letters"));
        assertEquals(Character.UnicodeBlock.SPACING_MODIFIER_LETTERS, Character.UnicodeBlock.forName("SpacingModifierLetters"));
        assertEquals(Character.UnicodeBlock.COMBINING_DIACRITICAL_MARKS, Character.UnicodeBlock.forName("COMBINING_DIACRITICAL_MARKS"));
        assertEquals(Character.UnicodeBlock.COMBINING_DIACRITICAL_MARKS, Character.UnicodeBlock.forName("Combining Diacritical Marks"));
        assertEquals(Character.UnicodeBlock.COMBINING_DIACRITICAL_MARKS, Character.UnicodeBlock.forName("CombiningDiacriticalMarks"));
        assertEquals(Character.UnicodeBlock.GREEK, Character.UnicodeBlock.forName("GREEK"));
        assertEquals(Character.UnicodeBlock.GREEK, Character.UnicodeBlock.forName("Greek and Coptic"));
        assertEquals(Character.UnicodeBlock.GREEK, Character.UnicodeBlock.forName("GreekandCoptic"));
        assertEquals(Character.UnicodeBlock.GREEK, Character.UnicodeBlock.forName("Greek"));
        assertEquals(Character.UnicodeBlock.GREEK, Character.UnicodeBlock.forName("Greek"));
        assertEquals(Character.UnicodeBlock.CYRILLIC, Character.UnicodeBlock.forName("CYRILLIC"));
        assertEquals(Character.UnicodeBlock.CYRILLIC_SUPPLEMENTARY, Character.UnicodeBlock.forName("CYRILLIC_SUPPLEMENTARY"));
        assertEquals(Character.UnicodeBlock.CYRILLIC_SUPPLEMENTARY, Character.UnicodeBlock.forName("Cyrillic Supplementary"));
        assertEquals(Character.UnicodeBlock.CYRILLIC_SUPPLEMENTARY, Character.UnicodeBlock.forName("CyrillicSupplementary"));
        assertEquals(Character.UnicodeBlock.ARMENIAN, Character.UnicodeBlock.forName("ARMENIAN"));
        assertEquals(Character.UnicodeBlock.HEBREW, Character.UnicodeBlock.forName("HEBREW"));
        assertEquals(Character.UnicodeBlock.ARABIC, Character.UnicodeBlock.forName("ARABIC"));
        assertEquals(Character.UnicodeBlock.SYRIAC, Character.UnicodeBlock.forName("SYRIAC"));
        assertEquals(Character.UnicodeBlock.THAANA, Character.UnicodeBlock.forName("THAANA"));
        assertEquals(Character.UnicodeBlock.DEVANAGARI, Character.UnicodeBlock.forName("DEVANAGARI"));
        assertEquals(Character.UnicodeBlock.BENGALI, Character.UnicodeBlock.forName("BENGALI"));
        assertEquals(Character.UnicodeBlock.GURMUKHI, Character.UnicodeBlock.forName("GURMUKHI"));
        assertEquals(Character.UnicodeBlock.GUJARATI, Character.UnicodeBlock.forName("GUJARATI"));
        assertEquals(Character.UnicodeBlock.ORIYA, Character.UnicodeBlock.forName("ORIYA"));
        assertEquals(Character.UnicodeBlock.TAMIL, Character.UnicodeBlock.forName("TAMIL"));
        assertEquals(Character.UnicodeBlock.TELUGU, Character.UnicodeBlock.forName("TELUGU"));
        assertEquals(Character.UnicodeBlock.KANNADA, Character.UnicodeBlock.forName("KANNADA"));
        assertEquals(Character.UnicodeBlock.MALAYALAM, Character.UnicodeBlock.forName("MALAYALAM"));
        assertEquals(Character.UnicodeBlock.SINHALA, Character.UnicodeBlock.forName("SINHALA"));
        assertEquals(Character.UnicodeBlock.THAI, Character.UnicodeBlock.forName("THAI"));
        assertEquals(Character.UnicodeBlock.LAO, Character.UnicodeBlock.forName("LAO"));
        assertEquals(Character.UnicodeBlock.TIBETAN, Character.UnicodeBlock.forName("TIBETAN"));
        assertEquals(Character.UnicodeBlock.MYANMAR, Character.UnicodeBlock.forName("MYANMAR"));
        assertEquals(Character.UnicodeBlock.GEORGIAN, Character.UnicodeBlock.forName("GEORGIAN"));
        assertEquals(Character.UnicodeBlock.HANGUL_JAMO, Character.UnicodeBlock.forName("HANGUL_JAMO"));
        assertEquals(Character.UnicodeBlock.HANGUL_JAMO, Character.UnicodeBlock.forName("Hangul Jamo"));
        assertEquals(Character.UnicodeBlock.HANGUL_JAMO, Character.UnicodeBlock.forName("HangulJamo"));
        assertEquals(Character.UnicodeBlock.ETHIOPIC, Character.UnicodeBlock.forName("ETHIOPIC"));
        assertEquals(Character.UnicodeBlock.CHEROKEE, Character.UnicodeBlock.forName("CHEROKEE"));
        assertEquals(Character.UnicodeBlock.UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS, Character.UnicodeBlock.forName("UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS"));
        assertEquals(Character.UnicodeBlock.UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS, Character.UnicodeBlock.forName("Unified Canadian Aboriginal Syllabics"));
        assertEquals(Character.UnicodeBlock.UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS, Character.UnicodeBlock.forName("UnifiedCanadianAboriginalSyllabics"));
        assertEquals(Character.UnicodeBlock.OGHAM, Character.UnicodeBlock.forName("OGHAM"));
        assertEquals(Character.UnicodeBlock.RUNIC, Character.UnicodeBlock.forName("RUNIC"));
        assertEquals(Character.UnicodeBlock.TAGALOG, Character.UnicodeBlock.forName("TAGALOG"));
        assertEquals(Character.UnicodeBlock.HANUNOO, Character.UnicodeBlock.forName("HANUNOO"));
        assertEquals(Character.UnicodeBlock.BUHID, Character.UnicodeBlock.forName("BUHID"));
        assertEquals(Character.UnicodeBlock.TAGBANWA, Character.UnicodeBlock.forName("TAGBANWA"));
        assertEquals(Character.UnicodeBlock.KHMER, Character.UnicodeBlock.forName("KHMER"));
        assertEquals(Character.UnicodeBlock.MONGOLIAN, Character.UnicodeBlock.forName("MONGOLIAN"));
        assertEquals(Character.UnicodeBlock.LIMBU, Character.UnicodeBlock.forName("LIMBU"));
        assertEquals(Character.UnicodeBlock.TAI_LE, Character.UnicodeBlock.forName("TAI_LE"));
        assertEquals(Character.UnicodeBlock.TAI_LE, Character.UnicodeBlock.forName("Tai Le"));
        assertEquals(Character.UnicodeBlock.TAI_LE, Character.UnicodeBlock.forName("TaiLe"));
        assertEquals(Character.UnicodeBlock.KHMER_SYMBOLS, Character.UnicodeBlock.forName("KHMER_SYMBOLS"));
        assertEquals(Character.UnicodeBlock.KHMER_SYMBOLS, Character.UnicodeBlock.forName("Khmer Symbols"));
        assertEquals(Character.UnicodeBlock.KHMER_SYMBOLS, Character.UnicodeBlock.forName("KhmerSymbols"));
        assertEquals(Character.UnicodeBlock.PHONETIC_EXTENSIONS, Character.UnicodeBlock.forName("PHONETIC_EXTENSIONS"));
        assertEquals(Character.UnicodeBlock.PHONETIC_EXTENSIONS, Character.UnicodeBlock.forName("Phonetic Extensions"));
        assertEquals(Character.UnicodeBlock.PHONETIC_EXTENSIONS, Character.UnicodeBlock.forName("PhoneticExtensions"));
        assertEquals(Character.UnicodeBlock.LATIN_EXTENDED_ADDITIONAL, Character.UnicodeBlock.forName("LATIN_EXTENDED_ADDITIONAL"));
        assertEquals(Character.UnicodeBlock.LATIN_EXTENDED_ADDITIONAL, Character.UnicodeBlock.forName("Latin Extended Additional"));
        assertEquals(Character.UnicodeBlock.LATIN_EXTENDED_ADDITIONAL, Character.UnicodeBlock.forName("LatinExtendedAdditional"));
        assertEquals(Character.UnicodeBlock.GREEK_EXTENDED, Character.UnicodeBlock.forName("GREEK_EXTENDED"));
        assertEquals(Character.UnicodeBlock.GREEK_EXTENDED, Character.UnicodeBlock.forName("Greek Extended"));
        assertEquals(Character.UnicodeBlock.GREEK_EXTENDED, Character.UnicodeBlock.forName("GreekExtended"));
        assertEquals(Character.UnicodeBlock.GENERAL_PUNCTUATION, Character.UnicodeBlock.forName("GENERAL_PUNCTUATION"));
        assertEquals(Character.UnicodeBlock.GENERAL_PUNCTUATION, Character.UnicodeBlock.forName("General Punctuation"));
        assertEquals(Character.UnicodeBlock.GENERAL_PUNCTUATION, Character.UnicodeBlock.forName("GeneralPunctuation"));
        assertEquals(Character.UnicodeBlock.SUPERSCRIPTS_AND_SUBSCRIPTS, Character.UnicodeBlock.forName("SUPERSCRIPTS_AND_SUBSCRIPTS"));
        assertEquals(Character.UnicodeBlock.SUPERSCRIPTS_AND_SUBSCRIPTS, Character.UnicodeBlock.forName("Superscripts and Subscripts"));
        assertEquals(Character.UnicodeBlock.SUPERSCRIPTS_AND_SUBSCRIPTS, Character.UnicodeBlock.forName("SuperscriptsandSubscripts"));
        assertEquals(Character.UnicodeBlock.CURRENCY_SYMBOLS, Character.UnicodeBlock.forName("CURRENCY_SYMBOLS"));
        assertEquals(Character.UnicodeBlock.CURRENCY_SYMBOLS, Character.UnicodeBlock.forName("Currency Symbols"));
        assertEquals(Character.UnicodeBlock.CURRENCY_SYMBOLS, Character.UnicodeBlock.forName("CurrencySymbols"));
        assertEquals(Character.UnicodeBlock.COMBINING_MARKS_FOR_SYMBOLS, Character.UnicodeBlock.forName("COMBINING_MARKS_FOR_SYMBOLS"));
        assertEquals(Character.UnicodeBlock.COMBINING_MARKS_FOR_SYMBOLS, Character.UnicodeBlock.forName("Combining Diacritical Marks for Symbols"));
        assertEquals(Character.UnicodeBlock.COMBINING_MARKS_FOR_SYMBOLS, Character.UnicodeBlock.forName("CombiningDiacriticalMarksforSymbols"));
        assertEquals(Character.UnicodeBlock.COMBINING_MARKS_FOR_SYMBOLS, Character.UnicodeBlock.forName("Combining Marks for Symbols"));
        assertEquals(Character.UnicodeBlock.COMBINING_MARKS_FOR_SYMBOLS, Character.UnicodeBlock.forName("CombiningMarksforSymbols"));
        assertEquals(Character.UnicodeBlock.LETTERLIKE_SYMBOLS, Character.UnicodeBlock.forName("LETTERLIKE_SYMBOLS"));
        assertEquals(Character.UnicodeBlock.LETTERLIKE_SYMBOLS, Character.UnicodeBlock.forName("Letterlike Symbols"));
        assertEquals(Character.UnicodeBlock.LETTERLIKE_SYMBOLS, Character.UnicodeBlock.forName("LetterlikeSymbols"));
        assertEquals(Character.UnicodeBlock.NUMBER_FORMS, Character.UnicodeBlock.forName("NUMBER_FORMS"));
        assertEquals(Character.UnicodeBlock.NUMBER_FORMS, Character.UnicodeBlock.forName("Number Forms"));
        assertEquals(Character.UnicodeBlock.NUMBER_FORMS, Character.UnicodeBlock.forName("NumberForms"));
        assertEquals(Character.UnicodeBlock.ARROWS, Character.UnicodeBlock.forName("ARROWS"));
        assertEquals(Character.UnicodeBlock.MATHEMATICAL_OPERATORS, Character.UnicodeBlock.forName("MATHEMATICAL_OPERATORS"));
        assertEquals(Character.UnicodeBlock.MATHEMATICAL_OPERATORS, Character.UnicodeBlock.forName("Mathematical Operators"));
        assertEquals(Character.UnicodeBlock.MATHEMATICAL_OPERATORS, Character.UnicodeBlock.forName("MathematicalOperators"));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_TECHNICAL, Character.UnicodeBlock.forName("MISCELLANEOUS_TECHNICAL"));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_TECHNICAL, Character.UnicodeBlock.forName("Miscellaneous Technical"));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_TECHNICAL, Character.UnicodeBlock.forName("MiscellaneousTechnical"));
        assertEquals(Character.UnicodeBlock.CONTROL_PICTURES, Character.UnicodeBlock.forName("CONTROL_PICTURES"));
        assertEquals(Character.UnicodeBlock.CONTROL_PICTURES, Character.UnicodeBlock.forName("Control Pictures"));
        assertEquals(Character.UnicodeBlock.CONTROL_PICTURES, Character.UnicodeBlock.forName("ControlPictures"));
        assertEquals(Character.UnicodeBlock.OPTICAL_CHARACTER_RECOGNITION, Character.UnicodeBlock.forName("OPTICAL_CHARACTER_RECOGNITION"));
        assertEquals(Character.UnicodeBlock.OPTICAL_CHARACTER_RECOGNITION, Character.UnicodeBlock.forName("Optical Character Recognition"));
        assertEquals(Character.UnicodeBlock.OPTICAL_CHARACTER_RECOGNITION, Character.UnicodeBlock.forName("OpticalCharacterRecognition"));
        assertEquals(Character.UnicodeBlock.ENCLOSED_ALPHANUMERICS, Character.UnicodeBlock.forName("ENCLOSED_ALPHANUMERICS"));
        assertEquals(Character.UnicodeBlock.ENCLOSED_ALPHANUMERICS, Character.UnicodeBlock.forName("Enclosed Alphanumerics"));
        assertEquals(Character.UnicodeBlock.ENCLOSED_ALPHANUMERICS, Character.UnicodeBlock.forName("EnclosedAlphanumerics"));
        assertEquals(Character.UnicodeBlock.BOX_DRAWING, Character.UnicodeBlock.forName("BOX_DRAWING"));
        assertEquals(Character.UnicodeBlock.BOX_DRAWING, Character.UnicodeBlock.forName("Box Drawing"));
        assertEquals(Character.UnicodeBlock.BOX_DRAWING, Character.UnicodeBlock.forName("BoxDrawing"));
        assertEquals(Character.UnicodeBlock.BLOCK_ELEMENTS, Character.UnicodeBlock.forName("BLOCK_ELEMENTS"));
        assertEquals(Character.UnicodeBlock.BLOCK_ELEMENTS, Character.UnicodeBlock.forName("Block Elements"));
        assertEquals(Character.UnicodeBlock.BLOCK_ELEMENTS, Character.UnicodeBlock.forName("BlockElements"));
        assertEquals(Character.UnicodeBlock.GEOMETRIC_SHAPES, Character.UnicodeBlock.forName("GEOMETRIC_SHAPES"));
        assertEquals(Character.UnicodeBlock.GEOMETRIC_SHAPES, Character.UnicodeBlock.forName("Geometric Shapes"));
        assertEquals(Character.UnicodeBlock.GEOMETRIC_SHAPES, Character.UnicodeBlock.forName("GeometricShapes"));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_SYMBOLS, Character.UnicodeBlock.forName("MISCELLANEOUS_SYMBOLS"));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_SYMBOLS, Character.UnicodeBlock.forName("Miscellaneous Symbols"));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_SYMBOLS, Character.UnicodeBlock.forName("MiscellaneousSymbols"));
        assertEquals(Character.UnicodeBlock.DINGBATS, Character.UnicodeBlock.forName("DINGBATS"));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A, Character.UnicodeBlock.forName("MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A"));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A, Character.UnicodeBlock.forName("Miscellaneous Mathematical Symbols-A"));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A, Character.UnicodeBlock.forName("MiscellaneousMathematicalSymbols-A"));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTAL_ARROWS_A, Character.UnicodeBlock.forName("SUPPLEMENTAL_ARROWS_A"));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTAL_ARROWS_A, Character.UnicodeBlock.forName("Supplemental Arrows-A"));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTAL_ARROWS_A, Character.UnicodeBlock.forName("SupplementalArrows-A"));
        assertEquals(Character.UnicodeBlock.BRAILLE_PATTERNS, Character.UnicodeBlock.forName("BRAILLE_PATTERNS"));
        assertEquals(Character.UnicodeBlock.BRAILLE_PATTERNS, Character.UnicodeBlock.forName("Braille Patterns"));
        assertEquals(Character.UnicodeBlock.BRAILLE_PATTERNS, Character.UnicodeBlock.forName("BraillePatterns"));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTAL_ARROWS_B, Character.UnicodeBlock.forName("SUPPLEMENTAL_ARROWS_B"));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTAL_ARROWS_B, Character.UnicodeBlock.forName("Supplemental Arrows-B"));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTAL_ARROWS_B, Character.UnicodeBlock.forName("SupplementalArrows-B"));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B, Character.UnicodeBlock.forName("MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B"));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B, Character.UnicodeBlock.forName("Miscellaneous Mathematical Symbols-B"));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B, Character.UnicodeBlock.forName("MiscellaneousMathematicalSymbols-B"));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTAL_MATHEMATICAL_OPERATORS, Character.UnicodeBlock.forName("SUPPLEMENTAL_MATHEMATICAL_OPERATORS"));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTAL_MATHEMATICAL_OPERATORS, Character.UnicodeBlock.forName("Supplemental Mathematical Operators"));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTAL_MATHEMATICAL_OPERATORS, Character.UnicodeBlock.forName("SupplementalMathematicalOperators"));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_SYMBOLS_AND_ARROWS, Character.UnicodeBlock.forName("MISCELLANEOUS_SYMBOLS_AND_ARROWS"));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_SYMBOLS_AND_ARROWS, Character.UnicodeBlock.forName("Miscellaneous Symbols and Arrows"));
        assertEquals(Character.UnicodeBlock.MISCELLANEOUS_SYMBOLS_AND_ARROWS, Character.UnicodeBlock.forName("MiscellaneousSymbolsandArrows"));
        assertEquals(Character.UnicodeBlock.CJK_RADICALS_SUPPLEMENT, Character.UnicodeBlock.forName("CJK_RADICALS_SUPPLEMENT"));
        assertEquals(Character.UnicodeBlock.CJK_RADICALS_SUPPLEMENT, Character.UnicodeBlock.forName("CJK Radicals Supplement"));
        assertEquals(Character.UnicodeBlock.CJK_RADICALS_SUPPLEMENT, Character.UnicodeBlock.forName("CJKRadicalsSupplement"));
        assertEquals(Character.UnicodeBlock.KANGXI_RADICALS, Character.UnicodeBlock.forName("KANGXI_RADICALS"));
        assertEquals(Character.UnicodeBlock.KANGXI_RADICALS, Character.UnicodeBlock.forName("Kangxi Radicals"));
        assertEquals(Character.UnicodeBlock.KANGXI_RADICALS, Character.UnicodeBlock.forName("KangxiRadicals"));
        assertEquals(Character.UnicodeBlock.IDEOGRAPHIC_DESCRIPTION_CHARACTERS, Character.UnicodeBlock.forName("IDEOGRAPHIC_DESCRIPTION_CHARACTERS"));
        assertEquals(Character.UnicodeBlock.IDEOGRAPHIC_DESCRIPTION_CHARACTERS, Character.UnicodeBlock.forName("Ideographic Description Characters"));
        assertEquals(Character.UnicodeBlock.IDEOGRAPHIC_DESCRIPTION_CHARACTERS, Character.UnicodeBlock.forName("IdeographicDescriptionCharacters"));
        assertEquals(Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION, Character.UnicodeBlock.forName("CJK_SYMBOLS_AND_PUNCTUATION"));
        assertEquals(Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION, Character.UnicodeBlock.forName("CJK Symbols and Punctuation"));
        assertEquals(Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION, Character.UnicodeBlock.forName("CJKSymbolsandPunctuation"));
        assertEquals(Character.UnicodeBlock.HIRAGANA, Character.UnicodeBlock.forName("HIRAGANA"));
        assertEquals(Character.UnicodeBlock.KATAKANA, Character.UnicodeBlock.forName("KATAKANA"));
        assertEquals(Character.UnicodeBlock.BOPOMOFO, Character.UnicodeBlock.forName("BOPOMOFO"));
        assertEquals(Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO, Character.UnicodeBlock.forName("HANGUL_COMPATIBILITY_JAMO"));
        assertEquals(Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO, Character.UnicodeBlock.forName("Hangul Compatibility Jamo"));
        assertEquals(Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO, Character.UnicodeBlock.forName("HangulCompatibilityJamo"));
        assertEquals(Character.UnicodeBlock.KANBUN, Character.UnicodeBlock.forName("KANBUN"));
        assertEquals(Character.UnicodeBlock.BOPOMOFO_EXTENDED, Character.UnicodeBlock.forName("BOPOMOFO_EXTENDED"));
        assertEquals(Character.UnicodeBlock.BOPOMOFO_EXTENDED, Character.UnicodeBlock.forName("Bopomofo Extended"));
        assertEquals(Character.UnicodeBlock.BOPOMOFO_EXTENDED, Character.UnicodeBlock.forName("BopomofoExtended"));
        assertEquals(Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS, Character.UnicodeBlock.forName("KATAKANA_PHONETIC_EXTENSIONS"));
        assertEquals(Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS, Character.UnicodeBlock.forName("Katakana Phonetic Extensions"));
        assertEquals(Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS, Character.UnicodeBlock.forName("KatakanaPhoneticExtensions"));
        assertEquals(Character.UnicodeBlock.ENCLOSED_CJK_LETTERS_AND_MONTHS, Character.UnicodeBlock.forName("ENCLOSED_CJK_LETTERS_AND_MONTHS"));
        assertEquals(Character.UnicodeBlock.ENCLOSED_CJK_LETTERS_AND_MONTHS, Character.UnicodeBlock.forName("Enclosed CJK Letters and Months"));
        assertEquals(Character.UnicodeBlock.ENCLOSED_CJK_LETTERS_AND_MONTHS, Character.UnicodeBlock.forName("EnclosedCJKLettersandMonths"));
        assertEquals(Character.UnicodeBlock.CJK_COMPATIBILITY, Character.UnicodeBlock.forName("CJK_COMPATIBILITY"));
        assertEquals(Character.UnicodeBlock.CJK_COMPATIBILITY, Character.UnicodeBlock.forName("CJK Compatibility"));
        assertEquals(Character.UnicodeBlock.CJK_COMPATIBILITY, Character.UnicodeBlock.forName("CJKCompatibility"));
        assertEquals(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A, Character.UnicodeBlock.forName("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A"));
        assertEquals(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A, Character.UnicodeBlock.forName("CJK Unified Ideographs Extension A"));
        assertEquals(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A, Character.UnicodeBlock.forName("CJKUnifiedIdeographsExtensionA"));
        assertEquals(Character.UnicodeBlock.YIJING_HEXAGRAM_SYMBOLS, Character.UnicodeBlock.forName("YIJING_HEXAGRAM_SYMBOLS"));
        assertEquals(Character.UnicodeBlock.YIJING_HEXAGRAM_SYMBOLS, Character.UnicodeBlock.forName("Yijing Hexagram Symbols"));
        assertEquals(Character.UnicodeBlock.YIJING_HEXAGRAM_SYMBOLS, Character.UnicodeBlock.forName("YijingHexagramSymbols"));
        assertEquals(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS, Character.UnicodeBlock.forName("CJK_UNIFIED_IDEOGRAPHS"));
        assertEquals(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS, Character.UnicodeBlock.forName("CJK Unified Ideographs"));
        assertEquals(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS, Character.UnicodeBlock.forName("CJKUnifiedIdeographs"));
        assertEquals(Character.UnicodeBlock.YI_SYLLABLES, Character.UnicodeBlock.forName("YI_SYLLABLES"));
        assertEquals(Character.UnicodeBlock.YI_SYLLABLES, Character.UnicodeBlock.forName("Yi Syllables"));
        assertEquals(Character.UnicodeBlock.YI_SYLLABLES, Character.UnicodeBlock.forName("YiSyllables"));
        assertEquals(Character.UnicodeBlock.YI_RADICALS, Character.UnicodeBlock.forName("YI_RADICALS"));
        assertEquals(Character.UnicodeBlock.YI_RADICALS, Character.UnicodeBlock.forName("Yi Radicals"));
        assertEquals(Character.UnicodeBlock.YI_RADICALS, Character.UnicodeBlock.forName("YiRadicals"));
        assertEquals(Character.UnicodeBlock.HANGUL_SYLLABLES, Character.UnicodeBlock.forName("HANGUL_SYLLABLES"));
        assertEquals(Character.UnicodeBlock.HANGUL_SYLLABLES, Character.UnicodeBlock.forName("Hangul Syllables"));
        assertEquals(Character.UnicodeBlock.HANGUL_SYLLABLES, Character.UnicodeBlock.forName("HangulSyllables"));
        assertEquals(Character.UnicodeBlock.HIGH_SURROGATES, Character.UnicodeBlock.forName("HIGH_SURROGATES"));
        assertEquals(Character.UnicodeBlock.HIGH_SURROGATES, Character.UnicodeBlock.forName("High Surrogates"));
        assertEquals(Character.UnicodeBlock.HIGH_SURROGATES, Character.UnicodeBlock.forName("HighSurrogates"));
        assertEquals(Character.UnicodeBlock.HIGH_PRIVATE_USE_SURROGATES, Character.UnicodeBlock.forName("HIGH_PRIVATE_USE_SURROGATES"));
        assertEquals(Character.UnicodeBlock.HIGH_PRIVATE_USE_SURROGATES, Character.UnicodeBlock.forName("High Private Use Surrogates"));
        assertEquals(Character.UnicodeBlock.HIGH_PRIVATE_USE_SURROGATES, Character.UnicodeBlock.forName("HighPrivateUseSurrogates"));
        assertEquals(Character.UnicodeBlock.LOW_SURROGATES, Character.UnicodeBlock.forName("LOW_SURROGATES"));
        assertEquals(Character.UnicodeBlock.LOW_SURROGATES, Character.UnicodeBlock.forName("Low Surrogates"));
        assertEquals(Character.UnicodeBlock.LOW_SURROGATES, Character.UnicodeBlock.forName("LowSurrogates"));
        assertEquals(Character.UnicodeBlock.PRIVATE_USE_AREA, Character.UnicodeBlock.forName("PRIVATE_USE_AREA"));
        assertEquals(Character.UnicodeBlock.PRIVATE_USE_AREA, Character.UnicodeBlock.forName("Private Use Area"));
        assertEquals(Character.UnicodeBlock.PRIVATE_USE_AREA, Character.UnicodeBlock.forName("PrivateUseArea"));
        assertEquals(Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS, Character.UnicodeBlock.forName("CJK_COMPATIBILITY_IDEOGRAPHS"));
        assertEquals(Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS, Character.UnicodeBlock.forName("CJK Compatibility Ideographs"));
        assertEquals(Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS, Character.UnicodeBlock.forName("CJKCompatibilityIdeographs"));
        assertEquals(Character.UnicodeBlock.ALPHABETIC_PRESENTATION_FORMS, Character.UnicodeBlock.forName("ALPHABETIC_PRESENTATION_FORMS"));
        assertEquals(Character.UnicodeBlock.ALPHABETIC_PRESENTATION_FORMS, Character.UnicodeBlock.forName("Alphabetic Presentation Forms"));
        assertEquals(Character.UnicodeBlock.ALPHABETIC_PRESENTATION_FORMS, Character.UnicodeBlock.forName("AlphabeticPresentationForms"));
        assertEquals(Character.UnicodeBlock.ARABIC_PRESENTATION_FORMS_A, Character.UnicodeBlock.forName("ARABIC_PRESENTATION_FORMS_A"));
        assertEquals(Character.UnicodeBlock.ARABIC_PRESENTATION_FORMS_A, Character.UnicodeBlock.forName("Arabic Presentation Forms-A"));
        assertEquals(Character.UnicodeBlock.ARABIC_PRESENTATION_FORMS_A, Character.UnicodeBlock.forName("ArabicPresentationForms-A"));
        assertEquals(Character.UnicodeBlock.VARIATION_SELECTORS, Character.UnicodeBlock.forName("VARIATION_SELECTORS"));
        assertEquals(Character.UnicodeBlock.VARIATION_SELECTORS, Character.UnicodeBlock.forName("Variation Selectors"));
        assertEquals(Character.UnicodeBlock.VARIATION_SELECTORS, Character.UnicodeBlock.forName("VariationSelectors"));
        assertEquals(Character.UnicodeBlock.COMBINING_HALF_MARKS, Character.UnicodeBlock.forName("COMBINING_HALF_MARKS"));
        assertEquals(Character.UnicodeBlock.COMBINING_HALF_MARKS, Character.UnicodeBlock.forName("Combining Half Marks"));
        assertEquals(Character.UnicodeBlock.COMBINING_HALF_MARKS, Character.UnicodeBlock.forName("CombiningHalfMarks"));
        assertEquals(Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS, Character.UnicodeBlock.forName("CJK_COMPATIBILITY_FORMS"));
        assertEquals(Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS, Character.UnicodeBlock.forName("CJK Compatibility Forms"));
        assertEquals(Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS, Character.UnicodeBlock.forName("CJKCompatibilityForms"));
        assertEquals(Character.UnicodeBlock.SMALL_FORM_VARIANTS, Character.UnicodeBlock.forName("SMALL_FORM_VARIANTS"));
        assertEquals(Character.UnicodeBlock.SMALL_FORM_VARIANTS, Character.UnicodeBlock.forName("Small Form Variants"));
        assertEquals(Character.UnicodeBlock.SMALL_FORM_VARIANTS, Character.UnicodeBlock.forName("SmallFormVariants"));
        assertEquals(Character.UnicodeBlock.ARABIC_PRESENTATION_FORMS_B, Character.UnicodeBlock.forName("ARABIC_PRESENTATION_FORMS_B"));
        assertEquals(Character.UnicodeBlock.ARABIC_PRESENTATION_FORMS_B, Character.UnicodeBlock.forName("Arabic Presentation Forms-B"));
        assertEquals(Character.UnicodeBlock.ARABIC_PRESENTATION_FORMS_B, Character.UnicodeBlock.forName("ArabicPresentationForms-B"));
        assertEquals(Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS, Character.UnicodeBlock.forName("HALFWIDTH_AND_FULLWIDTH_FORMS"));
        assertEquals(Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS, Character.UnicodeBlock.forName("Halfwidth and Fullwidth Forms"));
        assertEquals(Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS, Character.UnicodeBlock.forName("HalfwidthandFullwidthForms"));
        assertEquals(Character.UnicodeBlock.SPECIALS, Character.UnicodeBlock.forName("SPECIALS"));
        assertEquals(Character.UnicodeBlock.LINEAR_B_SYLLABARY, Character.UnicodeBlock.forName("LINEAR_B_SYLLABARY"));
        assertEquals(Character.UnicodeBlock.LINEAR_B_SYLLABARY, Character.UnicodeBlock.forName("Linear B Syllabary"));
        assertEquals(Character.UnicodeBlock.LINEAR_B_SYLLABARY, Character.UnicodeBlock.forName("LinearBSyllabary"));
        assertEquals(Character.UnicodeBlock.LINEAR_B_IDEOGRAMS, Character.UnicodeBlock.forName("LINEAR_B_IDEOGRAMS"));
        assertEquals(Character.UnicodeBlock.LINEAR_B_IDEOGRAMS, Character.UnicodeBlock.forName("Linear B Ideograms"));
        assertEquals(Character.UnicodeBlock.LINEAR_B_IDEOGRAMS, Character.UnicodeBlock.forName("LinearBIdeograms"));
        assertEquals(Character.UnicodeBlock.AEGEAN_NUMBERS, Character.UnicodeBlock.forName("AEGEAN_NUMBERS"));
        assertEquals(Character.UnicodeBlock.AEGEAN_NUMBERS, Character.UnicodeBlock.forName("Aegean Numbers"));
        assertEquals(Character.UnicodeBlock.AEGEAN_NUMBERS, Character.UnicodeBlock.forName("AegeanNumbers"));
        assertEquals(Character.UnicodeBlock.OLD_ITALIC, Character.UnicodeBlock.forName("OLD_ITALIC"));
        assertEquals(Character.UnicodeBlock.OLD_ITALIC, Character.UnicodeBlock.forName("Old Italic"));
        assertEquals(Character.UnicodeBlock.OLD_ITALIC, Character.UnicodeBlock.forName("OldItalic"));
        assertEquals(Character.UnicodeBlock.GOTHIC, Character.UnicodeBlock.forName("GOTHIC"));
        assertEquals(Character.UnicodeBlock.UGARITIC, Character.UnicodeBlock.forName("UGARITIC"));
        assertEquals(Character.UnicodeBlock.DESERET, Character.UnicodeBlock.forName("DESERET"));
        assertEquals(Character.UnicodeBlock.SHAVIAN, Character.UnicodeBlock.forName("SHAVIAN"));
        assertEquals(Character.UnicodeBlock.OSMANYA, Character.UnicodeBlock.forName("OSMANYA"));
        assertEquals(Character.UnicodeBlock.CYPRIOT_SYLLABARY, Character.UnicodeBlock.forName("CYPRIOT_SYLLABARY"));
        assertEquals(Character.UnicodeBlock.CYPRIOT_SYLLABARY, Character.UnicodeBlock.forName("Cypriot Syllabary"));
        assertEquals(Character.UnicodeBlock.CYPRIOT_SYLLABARY, Character.UnicodeBlock.forName("CypriotSyllabary"));
        assertEquals(Character.UnicodeBlock.BYZANTINE_MUSICAL_SYMBOLS, Character.UnicodeBlock.forName("BYZANTINE_MUSICAL_SYMBOLS"));
        assertEquals(Character.UnicodeBlock.BYZANTINE_MUSICAL_SYMBOLS, Character.UnicodeBlock.forName("Byzantine Musical Symbols"));
        assertEquals(Character.UnicodeBlock.BYZANTINE_MUSICAL_SYMBOLS, Character.UnicodeBlock.forName("ByzantineMusicalSymbols"));
        assertEquals(Character.UnicodeBlock.MUSICAL_SYMBOLS, Character.UnicodeBlock.forName("MUSICAL_SYMBOLS"));
        assertEquals(Character.UnicodeBlock.MUSICAL_SYMBOLS, Character.UnicodeBlock.forName("Musical Symbols"));
        assertEquals(Character.UnicodeBlock.MUSICAL_SYMBOLS, Character.UnicodeBlock.forName("MusicalSymbols"));
        assertEquals(Character.UnicodeBlock.TAI_XUAN_JING_SYMBOLS, Character.UnicodeBlock.forName("TAI_XUAN_JING_SYMBOLS"));
        assertEquals(Character.UnicodeBlock.TAI_XUAN_JING_SYMBOLS, Character.UnicodeBlock.forName("Tai Xuan Jing Symbols"));
        assertEquals(Character.UnicodeBlock.TAI_XUAN_JING_SYMBOLS, Character.UnicodeBlock.forName("TaiXuanJingSymbols"));
        assertEquals(Character.UnicodeBlock.MATHEMATICAL_ALPHANUMERIC_SYMBOLS, Character.UnicodeBlock.forName("MATHEMATICAL_ALPHANUMERIC_SYMBOLS"));
        assertEquals(Character.UnicodeBlock.MATHEMATICAL_ALPHANUMERIC_SYMBOLS, Character.UnicodeBlock.forName("Mathematical Alphanumeric Symbols"));
        assertEquals(Character.UnicodeBlock.MATHEMATICAL_ALPHANUMERIC_SYMBOLS, Character.UnicodeBlock.forName("MathematicalAlphanumericSymbols"));
        assertEquals(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B, Character.UnicodeBlock.forName("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B"));
        assertEquals(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B, Character.UnicodeBlock.forName("CJK Unified Ideographs Extension B"));
        assertEquals(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B, Character.UnicodeBlock.forName("CJKUnifiedIdeographsExtensionB"));
        assertEquals(Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT, Character.UnicodeBlock.forName("CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT"));
        assertEquals(Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT, Character.UnicodeBlock.forName("CJK Compatibility Ideographs Supplement"));
        assertEquals(Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT, Character.UnicodeBlock.forName("CJKCompatibilityIdeographsSupplement"));
        assertEquals(Character.UnicodeBlock.TAGS, Character.UnicodeBlock.forName("TAGS"));
        assertEquals(Character.UnicodeBlock.VARIATION_SELECTORS_SUPPLEMENT, Character.UnicodeBlock.forName("VARIATION_SELECTORS_SUPPLEMENT"));
        assertEquals(Character.UnicodeBlock.VARIATION_SELECTORS_SUPPLEMENT, Character.UnicodeBlock.forName("Variation Selectors Supplement"));
        assertEquals(Character.UnicodeBlock.VARIATION_SELECTORS_SUPPLEMENT, Character.UnicodeBlock.forName("VariationSelectorsSupplement"));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTARY_PRIVATE_USE_AREA_A, Character.UnicodeBlock.forName("SUPPLEMENTARY_PRIVATE_USE_AREA_A"));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTARY_PRIVATE_USE_AREA_A, Character.UnicodeBlock.forName("Supplementary Private Use Area-A"));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTARY_PRIVATE_USE_AREA_A, Character.UnicodeBlock.forName("SupplementaryPrivateUseArea-A"));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTARY_PRIVATE_USE_AREA_B, Character.UnicodeBlock.forName("SUPPLEMENTARY_PRIVATE_USE_AREA_B"));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTARY_PRIVATE_USE_AREA_B, Character.UnicodeBlock.forName("Supplementary Private Use Area-B"));
        assertEquals(Character.UnicodeBlock.SUPPLEMENTARY_PRIVATE_USE_AREA_B, Character.UnicodeBlock.forName("SupplementaryPrivateUseArea-B"));

        // Blocks added in 1.8
        assertEquals(Character.UnicodeBlock.ARABIC_EXTENDED_A, Character.UnicodeBlock.forName("ARABIC_EXTENDED_A"));
        assertEquals(Character.UnicodeBlock.ARABIC_EXTENDED_A, Character.UnicodeBlock.forName("arabic extended-A"));
        assertEquals(Character.UnicodeBlock.ARABIC_EXTENDED_A, Character.UnicodeBlock.forName("ArabicExtended-A"));
        assertEquals(Character.UnicodeBlock.SUNDANESE_SUPPLEMENT, Character.UnicodeBlock.forName("SUNDANESE_SUPPLEMENT"));
        assertEquals(Character.UnicodeBlock.SUNDANESE_SUPPLEMENT, Character.UnicodeBlock.forName("Sundanese Supplement"));
        assertEquals(Character.UnicodeBlock.SUNDANESE_SUPPLEMENT, Character.UnicodeBlock.forName("SundaneseSupplement"));
        assertEquals(Character.UnicodeBlock.MEETEI_MAYEK_EXTENSIONS, Character.UnicodeBlock.forName("MEETEI_MAYEK_EXTENSIONS"));
        assertEquals(Character.UnicodeBlock.MEETEI_MAYEK_EXTENSIONS, Character.UnicodeBlock.forName("MEETEI MAYEK EXTENSIONS"));
        assertEquals(Character.UnicodeBlock.MEETEI_MAYEK_EXTENSIONS, Character.UnicodeBlock.forName("MeeteiMayekExtensions"));
        assertEquals(Character.UnicodeBlock.MEROITIC_HIEROGLYPHS, Character.UnicodeBlock.forName("MEROITIC_HIEROGLYPHS"));
        assertEquals(Character.UnicodeBlock.MEROITIC_HIEROGLYPHS, Character.UnicodeBlock.forName("MEROITIC HIEROGLYPHS"));
        assertEquals(Character.UnicodeBlock.MEROITIC_HIEROGLYPHS, Character.UnicodeBlock.forName("MeroiticHieroglyphs"));
        assertEquals(Character.UnicodeBlock.MEROITIC_CURSIVE, Character.UnicodeBlock.forName("MEROITIC_CURSIVE"));
        assertEquals(Character.UnicodeBlock.MEROITIC_CURSIVE, Character.UnicodeBlock.forName("MEROITIC CURSIVE"));
        assertEquals(Character.UnicodeBlock.MEROITIC_CURSIVE, Character.UnicodeBlock.forName("MeroiticCursive"));
        assertEquals(Character.UnicodeBlock.SORA_SOMPENG, Character.UnicodeBlock.forName("SORA_SOMPENG"));
        assertEquals(Character.UnicodeBlock.SORA_SOMPENG, Character.UnicodeBlock.forName("SORA SOMPENG"));
        assertEquals(Character.UnicodeBlock.SORA_SOMPENG, Character.UnicodeBlock.forName("SoraSompeng"));
        assertEquals(Character.UnicodeBlock.CHAKMA, Character.UnicodeBlock.forName("CHAKMA"));
        assertEquals(Character.UnicodeBlock.SHARADA, Character.UnicodeBlock.forName("SHARADA"));
        assertEquals(Character.UnicodeBlock.TAKRI, Character.UnicodeBlock.forName("TAKRI"));
        assertEquals(Character.UnicodeBlock.MIAO, Character.UnicodeBlock.forName("MIAO"));
        assertEquals(Character.UnicodeBlock.ARABIC_MATHEMATICAL_ALPHABETIC_SYMBOLS,
                     Character.UnicodeBlock.forName("ARABIC_MATHEMATICAL_ALPHABETIC_SYMBOLS"));
        assertEquals(Character.UnicodeBlock.ARABIC_MATHEMATICAL_ALPHABETIC_SYMBOLS,
                     Character.UnicodeBlock.forName("ARABIC MATHEMATICAL ALPHABETIC SYMBOLS"));
        assertEquals(Character.UnicodeBlock.ARABIC_MATHEMATICAL_ALPHABETIC_SYMBOLS,
                     Character.UnicodeBlock.forName("ArabicMathematicalAlphabeticSymbols"));

    }

    public void test_forNameLjava_lang_StringExceptions() {
        try {
            Character.UnicodeBlock.forName(null);
            fail();
        } catch (NullPointerException expected) {
        }

        try {
            Character.UnicodeBlock.forName("INVALID_NAME");
            fail();
        } catch (IllegalArgumentException expected) {
        }

        // We don't map "SURROGATES_AREA" to the deprecated SURROGATES_AREA
        // enum value. ICU doesn't have any block corresponding to this since it's
        // now split into low surrogates and the high (normal/private use)
        // surrogates. Also, the only API that makes any reference to this goes
        // directly to ICU anyway.
        try {
            Character.UnicodeBlock.forName("SURROGATES_AREA");
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }
}
