package com.boot.mybatis.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LoggerAspect {

	//private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Around("execution(* com.boot.mybatis..controller.*Controller.*(..)) or execution(* com.boot.mybatis..service.*Impl.*(..)) or execution(* com.boot.mybatis..mapper.*Mapper.*(..))")
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
		
		log.debug(type + name + "." + joinPoint.getSignature().getName()+ "()");
		return joinPoint.proceed();
		
	}
}
