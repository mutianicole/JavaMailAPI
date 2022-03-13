package com.main.MainMailingApp;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	static void sendingEmail(String message, String subject, GetInput input) {
		final String password = input.getPassword();
		String host = "smtp." + input.getHost() + ".com";

		// Gets the system properties
		Properties properties = System.getProperties();
		//System.out.println("PROPERTIES " +properties);

		// Sets important information to properties object
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port","465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");

		// Gets the session object
		Session session=Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("myDemoEmail.Sushi@gmail.com", password);
			}
		});

		session.setDebug(true);

		// Compose the message
		MimeMessage m = new MimeMessage(session);

		try {
			
			// Sets the sender's email 
			m.setFrom(input.getFrom());
	
			// Adds recipient's email to the message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(input.getTo()));
	
			// Adds email subject to message
			m.setSubject(subject);
	
	
			// Adds text to message
			m.setText(message);
	
			// Sends the message using Transport class
			Transport.send(m);
	
			System.out.println("Email sent success...................");
		} 
		catch (Exception e) { // If we got here, an error has been found
			e.printStackTrace();
		}
	}
}
