package me.gavincook.commons.io;

import me.gavincook.commons.BaseTest;
import me.gavincook.commons.io.image.ImageInfo;
import me.gavincook.commons.io.image.ImageType;
import me.gavincook.commons.io.image.ImageUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Auther Hinsteny
 * @Desc
 * @Date 2017-08-07
 * @copyright: 2017 All rights reserved.
 */
public class ImagesUtilTest extends BaseTest {

    private static final String IMAGE_TYPE = ".jpg";
    private static final String IMAGE_Folder = "images";
    private static final String TEST_IMAGE = "togepi.jpg";
    private static final String MASK_IMAGE = "togepi.jpg";

    private String getImgSourcePath(String fileName) {
        if (null == fileName || fileName.replace(" +", "").length() == 0)
            fileName = UUID.randomUUID().toString() + IMAGE_TYPE;
        String path = ImagesUtilTest.this.getClass().getResource("").getPath();
        fileName = path.concat(IMAGE_Folder).concat(File.separator).concat(fileName);
        return System.getProperty("os.name").toLowerCase().indexOf("win") > -1 ? fileName.substring(1) : fileName;
    }

    @Test
    public void testScaleImage() throws IOException {
        String sourceFilePath = getImgSourcePath(TEST_IMAGE);
        boolean result = ImageUtil.scale(sourceFilePath, getImgSourcePath(null), 200, 400);
        Assert.assertEquals(true, result);
    }

    @Test
    public void testScaleRatio() throws IOException {
        String sourceFilePath = getImgSourcePath(TEST_IMAGE);
        boolean result = ImageUtil.scaleByRatio(sourceFilePath, getImgSourcePath(null), 2, false);
        Assert.assertEquals(true, result);
    }

    @Test
    public void testScaleSize() throws IOException {
        String sourceFilePath = getImgSourcePath(TEST_IMAGE);
        boolean result = ImageUtil.scaleBySize(sourceFilePath, getImgSourcePath(null), 50, 50, false);
        Assert.assertEquals(true, result);
    }

    @Test
    public void testCut() throws IOException {
        String sourceFilePath = getImgSourcePath(TEST_IMAGE);
        boolean result = ImageUtil.cut(sourceFilePath, getImgSourcePath(null), new ImageUtil.Incise(50, 100, 600, 200));
        Assert.assertEquals(true, result);
    }

    @Test
    public void testConvert() throws IOException {
        String sourceFilePath = getImgSourcePath(TEST_IMAGE);
        boolean result = ImageUtil.convert(sourceFilePath, ImageType.PNG, getImgSourcePath(null));
        Assert.assertEquals(true, result);
    }

    @Test
    public void testGray() throws IOException {
        String sourceFilePath = getImgSourcePath(TEST_IMAGE);
        boolean result = ImageUtil.gray(sourceFilePath, ImageType.PNG, getImgSourcePath(null));
        Assert.assertEquals(true, result);
    }

    @Test
    public void testPressText() throws IOException {
        String sourceFilePath = getImgSourcePath(TEST_IMAGE);
        boolean result = ImageUtil.pressText(sourceFilePath, getImgSourcePath(null),
                new ImageUtil.Text("Hinsteny", new Color(3, 200, 180)), new ImageUtil.MaskPosi(50,180, 0.2F));
        Assert.assertEquals(true, result);
    }

    @Test
    public void testPressImage() throws IOException {
        String sourceFilePath = getImgSourcePath(TEST_IMAGE);
        String maskFilePath = getImgSourcePath(MASK_IMAGE);
        boolean result = ImageUtil.pressImage(maskFilePath, sourceFilePath, getImgSourcePath(null), new ImageUtil.MaskPosi(50,180, 0.2F));
        Assert.assertEquals(true, result);
    }

    @Test
    public void testCreateThumbnail() throws IOException {
        String sourceFilePath = getImgSourcePath(TEST_IMAGE);
        boolean result = ImageUtil.createThumbnail(sourceFilePath, getImgSourcePath(null), 50,50, false);
        Assert.assertEquals(true, result);
    }

    @Test
    public void testReadImgInfo() throws IOException {
        String sourceFilePath = getImgSourcePath(TEST_IMAGE);
        ImageInfo result = ImageUtil.readImgInfo(sourceFilePath);
        Assert.assertNotNull(result);
    }

    @Test
    public void testRotate() throws IOException {
        String sourceFilePath = getImgSourcePath(TEST_IMAGE);
        boolean result = ImageUtil.rotate(sourceFilePath, getImgSourcePath(null), 90);
        Assert.assertEquals(true, result);
    }

}
