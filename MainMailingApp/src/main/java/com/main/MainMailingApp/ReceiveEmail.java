package com.main.MainMailingApp;

import java.io.IOException;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class ReceiveEmail {
	private String host, mailProtocol;
    
    public void retrieveEmail(GetInput input) throws MessagingException, IOException  {
    	// Sets mail properties and configure accordingly
    	host = "pop." + input.getFromHost() + ".com";
        mailProtocol = "pop3";
        
        // Set property values
        Properties properties = new Properties();
        properties.put("mail.pop3.host", host);
        properties.put("mail.pop3.port", "995");
        properties.put("nauk.pop3.starttls.enable", "true");
        
        Session emailSessionObj = Session.getDefaultInstance(properties);
        Store storeObj;
		try {
			// Create POP3 store object and connect with the server
			storeObj = emailSessionObj.getStore("pop3s");
			storeObj.connect(host, input.getFrom(),input.getPassword());
			
			// Create folder object and open it in read-only mode
	        Folder emailFolderObj = storeObj.getFolder("INBOX");
	        emailFolderObj.open(Folder.READ_ONLY);
	        
	        // Fetch messages from the folder and print in a loop
	        Message[] messageObjs = emailFolderObj.getMessages();

	        for (int i = 0, n = messageObjs.length; i < n; i++) {
	        	Message indvidualmsg = messageObjs[i];
	        	System.out.println("Printing individual messages");
	        	System.out.println("No# " + (i + 1));
	        	System.out.println("Email Subject: " + indvidualmsg.getSubject());
	        	System.out.println("Sender: " + indvidualmsg.getFrom()[0]);
	        	System.out.println("Content: " + indvidualmsg.getContent().toString());
	        }
	        emailFolderObj.close(false);
	        storeObj.close();
		} 
		catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
    }
}
