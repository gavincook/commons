package me.gavincook.commons.io;

/**
 * 编码枚举，增加编码时，应该将范围越大的放置在后面，范围小的放置在前面。文件工具类会依次遍历进行编码检测。
 * @author GavinCook
 * @date 2017-06-11
 * @since 1.0.0
 */
public enum Charset {

    GB2312("GB2312"),

    GBK("GBK"),

    UTF8("UTF-8"),

    UTF_16BE("UTF-16BE"),

    UTF_16LE("UTF-16LE"),

    ISO_8859_1("ISO-8859-1");

    private String charsetName;

    Charset(String charsetName){
        this.charsetName = charsetName;
    }

    public String charsetName() {
        return charsetName;
    }
}
