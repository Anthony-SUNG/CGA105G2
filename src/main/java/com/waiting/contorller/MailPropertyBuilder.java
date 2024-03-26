package com.waiting.contorller;

import com.core.common.Common;
import com.core.entity.ErrorTitle;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class MailPropertyBuilder extends Common {
    private String userName;
    private String userPassword;
    private String smtpHostName;
    private String smtpPort;
    private String emailEncoding;
    private String fromUser;

    public MailPropertyBuilder(String file) {
        Properties prop = new Properties();
        try (InputStream is = Files.newInputStream(Paths.get(file))) {
            prop.load(is);
            setSmtpHostName(prop.getProperty("smtpHostName"));
            setSmtpPort(prop.getProperty("smtpPort"));
            setFromUser(prop.getProperty("fromUser"));
            setEmailEncoding(prop.getProperty("emailEncoding"));
            setUserName(prop.getProperty("userName"));
            setUserPassword(prop.getProperty("userPass"));
        } catch (Exception var3) {
            logger.error(ErrorTitle.UNKNOWN_TITLE.getTitle("MailPropertyBuilder"),var3);
        }
    }

    protected String getUserName() {
        return userName;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

    protected String getUserPassword() {
        return userPassword;
    }

    private void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    protected String getSmtpHostName() {
        return smtpHostName;
    }

    private void setSmtpHostName(String smtpHostName) {
        this.smtpHostName = smtpHostName;
    }

    protected String getSmtpPort() {
        return smtpPort;
    }

    private void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    protected String getEmailEncoding() {
        return emailEncoding;
    }

    private void setEmailEncoding(String emailEncoding) {
        this.emailEncoding = emailEncoding;
    }

    protected String getFromUser() {
        return fromUser;
    }

    private void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

}
