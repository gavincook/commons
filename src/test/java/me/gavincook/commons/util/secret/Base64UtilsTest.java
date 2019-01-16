package me.gavincook.commons.util.secret;

import org.testng.annotations.Test;

/**
 * @author Hinsteny
 * @version $ID: Base64UtilsTest 2019-01-16 15:44 All rights reserved.$
 */
public class Base64UtilsTest {

    @Test(testName = "工具类使用示例")
    public void testAssertIntegerNumberGeZero() throws Exception {
        String data = "走遍世界的心不能停...O(∩_∩)O哈哈~";
        String result_this, result_sun = null;
        System.out.println("============== Base64 加码[默认字符编码] ==============");
        result_this = Base64Utils.base64Encodes(data.getBytes());
//        result_sun = new BASE64Encoder().encode(data.getBytes());
        System.out.println(String.format("this: [%s] equals sun : [%s]  is [%s]", result_this, result_sun, result_this.equals(result_sun)));

        System.out.println("============== Base64 解码[默认字符编码] ==============");
        result_this = Base64Utils.base64Decodes(result_this.getBytes());
//        result_sun = new String(new BASE64Decoder().decodeBuffer(result_sun));
        System.out.println(String.format("this: [%s] equals sun : [%s]  is [%s]", result_this, result_sun, result_this.equals(result_sun)));

        System.out.println("============== Base64 加码[指定字符编码] ==============");
        result_this = Base64Utils.base64Encodes(data, "UTF-8");
//        result_sun = new BASE64Encoder().encode(data.getBytes("UTF-8"));
        System.out.println(String.format("this: [%s] equals sun : [%s]  is [%s]", result_this, result_sun, result_this.equals(result_sun)));

        System.out.println("============== Base64 解码[指定字符编码] ==============");
        result_this = Base64Utils.base64Decodes(result_this.getBytes(), "UTF-8");
//        result_sun = new String(new BASE64Decoder().decodeBuffer(result_sun), "UTF-8");
        System.out.println(String.format("this: [%s] equals sun : [%s]  is [%s]", result_this, result_sun, result_this.equals(result_sun)));
    }

}
