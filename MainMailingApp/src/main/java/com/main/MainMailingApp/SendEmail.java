package com.main.MainMailingApp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	
	public static String readFile() {
		String strLine = " ";
		try{
            // Open the file that is the first 
            // command line parameter
            FileInputStream fstream = new FileInputStream("sample.txt");
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

          //Read File Line By Line
            String line;
            
            while ((line = br.readLine()) != null)   {
                // Print the content on the console
                strLine += line;
            }
            System.out.println (strLine);
            //Close the input stream
            in.close();
        }
		catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
		return strLine;
	}
	
	static void sendingEmail(String subject, GetInput input) {
		final String body = readFile();
		System.out.println(body);
		
		final String password = input.getPassword();
		String host = "smtp." + input.getFromHost() + ".com";

		// Gets the system properties
		Properties properties = System.getProperties();

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
	
			// Adds recipient's email to the email
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(input.getTo()));
	
			// Adds email subject to email
			m.setSubject(subject);
	
	
			// Adds message to the email
			m.setText(body);
	
			// Sends the email using Transport class
			Transport.send(m);
	
			System.out.println("Email sent success...................");
		} 
		catch (Exception e) { // If we got here, an error has been found
			e.printStackTrace();
		}
	}
}
