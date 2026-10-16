package com.vnk.order.globalexception;

@SuppressWarnings("serial")
public class OrderException extends RuntimeException {
	public OrderException(String message,Throwable cause) {
		super(message, cause);
	}
	
	public OrderException(String message) {
		super(message);
	}
}
