package com.main.MainMailingApp;

public class GetInput {
	private String Tohost;
	private String fromHost;
	private String message;
	private String subject;
	private String to;
	private String from;
	private String password;

	public String getHost() {
		return Tohost;
	}

	public void setHost(String host) {
		this.Tohost = host;
	}
	
	public String getFromHost() {
		return fromHost;
	}

	public void setFromHost(String fromHost) {
		this.fromHost = fromHost;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
}
