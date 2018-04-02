package me.gavincook.commons.mail;

import me.gavincook.commons.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Date;

/**
 * @author Divers King
 * @date 2018-01-09 15:11
 * @since 1.0.0
 **/
public class MailUtilTest extends BaseTest  {

    public static String fromMail = "564543626@qq.com"; // "market@aichainnews.com";
    public static String password = "gpqgmjgqmmqybdcb";  // "EasyVaas2018"
    public static String host = "smtp.qq.com";   // smtp.qq.com  // smtp.163.com  //smtp.exmail.qq.com
    public static String toMail = "lywber@163.com";
    public static String attachment_1 = MailUtilTest.class.getClassLoader().getResource("mail/attachment_1.txt").getPath();
    public static String attachment_2 = MailUtilTest.class.getClassLoader().getResource("mail/attachment_2.txt").getPath();

    private MailInfo createMailInfo(){
        MailInfo mailInfo = new MailInfoBuilder()
                .host(host)
                .fromMail(fromMail)
                .fromPassword(password)
                .toMails(new String[]{toMail, "1027102799@qq.com"})
                .ccMails(new String[]{"651551319@qq.com"})
                .subject("关于2017至2018年度寒假通知" + new Date())
                .content("经研究决定，放假日期从1月24日~2月28日。请各位老师做好工作安排。")
                .filePaths(new String[]{attachment_1, attachment_2})
                .needSSL(true)
                .build();

        return mailInfo;
    }

    @Test
    public void testScaleImage() throws IOException {
        MailInfo mailInfo = createMailInfo();
        boolean result = MailUtil.send(mailInfo);
        Assert.assertEquals(true, result);
    }

}
