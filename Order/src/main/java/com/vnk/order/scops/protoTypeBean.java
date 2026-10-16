package com.vnk.order.scops;

import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class protoTypeBean {
	private String id = UUID.randomUUID().toString();

	public protoTypeBean() {
		System.out.println("ProtoTypeBean");
	}
	
	public String getId() {
		return id;
	}
}
