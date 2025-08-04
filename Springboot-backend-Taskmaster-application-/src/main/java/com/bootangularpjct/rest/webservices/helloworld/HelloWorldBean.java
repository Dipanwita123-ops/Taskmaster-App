package com.bootangularpjct.rest.webservices.helloworld;

public class HelloWorldBean {

	private String message;

	//constructor
	public HelloWorldBean(String message) {
		this.message = message ;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}



}
