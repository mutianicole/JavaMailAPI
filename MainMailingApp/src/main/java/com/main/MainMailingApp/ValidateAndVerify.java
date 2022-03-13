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
	
	// Separates the host from the email address for setting up system properties
	public String separateHost(GetInput input, String receiverEmail) {
		Pattern pattern = Pattern.compile("(\\S.*\\S)(@)(\\S.*\\S)(.\\S[a-z]{2,3})");
        Matcher matcherForReceiver = pattern.matcher(receiverEmail);
        Matcher matcherForSender = pattern.matcher(input.getFrom());
        
        if (matcherForReceiver.matches()) {
            System.out.println("Username: " + matcherForReceiver.group(1));
            System.out.println("Hosting Service: " + matcherForReceiver.group(3));
            System.out.println("TLD: " + matcherForReceiver.group(4));
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
        return matcherForReceiver.group(4);
	}
	
	// Checks if the receipient's email matches to the server's email address requirements
	public boolean validateEmail(String receiverEmail, GetInput input) throws AddressException, UnknownHostException {
		boolean result = receiverEmail.matches(regexForGmail);
		
		String TLD = this.separateHost(input, receiverEmail);
		
		InetAddress inetAddress = InetAddress.getByName(input.getHost() + TLD);
	    System.out.println(inetAddress.getHostAddress());
	    try {
			System.out.println(inetAddress.isReachable(100));
		} 
	    catch (IOException e) {
			e.printStackTrace();
		}
	    
		if(result) System.out.println("Given email-id is valid");
		else System.out.println("Given email-id is not valid");
		
        return result;
    }
}
