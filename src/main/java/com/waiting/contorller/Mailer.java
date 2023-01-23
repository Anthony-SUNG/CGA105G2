package com.waiting.contorller;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {
	
	// sender 123
	private final String user = "zxchuahua2417@gmail.com";
	private final String pass = "lpmwmxsueszwtxfk";
	// to
	private final String costomer = "zxchuahua2417@gmail.com";


	public void send(String name, String phone, String mail, String message) {

		// Get the session object
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");

		// host: smtp.gmail.com
		props.setProperty("mail.host", "smtp.gmail.com");

		// host port 465
		props.put("mail.smtp.port", "465");

		// 帳號驗證
		props.put("mail.smtp.auth", "true");

		// javaMail 實作SSL連線
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		// set port 465
		props.put("mail.smtp.socketFactory.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, pass);
			}
		});

		try {
			// compose message
			MimeMessage sendmsg = new MimeMessage(session);
//			寄件
			sendmsg.setSender(new InternetAddress(user));
			sendmsg.setFrom(new InternetAddress(user));

			// 收件者
			sendmsg.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(costomer));

			sendmsg.setSubject(name + "的意見回饋");
			sendmsg.setContent("<h1>name :" + name + "</h1>" + "<h1>phone :" + phone + "</h1>" + "<h1>Email :" + mail
					+ "</h1>" + "<h2>message :" + message + "</h2>", "text/html;charset=UTF-8");

			// send message
			Transport transport = session.getTransport();

			Transport.send(sendmsg);
			transport.close();

			System.out.println("Done");

		} catch (AddressException e) {
			e.printStackTrace();
		}

		catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}
}
