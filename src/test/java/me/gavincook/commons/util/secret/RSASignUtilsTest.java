package me.gavincook.commons.util.secret;

import java.security.KeyPair;
import me.gavincook.commons.util.secret.RSASignUtils.Algorithm;
import org.testng.annotations.Test;

/**
 * @author Hinsteny
 * @version $ID: RSASignUtilsTest 2019-01-16 15:54 All rights reserved.$
 */
public class RSASignUtilsTest {

    @Test(testName = "工具类使用示例")
    public void testAssertIntegerNumberGeZero() throws Exception {
        String inputCharset = "UTF-8";
        KeyPair keyPair = RSASignUtils.generateKeyPair();
        String publicKey = RSASignUtils.getPublicKey(keyPair);
        String privateKey = RSASignUtils.getPrivateKey(keyPair);
        String data = "走遍世界的心不能停...O(∩_∩)O哈哈~";
        // 默认使用 Algorithm.SHA1withRSA 签名算法
        String sign = RSASignUtils.sign(data, privateKey, inputCharset);
        System.out.println(String.format("Data: %s, sign to: %s", data, sign));
        boolean verify = RSASignUtils.verify(data, sign, publicKey, inputCharset);
        System.out.println(String.format("Use correct publicKey to verify, the result is: %s", verify));
        String notCorrectPublicKey = RSASignUtils.getPublicKey(RSASignUtils.generateKeyPair());
        verify = RSASignUtils.verify(data, sign, notCorrectPublicKey, inputCharset);
        System.out.println(String.format("Use wrong publicKey to verify, the result is: %s", verify));

        // 指定使用 Algorithm.MD5withRSA 签名算法
        sign = RSASignUtils.sign(Algorithm.MD5withRSA, data, privateKey, inputCharset);
        System.out.println(String.format("Data: %s, sign to: %s", data, sign));
        verify = RSASignUtils.verify(Algorithm.MD5withRSA, data, sign, publicKey, inputCharset);
        System.out.println(String.format("Use correct publicKey to verify, the result is: %s", verify));
        notCorrectPublicKey = RSASignUtils.getPublicKey(RSASignUtils.generateKeyPair());
        verify = RSASignUtils.verify(Algorithm.MD5withRSA, data, sign, notCorrectPublicKey, inputCharset);
        System.out.println(String.format("Use wrong publicKey to verify, the result is: %s", verify));
    }

}
