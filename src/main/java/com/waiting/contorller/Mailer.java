package com.waiting.contorller;

import com.core.entity.ErrorTitle;
import com.member.model.Member.dao.impl.MemberDAO;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.MessagingException;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;

public class Mailer {
    public static final Logger logger = LogManager.getLogger(Mailer.class);
    private static String propertiesFile;
    private static final String SMTP_HOST_NAME;
    private static final String SMTP_PORT;
    private static final String USER_NAME;
    private static final String PASSWORD;
    private static final String FROM_USER;
    private static final String ENCODING;
    private static HtmlEmail htmlEmail;

    static {
        setPropertiesPath();
        MailPropertyBuilder builder = new MailPropertyBuilder(propertiesFile);
        ENCODING = builder.getEmailEncoding();
        SMTP_HOST_NAME = builder.getSmtpHostName();
        SMTP_PORT = builder.getSmtpPort();
        USER_NAME = builder.getUserName();
        PASSWORD = builder.getUserPassword();
        FROM_USER = builder.getFromUser();
    }

    public static synchronized void setPropertiesPath() {
        try {
            File file = new File(System.getProperty("user.dir") + File.separator + "mail.properties");
            if (!file.exists()) {
                logger.warn(String.format("未找到跟目錄的mail.properties:%s", file));
                file = toInvoke();
                logger.info(String.format("改使用路徑:%s", file));
            }
            propertiesFile = file.getAbsolutePath();
        } catch (URISyntaxException var4) {
            logger.error(ErrorTitle.UNKNOWN_TITLE.getTitle("setPropertiesPath"), var4);
        }
    }

    public static File toInvoke() throws URISyntaxException {
        URI uri = MailPropertyBuilder.class.getProtectionDomain().getCodeSource().getLocation().toURI();
        File f = new File(uri.toString().replaceFirst("file:", ""));
        String adminPath = f.getParent() + File.separator + "mail.properties";
        return new File(adminPath);
    }

    public static void initialHtmlMail() {
        if (htmlEmail == null) {
            try {
                htmlEmail = new HtmlEmail();
                htmlEmail.setHostName(SMTP_HOST_NAME);
                htmlEmail.setSmtpPort(Integer.parseInt(SMTP_PORT));
                htmlEmail.setAuthenticator(new DefaultAuthenticator(USER_NAME, PASSWORD));
                htmlEmail.setFrom(FROM_USER, "foodMap", ENCODING);
                htmlEmail.setCharset(ENCODING);
            } catch (EmailException var1) {
                logger.error(ErrorTitle.UNKNOWN_TITLE.getTitle("EmailException"), var1);
            }
        }
    }

    public void send(String name, String phone, String mail, String message) {
        try {
            StringBuilder html = new StringBuilder();
            html.append(String.format("<h1>name :%s</h1>"
                    + "<h1>phone :%s</h1>"
                    + "<h1>Email :%s</h1>"
                    + "<h2>message :%s</h2>", name, phone, mail, message));
            String htmlTxt = html.toString();
            htmlEmail.addTo(mail, name, ENCODING);
            htmlEmail.setSubject(name + "的意見回饋");
            htmlEmail.addPart(htmlTxt, "text/html;charset=" + ENCODING);

//            //附件
//            EmailAttachment attachment = new EmailAttachment();
//            File file = new File(System.getProperty("user.dir") + SPT + "log" + SPT + "TEJ_PROJECT.log");
//            logger.info(file);
//            if (file.exists()) {
//                attachment.setPath(file.getAbsolutePath());
//                attachment.setDisposition("attachment");
//                attachment.setDescription("附件");
//                attachment.setName(MimeUtility.encodeText(file.getName()));
//                htmlEmail.attach(attachment);
//            }
            htmlEmail.setCharset(ENCODING);
            htmlEmail.send();
        } catch (EmailException e) {
            logger.error(ErrorTitle.UNKNOWN_TITLE.getTitle("EmailException"), e);
        }

    }

    public void sendAccount(String name, String mail, String serverName) {
        try {
            Integer id = new MemberDAO().srhmail(mail);
            StringBuilder html = new StringBuilder();
            html.append("<div style='width:600px;height:350px;border:1px solid rgba(128,128,128,0.4);text-align:center;margin:20px auto;'>")
                    .append("<h1 style='background-color:rgba(255,183,0,0.956);margin:0;padding-top:15px;height:60px;vertical-align:middle;'>註冊成功</h1>")
                    .append(name).append(String.format("<h1>新會員 %s 您好:</h1>", name))
                    .append("<h1>您已成功註冊FoodMap帳號<br>請點擊按鈕開通帳號</h1>")
                    .append(String.format("<a href='http://%s:8081/CGA105G2/LonginServlet?action=open&id=%s'", serverName, id))
                    .append("style='display:block;text-decoration:none;background-color:rgba(6,4,106,0.956);"+
                            "padding:15px 32px;margin:4px 2px;border:none;border-radius:10px;color:white;text-align:center;cursor:pointer;"+
                            "display:inline-block;font-size:16px;font-family:Lucida Console;'>進入FoodMap</a></div>");
            // send message
            String htmlTxt = html.toString();
            htmlEmail.addTo(mail, name, ENCODING);
            htmlEmail.setSubject("FoodMap註冊成功通知");
            htmlEmail.addPart(htmlTxt, "text/html;charset=" + ENCODING);
            htmlEmail.setCharset(ENCODING);
            htmlEmail.send();
        } catch (EmailException e) {
            logger.error(ErrorTitle.UNKNOWN_TITLE.getTitle("EmailException"), e);
        }

    }

}
