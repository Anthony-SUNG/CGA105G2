package com.waiting.contorller;

import com.core.entity.ErrorTitle;
import com.member.model.Member.dao.impl.MemberDAO;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

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
    private static final String NEW_LINE = System.lineSeparator();

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
                htmlEmail.setStartTLSEnabled(true); // 啟用 TLS 加密
                htmlEmail.setFrom(FROM_USER, "foodMap");
                htmlEmail.setCharset(ENCODING);
            } catch (EmailException var1) {
                logger.error(ErrorTitle.UNKNOWN_TITLE.getTitle("EmailException"), var1);
            }
        }
    }

    public void send(String name, String phone, String mail, String message) {
        initialHtmlMail();
        try {
            StringBuilder html = new StringBuilder();
            html.append("<html>").append(NEW_LINE);
            html.append(String.format("<h1>name :%s</h1>", name)).append(NEW_LINE);
            html.append(String.format("<h1>phone :%s</h1>", phone)).append(NEW_LINE);
            html.append(String.format("<h1>Email :%s</h1>", mail)).append(NEW_LINE);
            html.append(String.format("<h2>message :%s</h2>", message)).append(NEW_LINE);
            html.append("</html>").append(NEW_LINE);
            String htmlTxt = html.toString();
            logger.info(htmlTxt);
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
            logger.info("意見回饋-寄送成功");
        } catch (Exception var14) {
            logger.error(ErrorTitle.UNKNOWN_TITLE.getTitle("Mail錯誤"), var14);
        } finally {
            close();
        }

    }

    public void sendAccount(String name, String mail, String serverName) {
        initialHtmlMail();
        try {
            Integer id = new MemberDAO().srhmail(mail);
            StringBuilder html = new StringBuilder();
            html.append("<table class=\"outlook-table\" style=\"width: 600px; border: 1px solid rgb(206,156,31); text-align: center; margin: 20px auto;\">").append(NEW_LINE);
            html.append("<tr><td>").append(NEW_LINE);
            html.append("<h1 class=\"outlook-h1\">註冊成功</h1>").append(NEW_LINE);
            html.append(String.format("<h1>新會員 %s 您好:</h1>", name)).append(NEW_LINE);
            html.append("<h1>您已成功註冊FoodMap帳號<br>請點擊按鈕開通帳號</h1>").append(NEW_LINE);
            html.append(String.format("<a href=\"http://%s:8081/CGA105G2/LonginServlet?action=open&id=%s\"", serverName, id)).append(NEW_LINE)
                    .append(" class=\"outlook-a\">進入FoodMap</a>").append(NEW_LINE);
            html.append("</td></tr></table>").append(NEW_LINE);
            // send message
            String htmlTxt = toHtml(html.toString());
            logger.info(htmlTxt);
            htmlEmail.addTo(mail, name, ENCODING);
            htmlEmail.setSubject("FoodMap註冊成功通知");
            htmlEmail.addPart(htmlTxt, "text/html;charset=" + ENCODING);
            htmlEmail.setCharset(ENCODING);
            htmlEmail.send();
            logger.info("註冊信-寄送成功");
        } catch (Exception var14) {
            logger.error(ErrorTitle.UNKNOWN_TITLE.getTitle("Mail錯誤"), var14);
        } finally {
            close();
        }
    }

    private String toHtml(String htmlTxt) {
        StringBuilder html = new StringBuilder();
        html.append("<!doctype html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">" +
                "    <title>massage</title>" +
                "    <style>" +
                "        /* Outlook-specific CSS styles */" +
                "        .outlook-table {width: 600px !important;}" +
                "        .outlook-h1 {background-color: #ffb700 !important; margin: 0 !important; padding-top: 15px !important; height: 60px !important; vertical-align: middle !important;}" +
                "        .outlook-a {background-color: #06046a !important; padding: 15px 32px !important; margin: 4px 2px !important; border: none !important; border-radius: 10px !important; color: white !important; text-align: center !important; cursor: pointer !important; display: inline-block !important; font-size: 16px !important; font-family: Lucida Console,serif !important; text-decoration: none !important;}" +
                "    </style>" +
                "</head><body>").append(NEW_LINE);
        html.append(htmlTxt).append(NEW_LINE);
        html.append("</body>" +
                "</html>");
        return html.toString();
    }

    public static void close() {
        htmlEmail = null;
    }

}
