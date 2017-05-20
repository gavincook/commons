package me.gavincook.commons.io.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * CSV 读取工具类
 *
 * @author GavinCook
 * @date 2017-05-20
 * @since 1.0.0
 */
public class CSVReader {

    /**
     * 缓冲流读取
     */
    private BufferedReader reader;

    /**
     * 构建csv reader
     *
     * @param stream
     */
    public CSVReader(InputStream stream, Charset charset) {
        this.reader = new BufferedReader(new InputStreamReader(stream, charset));
    }

    /**
     * 返回下一个csv行，如没有更多内容时返回<code>null</code>
     *
     * @return 下一个csv行或<code>null</code>
     */
    public CSVLine nextCSVLine() throws IOException {
        String lineStr = this.reader.readLine();
        if (lineStr == null) {
            return null;
        }
        return new CSVLine(lineStr);
    }

    /**
     * 关闭csv资源
     */
    public void close() {
        if (this.reader != null) {
            try {
                this.reader.close();
            } catch (IOException e) {
                throw new RuntimeException("close csv reader exception", e);
            }
        }
    }

}
