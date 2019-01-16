package me.gavincook.commons.util.secret;

import org.testng.annotations.Test;

/**
 * @author Hinsteny
 * @version $ID: AESUtilsTest 2019-01-16 15:42 All rights reserved.$
 */
public class AESUtilsTest {

    @Test(testName = "工具类使用示例")
    public void testAssertIntegerNumberGeZero() throws Exception {
        String data = "走遍世界的心不能停...O(∩_∩)O哈哈~", key, secret, origin;
        key = AESUtils.generateAESKey();
        System.out.println("key length 128: " + key);
        System.out.println("============== 默认AES加解密前后使用Base64加解码 ==============");
        secret = AESUtils.encrypt(data, key);
        System.out.println(String.format("encrypt data: %s, result: %s", data, secret));
        origin = AESUtils.decrypt(secret, key);
        System.out.println("decrypt result: " + origin);
        System.out.println("============== 指定AES加解密前后使用Hex加解码 ==============");
        secret = AESUtils.encryptThenHex(data, key, "UTF-8");
        System.out.println(String.format("encrypt data: %s, result: %s", data, secret));
        origin = AESUtils.decryptAfterHexDecode(secret, key, "UTF-8");
        System.out.println("decrypt result: " + origin);
    }

}
