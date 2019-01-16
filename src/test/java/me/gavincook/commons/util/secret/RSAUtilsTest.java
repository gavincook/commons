package me.gavincook.commons.util.secret;

import java.security.KeyPair;
import org.testng.annotations.Test;

/**
 * @author Hinsteny
 * @version $ID: RSAUtilsTest 2019-01-16 15:55 All rights reserved.$
 */
public class RSAUtilsTest {

    @Test(testName = "工具类使用示例")
    public void testAssertIntegerNumberGeZero() throws Exception {
        KeyPair keyPair = RSAUtils.generateKeyPair();
        String publicKey = RSAUtils.getPublicKey(keyPair);
        String privateKey = RSAUtils.getPrivateKey(keyPair);
        System.out.println("Public key: " + publicKey);
        System.out.println("Private key: " + privateKey);
        String data = "走遍世界的心不能停...O(∩_∩)O哈哈~", secret, origin;
        System.out.println("============== 公钥加密, 私钥解密 ==============");
        System.out.println("Origin data before encrypt is: " + data);
        secret = RSAUtils.encryptData(publicKey, data, "UTF-8");
        System.out.println("secret data after encrypt is: " + secret);
        origin = RSAUtils.decryptData(privateKey, secret, "UTF-8");
        System.out.println("origin data after encrypt is: " + origin);

        System.out.println("============== 私钥加密, 公钥解密 ==============");
        System.out.println("Origin data before encrypt is: " + data);
        secret = RSAUtils.encryptData(privateKey, data, "UTF-8", false);
        System.out.println("secret data after encrypt is: " + secret);
        origin = RSAUtils.decryptData(publicKey, secret, "UTF-8", true);
        System.out.println("origin data after encrypt is: " + origin);
    }

}
