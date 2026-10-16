package com.vnk.order.scops;

import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "singleton")
public class SingleTonBean {
	private String id = UUID.randomUUID().toString();

	public SingleTonBean() {
		System.out.println("SingleTonBean");
	}
	
	public String getId() {
		return id;
	}
}
