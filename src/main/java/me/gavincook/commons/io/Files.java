package me.gavincook.commons.io;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;

/**
 * 文件工具类
 * @author GavinCook
 * @date 2017-06-11
 * @since 1.0.0
 */
public class Files {

    /**
     * 根据文件流判断文件内容编码，注意这里的编码主要根据前100个字节来决定。不一定和原有编码一致，如GBK可能解析为GB2312。
     * @param stream 文件输入流
     * @param defaultCharset 当无法解析编码时，默认编码
     * @return
     */
    public static Charset getFileEncoding(InputStream stream, Charset defaultCharset) throws IOException {
        if (stream == null) {
            throw new IllegalArgumentException("stream argument can't be null for getFileEncoding");
        }
        try {
            byte[] first3Bytes = new byte[100];
            int length = stream.read(first3Bytes, 0, 100);

            /**
             * 通过前面的三个字节可以判断几个UTF8带bom的编码，分别为：{@link Charset#UTF8}, {@link Charset#UTF_16LE}, {@link Charset#UTF_16BE},
             */
            if (length >= 3) {
                final int b1 = first3Bytes[0] & 0xFF;
                final int b2 = first3Bytes[1] & 0xFF;
                final int b3 = first3Bytes[2] & 0xFF;

                switch (b1) {
                    case 0xEF:
                        if (b2 == 0xBB && b3 == 0xBF) {
                            return Charset.UTF8;
                        }
                        break;
                    case 0xFE:
                        if (b2 == 0xFF) {
                            return Charset.UTF_16BE;
                        }
                        break;
                    case 0xFF:
                        if (b2 == 0xFE) {
                            return Charset.UTF_16BE;
                        }
                        break;
                    default:
                        //do nothing
                }
            }

            /**
             * 如果不匹配，那么依次遍历{@link Charset}中的所有编码，依次判断。
             * 注意编码越在前，优先级越高
             */
            for (Charset charset : Charset.values()) {
                if (matchCharset(first3Bytes, charset.charsetName())) {
                    return charset;
                }
            }

            /**
             * 如果遍历{@link Charset}中的所有编码还未找到匹配的，则返回默认编码
             */
            return defaultCharset;
        } finally {
            stream.close();
        }
    }

    /**
     * 检测字节数组是否匹配指定编码
     * @param data 字节数组
     * @param charsetName 指定编码
     * @return 匹配则返回<code>true</code>， 否则返回<code>false</code>
     */
    public static boolean matchCharset(byte[] data, String charsetName) {
        if (data == null) {
            throw new IllegalArgumentException("data argument can't be null for matchCharset");
        }
        CharsetDecoder charsetDecoder = java.nio.charset.Charset.forName(charsetName).newDecoder();
        try {
            charsetDecoder.decode(ByteBuffer.wrap(data));
            return true;
        } catch (CharacterCodingException e) {
            return false;
        }

    }
}
