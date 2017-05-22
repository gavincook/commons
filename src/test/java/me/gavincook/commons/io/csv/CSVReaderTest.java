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

    @Test
    public void testReadCSV() throws IOException {
        InputStream in = CSVReaderTest.class.getClassLoader().getResourceAsStream("test.csv");
        if (in != null) {
            CSVReader csvReader = new CSVReader(in, Charset.forName("GBK"));
            CSVLine line;
            while ((line = csvReader.nextCSVLine()) != null) {
                System.out.println(Arrays.toString(line.tokens()));
            }
        }
        Assert.assertTrue(true);
    }

}
