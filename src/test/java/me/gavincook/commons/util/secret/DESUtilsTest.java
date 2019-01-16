package me.gavincook.commons.util.secret;

import org.testng.annotations.Test;

/**
 * @author Hinsteny
 * @version $ID: DESUtilsTest 2019-01-16 15:47 All rights reserved.$
 */
public class DESUtilsTest {

    @Test(testName = "工具类使用示例")
    public void testAssertIntegerNumberGeZero() throws Exception {
        String data = "走遍世界的心不能停...O(∩_∩)O哈哈~";
        String key, secret;
        key = DESUtils.generateDESKey();
        System.out.println("key is : " + key);
        secret = DESUtils.encrypt(data, key);
        System.out.println(String.format("encrypt data: %s, result: %s", data, secret));
        String dData = DESUtils.decrypt(secret, key);
        System.out.println("decrypt result: " + dData);
        key = DESUtils.generate3DESKey();
        System.out.println("key is : " + key);

        secret = DESUtils.encrypt3DES(data, key);
        System.out.println(String.format("encrypt data: %s, result: %s", data, secret));
        dData = DESUtils.decrypt3DES(secret, key);
        System.out.println("decrypt result: " + dData);
    }

}
