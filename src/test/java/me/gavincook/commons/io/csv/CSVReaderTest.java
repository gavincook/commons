package me.gavincook.commons.io.csv;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;

import me.gavincook.commons.io.Files;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * CSV工具类测试
 *
 * @author GavinCook
 * @date 2017-05-23
 * @since 1.0.0
 */
public class CSVReaderTest {

    /**
     * 读取csv测试，将类型和列数打印出来
     *
     * @throws IOException
     */
    @Test
    public void testReadCSV() throws IOException {
        InputStream in = CSVReaderTest.class.getClassLoader().getResourceAsStream("test.csv");

        InputStream checkCharset = CSVReaderTest.class.getClassLoader().getResourceAsStream("test.csv");

        if (in != null) {
            CSVReader csvReader = new CSVReader(in,
                Charset.forName(Files.getFileEncoding(checkCharset, me.gavincook.commons.io.Charset.UTF8).charsetName()));
            ReadableCSVLine line;
            while ((line = csvReader.nextCSVLine()) != null) {
                String[] tokens = line.tokens();
                System.out.println(Arrays.toString(tokens) + ", 长度为：" + tokens.length);
            }
            csvReader.close();
        }
        Assert.assertTrue(true);
    }

}
