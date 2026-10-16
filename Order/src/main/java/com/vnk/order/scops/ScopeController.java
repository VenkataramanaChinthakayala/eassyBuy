package com.vnk.order.scops;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class ScopeController {
	private final SingleTonBean singleTonBean;
	private final ObjectProvider<protoTypeBean> provider;
	private final RequestBean requestBean;
	private final SessionBean sessionBean;

	@GetMapping("/singleton")
	public String singleTonBean() {
		return singleTonBean.getId();
	}
	
	@GetMapping("/prototype")
	public String rotoTypeBean() {
		return provider.getObject().getId();
	}
	
	@GetMapping("/request")
	public String requestBean() {
		return requestBean.getId();
	}
	
	@GetMapping("/session")
	public String sessionBean() {
		return sessionBean.getId();
	}
	
}
