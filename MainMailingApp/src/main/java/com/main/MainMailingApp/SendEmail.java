package com.main.MainMailingApp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	
	String[] array;
	public static String readFile() {
		String strLine = " ";
		StringBuffer sb = new StringBuffer();
		try{
			File file = new File("sample.txt");    //creates a new file instance
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
                //constructs a string buffer with no characters

            while((strLine = br.readLine())!=null)
            {
                sb.append(strLine);      //appends line to string buffer
                sb.append("\n");//line feed
            }
                fr.close();    //closes the stream and release the resources
                System.out.println("Contents of File: ");
                // System.out.println(sb.toString());
        }
		catch (Exception e) { // If we get here, there's a problem opening the file
            System.err.println("Error: " + e.getMessage());
        }
		return sb.toString();
	}
	
	// TODO LATER: Create a new class for this
	public static void getReceipients() throws IOException {
		// Reads a file line by line
		FileInputStream fstream = new FileInputStream("emails.txt");
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		ValidateAndVerify demo = new ValidateAndVerify();
		String line;
		
		while((line = br.readLine()) != null) {
			// Checks if email address is valid. If so, add in the recipient list
		}
	}
	
	// Sends the email to receiver's email inbox
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
	
			System.out.println("Email successfully sent...................");
		} 
		catch (Exception e) { // If we got here, an error has been found
			e.printStackTrace();
		}
	}
}
