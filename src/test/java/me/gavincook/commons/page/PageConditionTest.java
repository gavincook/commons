package me.gavincook.commons.page;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * PageConditionTest
 *
 * @author gavincook
 * @version $ID: PageConditionTest.java, v0.1 2018-06-11 09:42 gavincook Exp $$
 */
public class PageConditionTest {
    @Test
    public void testGetSort() throws Exception {
        //单字段顺序排序
        PageCondition pageCondition = PageCondition.builder().asc("column1").build();
        Assert.assertEquals(pageCondition.getSort(), "column1 ASC");

        //单字段降序排序
        pageCondition = PageCondition.builder().desc("column1").build();
        Assert.assertEquals(pageCondition.getSort(), "column1 DESC");

        //多字段排序
        pageCondition = PageCondition.builder().desc("column1").desc("column2").build();
        Assert.assertEquals(pageCondition.getSort(), "column1 DESC,column2 DESC");
    }

    @Test
    public void testGetLimit() throws Exception {
        PageCondition pageCondition = PageCondition.builder().limit(15).build();
        Assert.assertEquals(pageCondition.getLimit(), 15);
    }

    @Test
    public void testGetOffset() throws Exception {
        //直接设置offset
        PageCondition pageCondition = PageCondition.builder().offset(15).build();
        Assert.assertEquals(pageCondition.getOffset(), 15);

        //通过页码计算offset
        pageCondition = PageCondition.builder().pageNum(2).limit(15).build();
        Assert.assertEquals(pageCondition.getOffset(), 15);
    }

    @Test
    public void testGetPageNum() throws Exception {
        //直接设置pageNum
        PageCondition pageCondition = PageCondition.builder().pageNum(15).build();
        Assert.assertEquals(pageCondition.getPageNum(), 15);

        //通过offset计算页码
        pageCondition = PageCondition.builder().offset(15).limit(15).build();
        Assert.assertEquals(pageCondition.getPageNum(), 2);
    }
}