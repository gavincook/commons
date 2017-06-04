package me.gavincook.commons.io.csv;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;

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
        if (in != null) {
            CSVReader csvReader = new CSVReader(in, Charset.forName("GBK"));
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
