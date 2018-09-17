package me.gavincook.commons.util;

import java.util.ArrayList;
import java.util.List;
import me.gavincook.commons.page.PageCondition;
import org.testng.annotations.Test;


/**
 * {@link AssertUtils} 单元测试
 *
 * @author GavinCook
 * @version $Id: AssertUtilsTest.java, v0.0.1 2017-07-27 下午6:10 GavinCook Exp $$
 */
public class AssertUtilsTest {

    @Test(testName = "测试大于等于0的整数-正常场景")
    public void testAssertIntegerNumberGeZero() throws Exception {
        AssertUtils.assertIntegerNumberGeZero(0);
        AssertUtils.assertIntegerNumberGeZero(1);
        AssertUtils.assertIntegerNumberGeZero(888L);
        AssertUtils.assertIntegerNumberGeZero(1.00F);
        AssertUtils.assertIntegerNumberGeZero(2.00D);
    }

    @Test(testName = "测试大于等于0的整数-null场景", expectedExceptions = AssertionError.class)
    public void testAssertIntegerNumberGeZeroForNull() throws Exception {
        AssertUtils.assertIntegerNumberGeZero(null);
    }

    @Test(testName = "测试大于等于0的整数-负数场景", expectedExceptions = AssertionError.class)
    public void testAssertIntegerNumberGeZeroForNegative() throws Exception {
        AssertUtils.assertIntegerNumberGeZero(-1L);
    }

    @Test(testName = "测试大于等于0的整数-非整数场景", expectedExceptions = AssertionError.class)
    public void testAssertIntegerNumberGeZeroForFloat() throws Exception {
        AssertUtils.assertIntegerNumberGeZero(2.3F);
    }

    @Test(testName = "测试正整数-正常场景")
    public void testAssertPositiveIntegerNumber() throws Exception {
        AssertUtils.assertPositiveIntegerNumber(1);
        AssertUtils.assertPositiveIntegerNumber(888L);
        AssertUtils.assertPositiveIntegerNumber(1.00F);
        AssertUtils.assertPositiveIntegerNumber(2.00D);
    }

    @Test(testName = "测试正整数-0场景", expectedExceptions = AssertionError.class)
    public void testAssertPositiveIntegerNumberForZero() throws Exception {
        AssertUtils.assertPositiveIntegerNumber(0);
    }

    @Test(testName = "测试正整数-null场景", expectedExceptions = AssertionError.class)
    public void testAssertPositiveIntegerNumberForNull() throws Exception {
        AssertUtils.assertPositiveIntegerNumber(null);
    }

    @Test(testName = "测试正整数-负数场景", expectedExceptions = AssertionError.class)
    public void testAssertPositiveIntegerNumberForNegative() throws Exception {
        AssertUtils.assertPositiveIntegerNumber(-1L);
    }

    @Test(testName = "测试正整数-非整数场景", expectedExceptions = AssertionError.class)
    public void testAssertPositiveIntegerNumberForFloat() throws Exception {
        AssertUtils.assertPositiveIntegerNumber(2.3F);
    }

    @Test
    public void testAssertPositiveNumber() throws Exception {
        short s = 1;
        byte b = 2;
        int i = 222;
        long l = 232L;
        float f = 0.2F;
        double d = 0.22D;

        AssertUtils.assertPositiveNumber(s);
        AssertUtils.assertPositiveNumber(b, "short check");
        AssertUtils.assertPositiveNumber(i);
        AssertUtils.assertPositiveNumber(l);
        AssertUtils.assertPositiveNumber(f);
        AssertUtils.assertPositiveNumber(d);
    }

    @Test(expectedExceptions = AssertionError.class)
    public void testAssertPositiveNumberForZero() throws Exception {
        AssertUtils.assertPositiveNumber(0);
    }

    @Test(expectedExceptions = AssertionError.class)
    public void testAssertPositiveNumberForNull() throws Exception {
        AssertUtils.assertPositiveNumber(null);
    }

