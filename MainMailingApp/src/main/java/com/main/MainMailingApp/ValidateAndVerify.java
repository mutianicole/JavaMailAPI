package com.main.MainMailingApp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;

public class ValidateAndVerify {
	final String regexForGmail = "^[A-Za-z0-9+.]+@(.+)$";
	final String regexForOutlook = "^[A-Za-z0-9+_.-]+@(.+)$";
	
	// Separates the host from the email address for setting up system properties
	public void getHost(GetInput input, String email) {
		Pattern pattern = Pattern.compile("(\\S.*\\S)(@)(\\S.*\\S)(.\\S[a-z]{2,3})");
        Matcher matcher = pattern.matcher(email);

        if (matcher.matches()) {
            System.out.println("Username: " + matcher.group(1));
            System.out.println("Hosting Service: " + matcher.group(3));
            System.out.println("TLD: " + matcher.group(4));
            input.setHost(matcher.group(3));
            System.out.println("Hosting Service: " + input.getHost());
        }	
	}
	
	// Checks if the email matches to the server's email address requirements
	public boolean validateEmail(String email) throws AddressException {
		boolean result = email.matches(regexForGmail);
		
		if(result) System.out.println("Given email-id is valid");
		else System.out.println("Given email-id is not valid");
		
        return result;
    }
}
