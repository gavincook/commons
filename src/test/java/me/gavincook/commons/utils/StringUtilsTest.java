package me.gavincook.commons.utils;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static me.gavincook.commons.utils.StringUtils.replaceSensInfo;

/**
 * @author Hinsteny
 * @date 2017-11-20
 * @copyright: 2017 All rights reserved.
 */
public class StringUtilsTest {

    @Test
    public void testReplaceSensInfo() throws IOException {
        Assert.assertEquals("", replaceSensInfo(null, 5, 10, '*'));
        Assert.assertEquals("12345**", replaceSensInfo("1234567890", 5, 7, '*'));
        Assert.assertEquals("12345*****", replaceSensInfo("1234567890", 5, 10, '*'));
        Assert.assertEquals("12345*****", replaceSensInfo("1234567890", 5, 15, '*'));
    }

}