    @Test(expectedExceptions = AssertionError.class)
    public void testAssertPositiveNumberForNegative() throws Exception {
        AssertUtils.assertPositiveNumber(-0.1D, "应该大于0");
    }

    @Test
    public void testAssertStringNotBlank() {
        AssertUtils.assertStringNotBlank("not empty");
        AssertUtils.assertStringNotBlank("not empty", "description");
    }

    @Test(expectedExceptions = AssertionError.class)
    public void testAssertStringNotBlank_blank() {
        AssertUtils.assertStringNotBlank(" ");
    }

    @Test(expectedExceptions = AssertionError.class)
    public void testAssertStringNotBlank_null() {
        AssertUtils.assertStringNotBlank(" ");
    }

    @Test
    public void testAssertNull() {
        AssertUtils.assertNull(null, null);
        AssertUtils.assertNull(null, "");
    }

    @Test(expectedExceptions = AssertionError.class)
    public void testAssertNull_NotNull() {
        AssertUtils.assertNull(new Object(), null);
    }

    @Test
    public void testAssertNotNull() {
        AssertUtils.assertNotNull(new Object(), null);
    }

    @Test(expectedExceptions = AssertionError.class)
    public void testAssertNotNull_Null() {
        AssertUtils.assertNotNull(null, null);
    }

    @Test
    public void testAssertNotEquals() {
        AssertUtils.assertNotEquals(1, 1L, null);
        AssertUtils.assertNotEquals(new Object(), new Object(), null);

        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");

        List<String> list2 = new ArrayList<>();
        list2.addAll(list1);
        list2.add("3");

        AssertUtils.assertNotEquals(list1, list2, null);
    }

    @Test
    public void testAssertEquals() {
        AssertUtils.assertEquals(1, 1, null);
        AssertUtils.assertEquals(1.0, 1.0, null);

        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");

        List<String> list2 = new ArrayList<>();
        list2.addAll(list1);

        AssertUtils.assertEquals(list1, list2, null);
    }

    @Test
    public void testAssertByteArrayEquals() {
        byte[] bytes1 = new byte[]{1, 2, 3};
        byte[] bytes2 = new byte[]{1, 2, 3};
        AssertUtils.assertArrayEquals(bytes1, bytes2, "");
    }

    @Test
    public void testAssertShortArrayEquals() {
        short[] shorts1 = new short[]{1, 2, 3};
        short[] shorts2 = new short[]{1, 2, 3};
        AssertUtils.assertArrayEquals(shorts1, shorts2, "");
    }

    @Test
    public void testAssertIntArrayEquals() {
        int[] ints1 = new int[]{1, 2, 3, 999};
        int[] ints2 = new int[]{1, 2, 3, 999};
        AssertUtils.assertArrayEquals(ints1, ints2, "");
    }

    @Test
    public void testAssertFloatArrayEquals() {
        float[] floats1 = new float[]{1, 2.6F, 3, 999};
        float[] floats2 = new float[]{1, 2.6F, 3, 999};
        AssertUtils.assertArrayEquals(floats1, floats2, "");
    }

    @Test
    public void testAssertDoubleArrayEquals() {
        double[] doubles1 = new double[]{1, 2, 3.3D, 999};
        double[] doubles2 = new double[]{1, 2, 3.3D, 999};
        AssertUtils.assertArrayEquals(doubles1, doubles2, "");
    }

    @Test
    public void testAssertLongArrayEquals() {
        long[] longs1 = new long[]{1, 2, 3, 999};
        long[] longs2 = new long[]{1, 2, 3, 999};
        AssertUtils.assertArrayEquals(longs1, longs2, "");
    }

    @Test
    public void testAssertTrue() {
        AssertUtils.assertTrue(true, "");
    }

    @Test
    public void testAssertFalse() {
        AssertUtils.assertFalse(false, "");
    }

    @Test
    public void testAssertPageConditionValid() {
        PageCondition pageCondition = new PageCondition.Builder().build();
        AssertUtils.assertPageConditionValid(pageCondition);
    }
}
