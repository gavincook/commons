package me.gavincook.commons.util;

import java.io.IOException;
import org.testng.annotations.Test;

/**
 * @author Hinsteny
 * @version $ID: ZipFileUtilsTest 2019-03-18 15:56 All rights reserved.$
 */
public class ZipFileUtilsTest {

    @Test
    public void testZip() throws IOException {
        String zipFilesFolder = "E:/openssl-0.9.8k_WIN32RSA密钥生成工具-2014-09-10-015136 (2)";
        String zipFilePth = "E:/test/openssl-0.9.8k_WIN32RSA密钥生成工具-2014-09-10-015136 (2).zip";
//        ZipFileUtils.zipFile(zipFilesFolder, zipFilePth);
    }

    @Test
    public void testUnzip() throws IOException {
        String zipFilePth = "E:/openssl-0.9.8k_WIN32RSA密钥生成工具-2014-09-10-015136 (2).zip";
        String zipFilesFolder = "E:/";
//        ZipFileUtils.unZipFiles(zipFilePth, zipFilesFolder, true);
    }

}
