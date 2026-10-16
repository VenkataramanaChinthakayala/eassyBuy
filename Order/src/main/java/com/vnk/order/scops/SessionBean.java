package com.vnk.order.scops;

import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION,
               proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionBean {
	private String id = UUID.randomUUID().toString();

	public SessionBean() {
		System.out.println("SessionBean");
	}
	
	public String getId() {
		return id;
	}
}
