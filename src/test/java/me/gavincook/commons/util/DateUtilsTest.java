package me.gavincook.commons.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * {@link DateUtils} 单元测试
 * @author GavinCook
 * @version $Id: DateUtilsTest.java, v0.0.1 2017-07-26 上午11:34 GavinCook Exp $$
 */
public class DateUtilsTest {

    @Test
    public void testPlusYears() {
        Date date = new Date();
        Date newDate = DateUtils.plusYears(date, 1);
        Assert.assertEquals(date.getYear(), newDate.getYear() - 1);
    }

    @Test
    public void testPlusMonths() {
        Date date = new Date();
        int months = 0;
        Date newDate = DateUtils.plusMonths(date, months);
        Assert.assertEquals(date, newDate);

        months = 2;
        newDate = DateUtils.plusMonths(date, months);
        Assert.assertEquals((date.getMonth() + months) % 12, newDate.getMonth());

        months = -2;
        newDate = DateUtils.plusMonths(date, months);
        Assert.assertEquals(((date.getMonth() + months) % 12 + 12) % 12, newDate.getMonth());

        months = 13;
        newDate = DateUtils.plusMonths(date, months);
        Assert.assertEquals((date.getMonth() + months) % 12, newDate.getMonth());

        months = -13;
        newDate = DateUtils.plusMonths(date, months);
        Assert.assertEquals(((date.getMonth() + months) % 12 + 12) % 12, newDate.getMonth());
    }

    @Test
    public void testPlusWeeks() {
        Date date = new Date();
        Date newDate = DateUtils.plusWeeks(date, 1);
        Assert.assertEquals(date.getDay(), newDate.getDay());

        newDate = DateUtils.plusWeeks(date, 60);
        Assert.assertEquals(date.getDay(), newDate.getDay());

        newDate = DateUtils.plusWeeks(date, 0);
        Assert.assertEquals(date.getDay(), newDate.getDay());

        newDate = DateUtils.plusWeeks(date, -1);
        Assert.assertEquals(date.getDay(), newDate.getDay());

        newDate = DateUtils.plusWeeks(date, -60);
        Assert.assertEquals(date.getDay(), newDate.getDay());
    }

    @Test
    public void testPlusDays() {
        Date date = new Date();
        Date newDate = DateUtils.plusDays(date, 1);
        Assert.assertEquals(DateUtils.between(date, newDate).toDays(), 1);

        newDate = DateUtils.plusDays(date, 365);
        Assert.assertEquals(DateUtils.between(date, newDate).toDays(), 365);

        newDate = DateUtils.plusDays(date, 366);
        Assert.assertEquals(DateUtils.between(date, newDate).toDays(), 366);

        newDate = DateUtils.plusDays(date, 0);
        Assert.assertEquals(DateUtils.between(date, newDate).toDays(), 0);

        newDate = DateUtils.plusDays(date, -1);
        Assert.assertEquals(DateUtils.between(date, newDate).toDays(), -1);

        newDate = DateUtils.plusDays(date, -365);
        Assert.assertEquals(DateUtils.between(date, newDate).toDays(), -365);

        newDate = DateUtils.plusDays(date, -366);
        Assert.assertEquals(DateUtils.between(date, newDate).toDays(), -366);
    }

    @Test
    public void testFormat() throws InterruptedException {
        Calendar calendar = Calendar.getInstance();
        //创建时间：2017-07-27 10:00:00.123，注意：月份是从0开始
        calendar.set(2017, 6, 27, 10, 0, 0);
        calendar.set(Calendar.MILLISECOND, 123);

        final Date date = calendar.getTime();
        Assert.assertEquals(DateUtils.format(date, "yyyy-MM-dd"), "2017-07-27");
        Assert.assertEquals(DateUtils.format(date, "yyyy-MM-dd HH:mm:ss.SSS"), "2017-07-27 10:00:00.123");
        Assert.assertEquals(DateUtils.format(date, "mm"), "00");

        /************* 以下为模拟多线程并发场景（10个线程，执行100个任务）***************************/
        ExecutorService service = Executors.newFixedThreadPool(10);
        final AtomicInteger reallyExecuteTimes = new AtomicInteger(0);
        int executeTimes = 100;

        Runnable task = new Runnable() {
            @Override
            public void run() {
                Assert.assertEquals(DateUtils.format(date, "yyyy-MM-dd"), "2017-07-27");
                Assert.assertEquals(DateUtils.format(date, "yyyy-MM-dd HH:mm:ss.SSS"), "2017-07-27 10:00:00.123");
                reallyExecuteTimes.incrementAndGet();
            }
        };

        while (executeTimes-- > 0) {
            service.submit(task);
        }
        service.shutdown();
        service.awaitTermination(10, TimeUnit.SECONDS);

        //只需要检查执行次数是否deng等于100，等于100表示所有的task中的断言都通过了
        Assert.assertEquals(reallyExecuteTimes.get(), 100);
    }

    //@Test(expectedExceptions = IllegalArgumentException.class)
    public void testParse_DateStrIsNull() throws ParseException {
        DateUtils.parse(null, "yyyy-MM");
    }

    //@Test(expectedExceptions = IllegalArgumentException.class)
    public void testParse_PatternIsNull() throws ParseException {
        DateUtils.parse("2017-07-27", null);
    }

    @Test(expectedExceptions = ParseException.class)
    public void testParse_parseException() throws ParseException {
        DateUtils.parse("2017-07-27", "yyyy-MM");
    }

    @Test
    public void testParse() throws ParseException, InterruptedException {
        Calendar calendar = new GregorianCalendar(2017, 6, 27);
        final Date date = calendar.getTime();

        Assert.assertEquals(DateUtils.parse("2017-07-27", "yyyy-MM-dd"), date);
        Assert.assertEquals(DateUtils.parse("20170727", "yyyyMMdd"), date);

        /************* 以下为模拟多线程并发场景（10个线程，执行100个任务）***************************/
        ExecutorService service = Executors.newFixedThreadPool(10);
        final AtomicInteger reallyExecuteTimes = new AtomicInteger(0);
        int executeTimes = 100;

        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    Assert.assertEquals(DateUtils.parse("2017-07-27", "yyyy-MM-dd"), date);
                    Assert.assertEquals(DateUtils.parse("20170727", "yyyyMMdd"), date);
                    reallyExecuteTimes.incrementAndGet();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        };

        while (executeTimes-- > 0) {
            service.submit(task);
        }
        service.shutdown();
        service.awaitTermination(10, TimeUnit.SECONDS);

        //只需要检查执行次数是否deng等于100，等于100表示所有的task中的断言都通过了
        Assert.assertEquals(reallyExecuteTimes.get(), 100);
    }
}
