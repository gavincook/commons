package me.gavincook.commons.mail;

/**
 * @author Divers King
 * @date 2018-01-10
 * @since 1.0.0
 **/
public class MailInfoBuilder {

    private MailInfo mailInfo;

    public MailInfoBuilder(){
        mailInfo = new MailInfo();
    }

    public MailInfoBuilder host(String host){
        mailInfo.setHost(host);
        return this;
    }

    public MailInfoBuilder fromMail(String fromMail) {
        mailInfo.setFromMail(fromMail);
        return this;
    }

    public MailInfoBuilder fromPassword(String fromPassword) {
        mailInfo.setFromPassword(fromPassword);
        return this;
    }

    public MailInfoBuilder toMails(String[] toMails) {
        mailInfo.setToMails(toMails);
        return this;
    }

    public MailInfoBuilder ccMails(String[] ccMails) {
        mailInfo.setCcMails(ccMails);
        return this;
    }

    public MailInfoBuilder subject(String subject) {
        mailInfo.setSubject(subject);
        return this;
    }

    public MailInfoBuilder content(String content) {
        mailInfo.setContent(content);
        return this;
    }

    public MailInfoBuilder filePaths(String[] filePaths) {
        mailInfo.setFilePaths(filePaths);
        return this;
    }

    public MailInfoBuilder needSSL(boolean needSSL) {
        mailInfo.setNeedSSL(needSSL);
        return this;
    }

    public MailInfo build(){
        return mailInfo;
    }

}
