package com.vnk.order.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class BeanLifeCycle
		implements BeanNameAware, ApplicationContextAware, InitializingBean, DisposableBean, BeanPostProcessor {

	public BeanLifeCycle() {
		System.out.println("2.Hello");
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("3.Bean name :" + name);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("4.Application context application name: " + applicationContext.getApplicationName()
				+ "Definition count: " + applicationContext.getBeanDefinitionCount());
	}

	@PostConstruct
	public void postConstructor() {
		System.out.println("5.Post Constructor");
	}
	
	@Override
	@PreDestroy
	public void destroy() throws Exception {

		System.out.println("9.destroy object");
	}

	@Override
	public void afterPropertiesSet() throws Exception {

		System.out.println("8.Ofter properties set");
	}
	
}
