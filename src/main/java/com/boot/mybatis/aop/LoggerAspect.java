package com.boot.mybatis.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Around("execution(* com.boot.mybatis.aop..Controller.*Controller.*(..)) or execution(* com.boot.mybatis.aop..servic.*Impl.*(..)) or execution(* com.boot.mybatis.aop..mapper.*Mapper.*(..))")
	public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {
		
		String type = "";
		String name = joinPoint.getSignature().getDeclaringTypeName();
		
		if(name.indexOf("Controller") > -1) {
			type = "Controller \t: " ;
		}else if(name.indexOf("service") > -1) {
			type = "ServiceImpl \t: " ;
		}else if(name.indexOf("Mapper") > -1) {
			type = "Mapper \t\t: ";
		}
		log.info(type + name + "." + joinPoint.getSignature().getName()+ "()");
		return joinPoint.proceed();
	}
}
