package me.gavincook.commons.util.secret;

import java.util.UUID;
import org.testng.annotations.Test;

/**
 * @author Hinsteny
 * @version $ID: MD5UtilsTest 2019-01-16 15:48 All rights reserved.$
 */
public class MD5UtilsTest {

    @Test(testName = "工具类使用示例")
    public void testAssertIntegerNumberGeZero() throws Exception {
        String md5PrivateKey = UUID.randomUUID().toString();
        String data = "走遍世界的心不能停...O(∩_∩)O哈哈~";
        String sign = MD5Utils.sign(data + md5PrivateKey);
        System.out.println(String.format("Data: %s, calculate MD5 to: %s", data + md5PrivateKey, sign));
        String wrongSign = MD5Utils.sign(data + UUID.randomUUID().toString());
        assert !sign.equals(wrongSign) : "the sign should not equals that is correct";
    }

}