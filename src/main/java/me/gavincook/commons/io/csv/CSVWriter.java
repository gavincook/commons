package me.gavincook.commons.io.csv;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

/**
 * CSV文件写入工具类
 *
 * @author GavinCook
 * @date 2017-05-23
 * @since 1.0.0
 */
public class CSVWriter {

    /**
     * 缓冲流读取
     */
    private BufferedWriter writer;

    /**
     * 构建csv writer
     *
     * @param stream csv文件输出流
     */
    public CSVWriter(OutputStream stream, Charset charset) {
        this.writer = new BufferedWriter(new OutputStreamWriter(stream, charset));
    }

    public void write(WritableCSVLine csvLine) throws IOException {
        this.writer.write(csvLine.getFormattedTokensStr());
    }

    public void flush() throws IOException {
        this.writer.flush();
    }

    public void close() throws IOException {
        this.writer.close();
    }
}
