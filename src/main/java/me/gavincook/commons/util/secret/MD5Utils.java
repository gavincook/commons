package me.gavincook.commons.util.secret;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.UUID;

/**
 * DM5签名工具类
 * @author Hinsteny
 * @version $ID: MD5Utils 2018-04-15 14:52 All rights reserved.$
 */
public class MD5Utils {

    private static final String MD5 = "MD5";

    private static final String DEFAULT_CHART_SET = "UTF-8";

    /**
     * 对文本进行32位小写MD5加密
     *
     * @param text
     * @return 加密后的内容
     */
    public static String sign(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return sign(text, DEFAULT_CHART_SET);
    }

    /**
     * 对文本进行32位小写MD5加密
     *
     * @param text
     * @return 加密后的内容
     */
    public static String sign(String text, String charset) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5Encoder = MessageDigest.getInstance(MD5);
        byte[] textBytes = getContentBytes(text, charset);
        md5Encoder.update(textBytes);
        byte[] encodedBytes = md5Encoder.digest();
        StringBuilder resultBuffer = new StringBuilder();
        for (byte perByte : encodedBytes) {
            int bt = perByte & 0xff;
            if (bt < 16) {
                resultBuffer.append(0);
            }
            resultBuffer.append(Integer.toHexString(bt));
        }
        return resultBuffer.toString();
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    private static byte[] getContentBytes(String content, String charset) throws UnsupportedEncodingException{
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedEncodingException("sign occurred a exception with " + charset);
        }
    }

}
