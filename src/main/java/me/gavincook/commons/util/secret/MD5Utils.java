package me.gavincook.commons.util.secret;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * DM5签名工具类
 * @author Hinsteny
 * @version $ID: MD5Utils 2018-04-15 14:52 All rights reserved.$
 */
public class MD5Utils {

    private static final String MD5 = "MD5";

    /**
     * 对文本进行32位小写MD5加密
     *
     * @param text
     * @return 加密后的内容
     */
    public static String calculateMD5Val(String text) {
        try {
            MessageDigest md5Encoder = MessageDigest.getInstance(MD5);
            byte[] textBytes = text.getBytes();
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
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException:" + MD5, e);
        }
    }

    /**
     * Test
     * @param args
     */
    public static void main(String[] args) {
        String md5PrivateKey = UUID.randomUUID().toString();
        String data = "走遍世界的心不能停...O(∩_∩)O哈哈~";
        String sign = MD5Utils.calculateMD5Val(data + md5PrivateKey);
        System.err.println(String.format("Data: %s, calculate MD5 to: %s", data + md5PrivateKey, sign));
        String wrongSign = MD5Utils.calculateMD5Val(data + UUID.randomUUID().toString());
        assert sign.equals(wrongSign) : "the sign is not correct";
    }
}
