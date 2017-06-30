package me.gavincook.commons.io.csv;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * CSV工具类测试
 *
 * @author GavinCook
 * @date 2017-05-23
 * @since 1.0.0
 */
public class CSVWriterTest {

    /**
     * 测试写入一行数据， 包含逗号和引号
     *
     * @throws IOException
     */
    @Test
    public void testWriteCSV() throws IOException {
        File file = new File(CSVWriterTest.class.getClassLoader().getResource("").getFile() + "/write-test.csv");
        if (!file.exists()) {
            file.createNewFile();
        }
        System.out.println(file.getAbsoluteFile());
        OutputStream out = new FileOutputStream(file);
        CSVWriter writer = new CSVWriter(out, Charset.forName("GBK"));
        WritableCSVLine csvLine = new WritableCSVLine();
        csvLine.appendToken("1");
        csvLine.appendToken("2,");
        csvLine.appendToken("3\",");
        writer.write(csvLine);
        writer.close();
        Assert.assertTrue(true);
    }

}
