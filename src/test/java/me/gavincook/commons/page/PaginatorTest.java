package me.gavincook.commons.page;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * PaginatorTest
 *
 * @author gavincook
 * @version $ID: PaginatorTest.java, v0.1 2018-06-11 10:01 gavincook Exp $$
 */
public class PaginatorTest {

    /**
     * 空数据分页对象
     * @throws Exception
     */
    @Test
    public void testOfEmpty() throws Exception {
        Paginator paginator = Paginator.ofEmpty(15);
        Assert.assertEquals(paginator.getCurrentPageNum(), 1);
        Assert.assertEquals(paginator.getPageSize(), 15);
    }

    /**
     * 分页条件构造分页对象
     * @throws Exception
     */
    @Test
    public void testPageConditionConstruct() throws Exception {
        PageCondition pageCondition = PageCondition.builder().offset(0).limit(15).build();
        List<String> data = new ArrayList<>();
        Paginator<String> paginator = new Paginator<>(200, data, pageCondition);
        Assert.assertEquals(paginator.getPageSize(), pageCondition.getLimit());
        Assert.assertEquals(paginator.getItems(), data);
        Assert.assertEquals(paginator.getPreviousPageNum(), 1);
        Assert.assertEquals(paginator.getNextPageNum(), 2);
        Assert.assertEquals(paginator.getCurrentPageNum(), 1);
    }

    /**
     * 从已有的分页对象构建新的分页对象
     * @throws Exception
     */
    @Test
    public void testPaginatorConstruct() throws Exception {
        PageCondition pageCondition = PageCondition.builder().offset(0).limit(15).build();
        List<String> data = new ArrayList<>();
        Paginator<String> old = new Paginator<>(200, data, pageCondition);

        Paginator<String> paginator = new Paginator<>(old, data);
        Assert.assertEquals(paginator.getPageSize(), pageCondition.getLimit());
        Assert.assertEquals(paginator.getItems(), data);
        Assert.assertEquals(paginator.getPreviousPageNum(), 1);
        Assert.assertEquals(paginator.getNextPageNum(), 2);
        Assert.assertEquals(paginator.getCurrentPageNum(), 1);
    }

    /**
     * 通过页码和页面大小构建分页对象
     * @throws Exception
     */
    @Test
    public void testPageNumAndPageSizeConstruct() throws Exception {
        List<String> data = new ArrayList<>();
        Paginator<String> old = new Paginator<>(200, data, 1, 15);

        Paginator<String> paginator = new Paginator<>(old, data);
        Assert.assertEquals(paginator.getPageSize(), 15);
        Assert.assertEquals(paginator.getItems(), data);
        Assert.assertEquals(paginator.getPreviousPageNum(), 1);
        Assert.assertEquals(paginator.getNextPageNum(), 2);
        Assert.assertEquals(paginator.getCurrentPageNum(), 1);
    }
}