package com.vnk.order.cron_job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulingJob {
	//@Scheduled(cron = "* * * * * *")
	public void sedulingBalanceNotification() {
		System.out.println("every second");
	}
	
	//@Scheduled(cron = "0 */5 * * * *")
	public void sedulingBalanceNotificationFor5Min () {
		System.out.println("sedulingBalanceNotificationFor5Min");
	}
	
	@Scheduled(cron = "0 0 */1 * * *")
	public void sedulingBalanceNotificationFor1Hour () {
		System.out.println("sedulingBalanceNotificationFor1Hour");
	}
	
	//@Scheduled(cron = "0 30 9 * * *")
	public void sedulingBalanceNotificationForOneday () {
		System.out.println("sedulingBalanceNotificationForOneday");
	}
	
	//@Scheduled(cron = "0 0 0 * * SUN")
	public void sedulingBalanceNotificationForWeek () {
		System.out.println("sedulingBalanceNotificationForWeek");
	}
	
	//@Scheduled(cron = "0 0 0 1 JUN *")
	public void sedulingBalanceNotificationForMonth () {
		System.out.println("sedulingBalanceNotificationForMonth");
	}
}
