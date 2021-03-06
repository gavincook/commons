package me.gavincook.commons.mail;

/**
 * 邮件描述对象
 *
 * @author Divers King
 * @date 2018-01-09 13:16
 * @since 1.0.0
 **/
public class MailInfo {

    /**
     * 发送方的host地址，如： smtp.163.com
     */
    private String host;

    /**
     * 发送方邮件账号
     */
    private String fromMail;

    /**
     * 三方密码
     */
    private String fromPassword;

    /**
     *  接收方
     */
    private String[] toMails;

    /**
     * 抄送方
     */
    private String[] ccMails;

    /**
     * 主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 是否使用ssl方式
     */
    private boolean needSSL = true;

    /**
     * 附件
     */
    private String[] filePaths = new String[]{};

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getFromMail() {
        return fromMail;
    }

    public void setFromMail(String fromMail) {
        this.fromMail = fromMail;
    }

    public String getFromPassword() {
        return fromPassword;
    }

    public void setFromPassword(String fromPassword) {
        this.fromPassword = fromPassword;
    }

    public String[] getToMails() {
        return toMails;
    }

    public void setToMails(String[] toMails) {
        this.toMails = toMails;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String[] getFilePaths() {
        return filePaths;
    }

    public void setFilePaths(String[] filePaths) {
        this.filePaths = filePaths;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getCcMails() {
        return ccMails;
    }

    public void setCcMails(String[] ccMails) {
        this.ccMails = ccMails;
    }

    public boolean isNeedSSL() {
        return needSSL;
    }

    public void setNeedSSL(boolean needSSL) {
        this.needSSL = needSSL;
    }
}
