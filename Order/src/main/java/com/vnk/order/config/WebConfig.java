package com.vnk.order.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.vnk.order.util.LoggingInterseptor;

//@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Autowired
	private LoggingInterseptor filter;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(filter);
	}
}
