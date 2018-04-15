package me.gavincook.commons.util.secret;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

/**
 * AES加解密算法
 * 加密用的Key 可以用26个字母和数字组成，最好不要用保留字符
 * 此处使用AES-128-CBC加密模式，key需要为16位。
 *
 * @author Hinsteny
 * @version $ID: AESUtils 2018-04-15 14:45 All rights reserved.$
 */
public class AESUtils {

    /** 默认的AES秘钥长度 **/
    private static final int DEFAULT_KEY_LENGTH = 128;

    /** 算法 */
    private static final String AES       = "AES";

    /** 算法/模式/补码方式 */
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";

    /** 编码类型 */
    private static final String CHARCODE  = "UTF-8";

    /** 使用CBC模式，需要一个向量iv，可增加加密算法的强度 */
    private static final String MODEL     = "0102030405060708";

    /**
     * 生成一个AES加密私钥串
     * @return
     */
    public static String generateAESKey() {
        return generateAESKey(DEFAULT_KEY_LENGTH);
    }

    /**
     *
     * @param keyLen AES秘钥长度可选值有
     * @return
     */
    private static String generateAESKey(int keyLen) {
        if (!(keyLen == 128)) {
            throw new RuntimeException("AES key length is not correct");
        }
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        kg.init(keyLen);
        SecretKey sk = kg.generateKey();
        byte[] b = sk.getEncoded();
        return byteToHexString(b);
    }

    /**
     * 判断key长度有效性
     * @param keyLen
     * @return
     */
    private static boolean judgeKeyLen(int keyLen) {
        if (!(keyLen == 16)) {
            throw new RuntimeException("AES key length is not correct");
        }
        return true;
    }

    /**
     * byte数组转化为16进制字符串
     * @param bytes
     * @return
     */
    public static String byteToHexString(byte[] bytes){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            int source = (bytes[i] & 0xFF);
            String strHex=Integer.toHexString(source);
            if(strHex.length() < 2){
                sb.append(strHex);
            } else {
                sb.append(strHex.charAt(0));
            }
        }
        return  sb.toString();
    }

    /**
     * 加密
     *
     * @param src 需要加密的内容
     * @param key 加密秘钥
     * @return
     */
    public static String encrypt(String src, String key) {
        return encrypt(src, key, CHARCODE);
    }

    /**
     * 解密
     *
     * @param src 密文
     * @param key 密钥
     * @return
     */
    public static String decrypt(String src, String key) {
        return decrypt(src, key, CHARCODE);
    }

    /**
     * 加密
     *
     * @param src 需要加密的内容
     * @param key 加密秘钥
     * @param charset 编码
     * @return
     */
    public static String encrypt(String src, String key, String charset) {
        if (key == null) {
            return null;
        }
        judgeKeyLen(key.length());
        String afterCode;
        try {
            byte[] raw = key.getBytes();
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
            IvParameterSpec iv = new IvParameterSpec(MODEL.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(src.getBytes(charset));
            //此处使用BASE64做转码功能，防止中文乱码
            afterCode = new BASE64Encoder().encode(encrypted);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return afterCode.replaceAll("\r|\n", "");
    }

    /**
     * 解密
     *
     * @param src 密文
     * @param key 密钥
     * @return
     */
    public static String decrypt(String src, String key, String charset) {
        // 判断Key密钥是否正确
        if (key == null) {
            return null;
        }
        judgeKeyLen(key.length());
        String originalString;
        try {
            byte[] raw = key.getBytes(CHARCODE);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(MODEL.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            //先用base64解密
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(src);
            byte[] original = cipher.doFinal(encrypted1);
            originalString = new String(original, charset);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return originalString;
    }

    /**
     * test
     * @param args
     */
    public static void main(String[] args) {
        String data = "走遍世界的心不能停...O(∩_∩)O哈哈~";
        String key ,secret;
        key = AESUtils.generateAESKey(128);
        System.out.println("key length 128: " + key);
        secret = AESUtils.encrypt(data, key);
        System.out.println(String.format("encrypt data: %s, result: %s", data, secret));
        String dData = AESUtils.decrypt(secret, key);
        System.out.println("decrypt result: " + dData);

    }
}
