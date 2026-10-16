package com.vnk.order.util;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
//@Component
public class LoggingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	HttpServletRequest httpServletRequest= (HttpServletRequest) request;
	System.out.println("Filterbefor "+ httpServletRequest.getRequestURI());
	chain.doFilter(request, response);
	System.out.println("After Filter "+ httpServletRequest.getRequestURI());
	}

}
