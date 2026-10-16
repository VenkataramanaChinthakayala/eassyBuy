package com.vnk.order.scops;

import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST,
               proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestBean {
	private String id = UUID.randomUUID().toString();

	public RequestBean() {
		System.out.println("RequestBean");
	}
	
	public String getId() {
		return id;
	}
}
