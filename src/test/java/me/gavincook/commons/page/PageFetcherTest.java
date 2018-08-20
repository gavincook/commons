package me.gavincook.commons.page;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * PageFetcherTest
 *
 * @author gavincook
 * @version $ID: PageFetcherTest.java, v0.1 2018-08-20 22:44 gavincook Exp $$
 */
public class PageFetcherTest {

    @Test
    public void testFetchPageData() throws Exception {

        PageCondition pageCondition = new PageCondition.Builder().build();
        Paginator<String> paginator = PageFetcher.fetchPageData(pageCondition, () -> 10L, () -> {
            List<String> ret = new ArrayList<>();
            ret.add("1");
            return ret;
        }, list -> list);

        Assert.assertEquals(paginator.getCurrentPageNum(), 1);
        Assert.assertEquals(paginator.getItems().size(), 1);
    }

    /**
     * dataConverter为空的异常测试
     * @throws Exception
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFetchPageDataForDataConverterNull() throws Exception {

        PageCondition pageCondition = new PageCondition.Builder().build();

        Paginator<String> paginator = PageFetcher.fetchPageData(pageCondition, () -> 10L, () -> {
            List<String> ret = new ArrayList<>();
            ret.add("1");
            return ret;
        }, null);

    }
}