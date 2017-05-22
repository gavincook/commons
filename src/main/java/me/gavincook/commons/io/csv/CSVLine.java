package me.gavincook.commons.io.csv;

/**
 * @author GavinCook
 * @date 2017-05-23
 * @since 1.0.0
 */
public interface CSVLine {

    /**
     * 列分隔符
     */
    char COMMA = ',';

    /**
     * 转义符
     */
    char DOUBLE_QUOTE = '"';

    boolean hasMore();

    String nextToken();
}
