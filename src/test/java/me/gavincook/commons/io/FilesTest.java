package me.gavincook.commons.io;

import me.gavincook.commons.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * 文件工具类测试
 * @author GavinCook
 * @date 2017-06-11
 * @since 1.0.0
 */
public class FilesTest extends BaseTest{

    /**
     * 文件编码检测
     * @throws IOException
     */
    @Test
    public void testGetFileEncoding() throws IOException {
        InputStream in = FilesTest.class.getClassLoader().getResourceAsStream(
                getTestResourceDir().concat("gbk_file.txt"));
        assert  Charset.GB2312 == Files.getFileEncoding(in, Charset.GBK);
        in = FilesTest.class.getClassLoader().getResourceAsStream(
                getTestResourceDir().concat("utf8_file.txt"));
        assert  Charset.GBK == Files.getFileEncoding(in, Charset.GBK);
    }

}
