package com.main.MainMailingApp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;

public class ValidateAndVerify {
	final String regexForGmail = "^[A-Za-z0-9+.]+@(.+)$";
	final String regexForOutlook = "^[A-Za-z0-9+_.-]+@(.+)$";
	private String username, TLD;

	// Separates the host from the email address for setting up system properties
	public void separateHost(GetInput input, String receiverEmail) {
		Pattern pattern = Pattern.compile("(\\S.*\\S)(@)(\\S.*\\S)(.\\S[a-z]{2,3})");
        Matcher matcherForReceiver = pattern.matcher(receiverEmail);
        Matcher matcherForSender = pattern.matcher(input.getFrom());
        
        if (matcherForReceiver.matches()) {
            System.out.println("Username: " + matcherForReceiver.group(1));
            System.out.println("Hosting Service: " + matcherForReceiver.group(3));
            System.out.println("TLD: " + matcherForReceiver.group(4));
        	username =  matcherForReceiver.group(1);
        	TLD = matcherForReceiver.group(4);
            input.setHost(matcherForReceiver.group(3));
            System.out.println("Hosting Service: " + input.getHost());
        }	
        
        if (matcherForSender.matches()) {
        	System.out.println("SENDER------");
        	System.out.println("Username: " + matcherForSender.group(1));
            System.out.println("Hosting Service: " + matcherForSender.group(3));
            System.out.println("TLD: " + matcherForSender.group(4));
            input.setFromHost(matcherForSender.group(3));
            System.out.println("Hosting Service: " + input.getFromHost());
        }
	}
	
	// Checks if the receipient's email matches to the server's email address requirements
	public boolean validateEmail(String receiverEmail, GetInput input) throws AddressException, UnknownHostException {
		boolean isValidUsername = false;
		
		separateHost(input, receiverEmail);

		if (input.getHost().equalsIgnoreCase("gmail")) {
			isValidUsername = receiverEmail.matches(regexForGmail);
		}
		else if (input.getHost().equalsIgnoreCase("outlook")) {
			isValidUsername = receiverEmail.matches(regexForOutlook);
		}

	    try {
	    	InetAddress inetAddress = InetAddress.getByName(input.getHost() + TLD);
			inetAddress.isReachable(10);
		} 
	    catch (IOException e) {
			e.printStackTrace();
			System.out.println("ERROR: Domain does not exist. Exiting program...");
			System.exit(0);
		}
	    
		if(isValidUsername) {
			System.out.println("Given email-id is valid");
			return true;
		}
		
		System.out.println("Given email-id is not valid");
		return false;
    }
}
