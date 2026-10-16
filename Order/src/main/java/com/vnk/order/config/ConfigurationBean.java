package com.vnk.order.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
//@EnableAsync
public class ConfigurationBean {
	
	@Bean("applicationTaskExcutor")
	public Executor configurationExcutor() {
		ThreadPoolTaskExecutor excute = new ThreadPoolTaskExecutor();
		excute.setCorePoolSize(5);
		excute.setMaxPoolSize(10);
		excute.setQueueCapacity(100);
		excute.setThreadNamePrefix("order-worker-");
		excute.setWaitForTasksToCompleteOnShutdown(true);
		excute.setAwaitTerminationSeconds(30);
		excute.initialize();
		return excute;
	}
}
