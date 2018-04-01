package me.gavincook.commons.util;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static me.gavincook.commons.util.StringUtils.replaceSensInfo;

/**
 * @author Hinsteny
 * @date 2017-11-20
 * @copyright: 2017 All rights reserved.
 */
public class StringUtilsTest {

    @Test
    public void testReplaceSensInfo() throws IOException {
        Assert.assertEquals("", replaceSensInfo(null, 5, 10, '*'));
        Assert.assertEquals("12345**", replaceSensInfo("1234567890", 5, 7, '*'));
        Assert.assertEquals("12345*****", replaceSensInfo("1234567890", 5, 10, '*'));
        Assert.assertEquals("12345*****", replaceSensInfo("1234567890", 5, 15, '*'));
    }

    @Test
    public void testIsNullOrEmpty() {
        Assert.assertTrue(StringUtils.isNullOrEmpty(null));
        Assert.assertTrue(StringUtils.isNullOrEmpty(""));
        Assert.assertFalse(StringUtils.isNullOrEmpty(" "));
        Assert.assertFalse(StringUtils.isNullOrEmpty("nonEmpty"));
        Assert.assertFalse(StringUtils.isNullOrEmpty(" nonEmpty "));
        Assert.assertFalse(StringUtils.isNullOrEmpty("\t"));
    }

    @Test
    public void testIsBlank() {
        Assert.assertTrue(StringUtils.isBlank(null));
        Assert.assertTrue(StringUtils.isBlank(""));
        Assert.assertTrue(StringUtils.isBlank(" "));
        Assert.assertTrue(StringUtils.isBlank("\t\n"));
        Assert.assertFalse(StringUtils.isBlank("nonEmpty"));
        Assert.assertFalse(StringUtils.isBlank(" nonEmpty "));
    }

    @Test
    public void testLength() {
        Assert.assertEquals(StringUtils.length(null), 0);
        Assert.assertEquals(StringUtils.length(""), 0);
        Assert.assertEquals(StringUtils.length(" "), 1);
        Assert.assertEquals(StringUtils.length("\t\n"), 2);
        Assert.assertEquals(StringUtils.length(" str "), 5);
    }

    @Test
    public void testTrim() {
        Assert.assertNull(StringUtils.trim(null));
        Assert.assertEquals(StringUtils.trim("  \tabc\n\r  "), "abc");
        Assert.assertEquals(StringUtils.trim("abc"), "abc");
        Assert.assertEquals(StringUtils.trim("  \ta\rb c\n\r "), "a\rb c");
    }

    @Test
    public void testLeftPadWith2Args() {
        Assert.assertNull(StringUtils.leftPad(null, 5));
        Assert.assertEquals(StringUtils.leftPad("abc", 3), "abc");
        Assert.assertEquals(StringUtils.leftPad("abc", 2), "abc");
        Assert.assertEquals(StringUtils.leftPad("abc", 5), "  abc");
    }

    @Test
    public void testLeftPadWith3Args() {
        char padChar = StringUtils.SPACE_CHAR;
        Assert.assertNull(StringUtils.leftPad(null, 5, padChar));
        Assert.assertEquals(StringUtils.leftPad("abc", 3, padChar), "abc");
        Assert.assertEquals(StringUtils.leftPad("abc", 2, padChar), "abc");
        Assert.assertEquals(StringUtils.leftPad("abc", 5, padChar), String.valueOf(padChar) + String.valueOf(padChar) + "abc");

        padChar = '#';
        Assert.assertNull(StringUtils.leftPad(null, 5, padChar));
        Assert.assertEquals(StringUtils.leftPad("abc", 3, padChar), "abc");
        Assert.assertEquals(StringUtils.leftPad("abc", 2, padChar), "abc");
        Assert.assertEquals(StringUtils.leftPad("abc", 5, padChar), String.valueOf(padChar) + String.valueOf(padChar) + "abc");
    }

    @Test
    public void testRightPadWith2Args() {
        Assert.assertNull(StringUtils.rightPad(null, 5));
        Assert.assertEquals(StringUtils.rightPad("abc", 3), "abc");
        Assert.assertEquals(StringUtils.rightPad("abc", 2), "abc");
        Assert.assertEquals(StringUtils.rightPad("abc", 5), "abc  ");
    }

    @Test
    public void testRightPadWith3Args() {
        char padChar = StringUtils.SPACE_CHAR;
        Assert.assertNull(StringUtils.rightPad(null, 5, padChar));
        Assert.assertEquals(StringUtils.rightPad("abc", 3, padChar), "abc");
        Assert.assertEquals(StringUtils.rightPad("abc", 2, padChar), "abc");
        Assert.assertEquals(StringUtils.rightPad("abc", 5, padChar), "abc" + String.valueOf(padChar) + String.valueOf(padChar));

        padChar = '#';
        Assert.assertNull(StringUtils.rightPad(null, 5, padChar));
        Assert.assertEquals(StringUtils.rightPad("abc", 3, padChar), "abc");
        Assert.assertEquals(StringUtils.rightPad("abc", 2, padChar), "abc");
        Assert.assertEquals(StringUtils.rightPad("abc", 5, padChar), "abc" + String.valueOf(padChar) + String.valueOf(padChar));
    }

    @Test
    public void testRepeatChar() {
        Assert.assertNull(StringUtils.repeat('A', 0));
        Assert.assertNull(StringUtils.repeat('A', -1));
        Assert.assertEquals(StringUtils.repeat('A', 1), "A");
        Assert.assertEquals(StringUtils.repeat('A', 3), "AAA");
    }

    @Test
    public void testRepeatString() {
        Assert.assertNull(StringUtils.repeat("abc", 0));
        Assert.assertNull(StringUtils.repeat("abc", -1));
        Assert.assertEquals(StringUtils.repeat("abc", 1), "abc");
        Assert.assertEquals(StringUtils.repeat("abc", 2), "abcabc");
        Assert.assertEquals(StringUtils.repeat("", 2), "");
        Assert.assertEquals(StringUtils.repeat("", 1), "");
    }

    @Test
    public void testJoinCollection() {
        List<Object> list = new ArrayList<>();
        list.add(1);
        list.add("2");
        list.add(3.0D);
        list.add(4L);
        list.add(5.0F);
        list.add(true);
        list.add(false);
        Assert.assertEquals(StringUtils.join(list, ","), "1,2,3.0,4,5.0,true,false");
        Assert.assertEquals(StringUtils.join(list, ""), "123.045.0truefalse");
        Assert.assertEquals(StringUtils.join(list, null), "123.045.0truefalse");
        Assert.assertNull(StringUtils.join((Collection<? extends Object>) null, null));
    }

    @Test
    public void testJoinArray() {
        Object[] array = new Object[]{1, "2", 3.0D, 4L, 5.0F, true, false};
        Assert.assertEquals(StringUtils.join(array, ","), "1,2,3.0,4,5.0,true,false");
        Assert.assertEquals(StringUtils.join(array, ""), "123.045.0truefalse");
        Assert.assertEquals(StringUtils.join(array, null), "123.045.0truefalse");
        Assert.assertNull(StringUtils.join((Collection<? extends Object>) null, null));
    }
}
