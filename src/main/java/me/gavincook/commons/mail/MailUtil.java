package me.gavincook.commons.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * 邮件发送工具类
 *
 * @author Divers King
 * @date 2018-01-09 15:06
 * @since 1.0.0
 **/
public class MailUtil {

    /**
     * mail.user=tanghong@xheart.cn
     mail.password=123456T
     mail.host=smtp.exmail.qq.com
     */

    private static String mailHost = "smtp.163.com";

    private static String mailUser = "lywber@163.com";

    private static String mailPassword = "lywb530";


    public void send(String receiver,String subject,String content) throws MessagingException, UnsupportedEncodingException {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", mailHost);
        props.put("mail.debug", "true");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailUser,mailPassword);
            }
        });

        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(mailUser));
        String[] arr = receiver.split("，|,");
        InternetAddress[] address = new InternetAddress[arr.length];
        for (int i = 0; i < arr.length; i++) {
            address[i] = new InternetAddress(arr[i]);
        }
        msg.addRecipients(Message.RecipientType.TO, address);
        msg.setSubject(MimeUtility.encodeWord(subject, "UTF-8", "B"));
        msg.setSentDate(new Date());
        msg.setContent(content,"text/html;charset=utf-8");
        Transport.send(msg);

    }

    public static void main(String[] args) throws UnsupportedEncodingException, MessagingException {
        MailUtil m = new MailUtil();
        m.send("564543626@qq.com","测试","this is a test email.");
    }
}
