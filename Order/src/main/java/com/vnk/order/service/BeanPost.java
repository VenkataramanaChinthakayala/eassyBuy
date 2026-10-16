package com.vnk.order.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class BeanPost implements BeanPostProcessor {
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("6.BeanPostProcessor - BEFORE" + beanName);
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("7.BeanPostProcessor - AFTER" + beanName);

		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}
}
