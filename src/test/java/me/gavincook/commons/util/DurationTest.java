package me.gavincook.commons.util;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 时间段测试
 *
 * @author GavinCook
 * @version $Id: DurationTest.java, v0.0.1 2017-07-26 下午5:51 GavinCook Exp $$
 */
public class DurationTest {

    @Test
    public void testOfNanos() {
        Assert.assertEquals(Duration.ofNanos(1000L).toNanos(), 1000L);
        Assert.assertEquals(Duration.ofNanos(1000_000_000L).toNanos(), 1000_000_000L);
        Assert.assertEquals(Duration.ofNanos(-1000L).toNanos(), -1000L);
        Assert.assertEquals(Duration.ofNanos(1000_000_000L).toSeconds(), 1L);
        Assert.assertEquals(Duration.ofNanos(1000_000_000L).toMillis(), 1000L);
    }

    @Test
    public void testOfMillis() {
        Assert.assertEquals(Duration.ofMillis(1000L).toMillis(), 1000L);
        Assert.assertEquals(Duration.ofMillis(1000_000_000L).toMillis(), 1000_000_000L);
        Assert.assertEquals(Duration.ofMillis(-1000L).toMillis(), -1000L);
    }

    @Test
    public void testOfSeconds() {
        Assert.assertEquals(Duration.ofSeconds(1L).toNanos(), 1000_000_000L);
        Assert.assertEquals(Duration.ofSeconds(3600L).toHours(), 1L);
        Assert.assertEquals(Duration.ofSeconds(-1000L).toSeconds(), -1000L);
    }

    @Test
    public void testOfMinutes() {
        Assert.assertEquals(Duration.ofMinutes(1L).toSeconds(), 60L);
        Assert.assertEquals(Duration.ofMinutes(60L).toHours(), 1L);
        Assert.assertEquals(Duration.ofMinutes(24 * 60).toDays(), 1L);
        Assert.assertEquals(Duration.ofMinutes(-1000L).toMinutes(), -1000L);
    }

    @Test
    public void testOfHours() {
        Assert.assertEquals(Duration.ofHours(1L).toSeconds(), 3600L);
        Assert.assertEquals(Duration.ofHours(24).toDays(), 1L);
        Assert.assertEquals(Duration.ofHours(-1000L).toHours(), -1000L);
    }

    @Test
    public void testOfDays() {
        Assert.assertEquals(Duration.ofDays(1L).toHours(), 24L);
        Assert.assertEquals(Duration.ofDays(1L).toSeconds(), 24 * 60 * 60);
        Assert.assertEquals(Duration.ofDays(-1000L).toDays(), -1000L);
    }

    @Test
    public void testIsNegative() {
        Assert.assertTrue(Duration.ofDays(-1L).isNegative());
        Assert.assertFalse(Duration.ofDays(1L).isNegative());
    }
}
