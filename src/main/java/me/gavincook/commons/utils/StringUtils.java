package me.gavincook.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 字符串相关操作方法工具类
 * @author Hinsteny
 * @date 2017-11-20
 * @copyright: 2017 All rights reserved.
 */
public class StringUtils {

    private static final Logger logger = LoggerFactory.getLogger(StringUtils.class);

    /**
     * 敏感信息脱敏默认替换字符
     */
    private static final Character conceal_char = '*';

    // Empty checks
    //-----------------------------------------------------------------------
    /**
     * <p>Checks if a String is empty ("") or null.</p>
     *
     * <pre>
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     * </pre>
     *
     * <p>NOTE: This method changed in Lang version 2.0.
     * It no longer trims the String.
     * That functionality is available in isBlank().</p>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if the String is empty or null
     * @since 1.0
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * <p>Checks if a String is not empty ("") and not null.</p>
     *
     * <pre>
     * StringUtils.isNotEmpty(null)      = false
     * StringUtils.isNotEmpty("")        = false
     * StringUtils.isNotEmpty(" ")       = true
     * StringUtils.isNotEmpty("bob")     = true
     * StringUtils.isNotEmpty("  bob  ") = true
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if the String is not empty and not null
     * @since 1.0
     */
    public static boolean isNotEmpty(String str) {
        return !StringUtils.isEmpty(str);
    }

    /**
     * <p>Checks if a String is whitespace, empty ("") or null.</p>
     *
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if the String is null, empty or whitespace
     * @since 1.0
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Checks if a String is not empty (""), not null and not whitespace only.</p>
     *
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if the String is
     *  not empty and not null and not whitespace
     * @since 1.0
     */
    public static boolean isNotBlank(String str) {
        return !StringUtils.isBlank(str);
    }

    /**
     * 用指定字符隐藏遮盖指定字符的相关位置内容
     * @param origin
     * @param start
     * @param end
     * @param shadow
     * @return
     */

    /**
     * <p>Replace sensitive substring with mark char to one string.</p>
     *
     * <pre>
     * StringUtils.replaceSensInfo(null, 5, 10, '*')      = ""
     * StringUtils.replaceSensInfo("123456", 3, 6, '*')   = "123***"
     * StringUtils.replaceSensInfo("123456", 3, 10, '*')  = "123***"
     * </pre>
     *
     * @param origin  the String to replace
     * @param start  the start index in origin
     * @param end  the end index in origin
     * @param shadow  the char use to be replace
     * @return
     * @since 1.0
     */
    public static String replaceSensInfo(String origin, Integer start, Integer end, Character shadow) {
        char[] news = new char[0];
        int length;
        if (null != origin && (length = origin.length()) > 0) {
            if (start < 0 || start > length || end < 0 || end < start) {
                throw new RuntimeException("param illegal!");
            }
            end = (end > origin.length()) ? origin.length() : end;
            news = new char[end];
            origin.getChars(0, start, news, 0);
            shadow = (null == shadow) ? conceal_char : shadow;
            char[] x = repeatChar(new char[end], start, end, shadow);
            System.arraycopy( x, start, news, start, end - start);
        }
        if (logger.isInfoEnabled()) {
            logger.info("Origin string: {}, news: {}", origin, String.valueOf(news));
        }
        return String.valueOf(news);
    }

    /**
     * 用指定字符填充字符数组
     * @param value
     * @param srcBegin
     * @param srcEnd
     * @param repeat
     */
    public static char[] repeatChar(char[] value,int srcBegin, int srcEnd, char repeat) {
        if (srcBegin < 0) {
            throw new StringIndexOutOfBoundsException(srcBegin);
        }
        if (srcEnd > value.length) {
            throw new StringIndexOutOfBoundsException(srcEnd);
        }
        if (srcBegin > srcEnd) {
            throw new StringIndexOutOfBoundsException(srcEnd - srcBegin);
        }
        for (int i = srcBegin, j = srcEnd; i<j; i++) {
            value[i] = repeat;
        }
        return value;
    }

}
