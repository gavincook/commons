package me.gavincook.commons.util;

import static me.gavincook.commons.util.StringUtils.replaceSensInfo;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import me.gavincook.commons.BaseTest;
import me.gavincook.commons.io.xml.XmlUtil;
import me.gavincook.commons.io.xml.XmlUtilTest.TransReq;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Hinsteny
 * @version $ID: ObjectUtilsTest 2019-05-02 16:03 All rights reserved.$
 */
public class ObjectUtilsTest extends BaseTest {

    /**
     * 对象转化为xml方法测试
     */
    @Test
    public void testIsPrimitive() {
        byte _byte = 11;
        Assert.assertTrue(ObjectUtils.isPrimitive(((Object)_byte).getClass()));
        Assert.assertTrue(ObjectUtils.isPrimitive(Byte.class));
        short _short = 12;
        Assert.assertTrue(ObjectUtils.isPrimitive(((Object)_short).getClass()));
        Assert.assertTrue(ObjectUtils.isPrimitive(Short.class));
        Assert.assertTrue(ObjectUtils.isPrimitive(Integer.class));
        Assert.assertTrue(ObjectUtils.isPrimitive(Long.class));
        Assert.assertTrue(ObjectUtils.isPrimitive(Float.class));
        Assert.assertTrue(ObjectUtils.isPrimitive(Double.class));
        Assert.assertTrue(ObjectUtils.isPrimitive(Boolean.class));
        Assert.assertTrue(ObjectUtils.isPrimitive(Character.class));
    }

}
