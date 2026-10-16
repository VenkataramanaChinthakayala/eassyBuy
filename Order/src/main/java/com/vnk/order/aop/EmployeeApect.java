package com.vnk.order.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//@Aspect
@Component
public class EmployeeApect {
	@Before("execution(* com.vnk.order.service.OrderServiceImpl.*(..))")
	public void before() {
		System.out.println("Befor method excution");
	}

	@After("execution(* com.vnk.order.service.OrderServiceImpl.*(..))")
	public void after() {
		System.out.println("Ofter method excution");
	}

	@AfterReturning("execution(* com.vnk.order.service.OrderServiceImpl.*(..))")
	public void afterReturning() {
		System.out.println("afterReturning");
	}

	@AfterThrowing("execution(* com.vnk.order.service.OrderServiceImpl.*(..))")
	public void afterThrowing() {
		System.out.println("afterThrowing");
	}
	@Around("execution(* com.vnk.order.service.OrderServiceImpl.*(..))")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

	    System.out.println("Around - Before");

	    Object result = joinPoint.proceed();

	    System.out.println("Around - After");

	    return result;
	}

}
