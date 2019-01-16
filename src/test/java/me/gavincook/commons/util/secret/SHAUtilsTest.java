package me.gavincook.commons.util.secret;

import java.util.UUID;
import org.testng.annotations.Test;

/**
 * @author Hinsteny
 * @version $ID: SHAUtilsTest 2019-01-16 15:56 All rights reserved.$
 */
public class SHAUtilsTest {

    @Test(testName = "工具类使用示例")
    public void testAssertIntegerNumberGeZero() throws Exception {
        String key = UUID.randomUUID().toString();
        String data = "走遍世界的心不能停...O(∩_∩)O哈哈~", sign, wrongSign;
        sign = SHAUtils.calculateSha1Val(data + key);
        System.err.println(String.format("Data: %s, calculate Sha1 to: %s", data + key, sign));
        wrongSign = SHAUtils.calculateSha1Val(data + UUID.randomUUID().toString());
        assert !sign.equals(wrongSign) : "the sign should not equals that is correct";

        sign = SHAUtils.calculateSha224Val(data + key);
        System.err.println(String.format("Data: %s, calculate Sha224 to: %s", data + key, sign));
        wrongSign = SHAUtils.calculateSha224Val(data + UUID.randomUUID().toString());
        assert !sign.equals(wrongSign) : "the sign should not equals that is correct";

        sign = SHAUtils.calculateSha256Val(data + key);
        System.err.println(String.format("Data: %s, calculate Sha256 to: %s", data + key, sign));
        wrongSign = SHAUtils.calculateSha256Val(data + UUID.randomUUID().toString());
        assert !sign.equals(wrongSign) : "the sign should not equals that is correct";

        sign = SHAUtils.calculateSha384Val(data + key);
        System.err.println(String.format("Data: %s, calculate Sha384 to: %s", data + key, sign));
        wrongSign = SHAUtils.calculateSha384Val(data + UUID.randomUUID().toString());
        assert !sign.equals(wrongSign) : "the sign should not equals that is correct";

        sign = SHAUtils.calculateSha512Val(data + key);
        System.err.println(String.format("Data: %s, calculate Sha512 to: %s", data + key, sign));
        wrongSign = SHAUtils.calculateSha512Val(data + UUID.randomUUID().toString());
        assert !sign.equals(wrongSign) : "the sign should not equals that is correct";
    }

}
