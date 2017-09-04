package me.gavincook.commons.io.image;

/**
 * 图片类型枚举集合对象
 * @author Hinsteny
 * @date 2017-08-07
 * @since 1.0.0
 */
public enum ImageType {

    JPG("JPG", "Animate a sequence of images"),
    JPEG("JPEG", "Executes an arbitary number of utility commands"),
    PNG("PNG", "Measure and report utility command performance."),
    GIF("GIF", "Compare two images using statistics and/or visual differencing");

    // 图片类型
    String name;

    // 类型描述
    String depict;

    ImageType(String name, String depict) {
        this.name = name;
        this.depict = depict;
    }

    public String getName() {
        return name;
    }

    public String getDepict() {
        return depict;
    }
}
