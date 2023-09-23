package com.boot.mybatis.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {

	
	// 컨트롤러 실행되기 전 수행
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.debug("===============start===============");
		log.debug("Request URL \t:  " + request.getRequestURI());
		return true;
	}
	
	// 컨트롤러 실행된 후 수행
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.debug("===============end===============");
		
	}
	
}
