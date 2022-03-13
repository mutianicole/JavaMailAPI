package com.main.MainMailingApp;

import java.net.UnknownHostException;
import java.util.Scanner;

import javax.mail.internet.AddressException;

public class App 
{
	// - Read a file that has the message
	public static void main( String[] args ) throws UnknownHostException { 
		System.out.println("---------- Welcome to iEmail ----------");
		System.out.println("Please login...");
		
		GetInput input = new GetInput();		
		Scanner sc = new Scanner(System.in);

		// Asks for user's email credentials
		System.out.println("-> Email address: ");
		input.setFrom(sc.next());
		System.out.println("-> Password: ");
		input.setPassword(sc.next());
	
		// Takes in user input whether they want to send email, receive email, or exit the program
		// Continues to loop until user picked to exit the program
		while(true) {
			System.out.println("Type '1' to send email, '2' to check your inbox, or '3' to exit");
			String choice = sc.next();
			
			switch(choice) {
			case "1":
				System.out.println("---------- SEND EMAIL OPTION ----------");
				System.out.println("Type below the email address who you'll send this email to...");
				input.setTo(sc.next());
				String receiverEmail = input.getTo();
				
				ValidateAndVerify demo = new ValidateAndVerify();
//				demo.separateHost(input, email);
				
				try {
					boolean isValid = demo.validateEmail(receiverEmail, input);
					if (isValid) SendEmail.sendingEmail("TESTING IF THIS WORKS", "Code Test", input);
					else System.out.println("The email you typed is not valid or does not exist...");
				} 
				catch (AddressException e) {
					e.printStackTrace();
				}
				break;
			case "2": 
				System.out.println("---------- YOUR INBOX ----------");
				break;
			case "3":
				sc.close();
				System.out.println("Thank you for choosing iEmail. Goodbye.....");
				System.exit(0);
			default:
				System.out.println("ERROR: Wrong input. Try again");
				break;
			} // End of switch statement
			System.out.println("------------------------------------------------------------");
		} // End of while loop
    } // End of main  
}
