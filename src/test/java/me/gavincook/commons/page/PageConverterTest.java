package me.gavincook.commons.page;

import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * PageConverterTest
 *
 * @author gavincook
 * @date 2018-09-17 22:19
 * @since 1.0.0
 */
public class PageConverterTest {

    @Test
    public void testConvert() {
        PageCondition pageCondition = new PageCondition.Builder().build();
        List<Integer> oldPageData = new ArrayList<>();
        oldPageData.add(1);
        oldPageData.add(2);
        Paginator<Integer> paginator = new Paginator<>(pageCondition.getLimit(), oldPageData, pageCondition);
        Paginator<String> newPaginator = PageConverter.convert(paginator, (olds) -> {
            List<String> news = new ArrayList<>();
            olds.forEach(integer -> news.add(String.valueOf(integer)));
            return news;
        });

        Assert.assertEquals(newPaginator.getItems().size(), oldPageData.size());
    }

    @Test
    public void testConvert1() {
        PageCondition pageCondition = new PageCondition.Builder().build();
        List<Integer> oldPageData = new ArrayList<>();
        oldPageData.add(1);
        Paginator<Integer> paginator = new Paginator<>(pageCondition.getLimit(), oldPageData, pageCondition);
        PageConverter.convert(paginator, (olds) -> {
            List<String> news = new ArrayList<>();
            olds.forEach(String::valueOf);
            return news;
        }, (item) -> {
            Assert.assertEquals(item, "1");
        });
    }
}