package me.gavincook.commons.mail;

import me.gavincook.commons.io.Charset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

/**
 * 邮件发送工具类
 *
 * @author Divers King
 * @date 2018-01-10
 * @since 1.0.0
 **/
public class MailUtil {

    private static final Logger logger = LoggerFactory.getLogger(MailUtil.class);

    /**
     * 发送邮件
     * @param mailInfo 邮件信息
     * @return boolean
     */
    public static boolean send(final MailInfo mailInfo) {
        boolean result = true;
        Properties props = new Properties();

        if (mailInfo.isNeedSSL()) {
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
            props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.socketFactory.port", "465");
        }

        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", mailInfo.getHost());
        props.setProperty("mail.smtp.auth", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailInfo.getFromMail(), mailInfo.getFromPassword());
            }
        });
        session.setDebug(true);

        MimeMessage msg = createMimeMessage(session, mailInfo);
        try {
            // 伪装一下
            msg.setHeader("X-Mailer","Microsoft Outlook Express 6.00.2900.2869");
            msg.setFrom(new InternetAddress(mailInfo.getFromMail()));
            Transport.send(msg);
        } catch (MessagingException e) {
            result = false;
            logger.error("send email error, {}", e);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 构造邮件内容
     * @param session session
     * @param mailInfo mainInfo
     * @return eil
     */
    private static MimeMessage createMimeMessage(Session session, MailInfo mailInfo){
        MimeMessage msg = new MimeMessage(session);
        try {
            //设置发送方
            msg.setFrom(new InternetAddress(mailInfo.getFromMail(), "USER_AA", Charset.UTF8.charsetName()));

            String[] toMails = mailInfo.getToMails();
            InternetAddress[] address = new InternetAddress[toMails.length];
            for (int i = 0; i < toMails.length; i++) {
                address[i] = new InternetAddress(toMails[i]);
            }
            msg.setRecipients(MimeMessage.RecipientType.TO, address);

            //主题
            msg.setSubject(mailInfo.getSubject(), Charset.UTF8.charsetName());

            //邮件主体
            MimeMultipart mainPart = new MimeMultipart();
            mainPart.setSubType("mixed");

            //邮件内容节点
            MimeBodyPart text = new MimeBodyPart();
            text.setContent(mailInfo.getContent(), "text/html;charset=UTF-8");
            mainPart.addBodyPart(text);

            //添加附件
            mainPart = addAttachments(mainPart, mailInfo.getFilePaths());

            msg.setContent(mainPart);
            msg.setSentDate(new Date());
        } catch (MessagingException e) {
            logger.error("create email error, {}", e);
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 添加附件
     * @param mainPart 邮件主体
     * @param filePaths 附件地址
     */
    private static MimeMultipart addAttachments(MimeMultipart mainPart, String[] filePaths) {
        MimeBodyPart attachment;
        DataHandler dh2;
        for (String filePath : filePaths) {
            attachment = new MimeBodyPart();
            // 添加文件
            dh2 = new DataHandler(new FileDataSource(filePath));

            // 将附件数据添加到“节点”, 再把节点添加到邮件主体
            try {
                attachment.setDataHandler(dh2);
                attachment.setFileName(MimeUtility.encodeText(dh2.getName()));
                mainPart.addBodyPart(attachment);
            } catch (Exception e) {
                logger.error("add attachment error, {}", e);
                e.printStackTrace();
            }
        }
        return mainPart;
    }

}
