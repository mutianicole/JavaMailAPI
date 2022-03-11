package com.main.MainMailingApp;

public class App 
{
	// Ask users for their email, password, subject, recipient's email
	// Create private variables, getters, and setters
	// TODO LATER: - Figure out how to set the host variable without hard coding it
	// - Read a file that has the message
	
	
    public static void main( String[] args ) { 
    	//SendEmail send = new SendEmail();
    	SendEmail.sendingEmail("TESTING IF THIS WORKS", "Code Test", "shad@binarylogicit.com", "myDemoEmail.Sushi@gmail.com");
    }  
}
