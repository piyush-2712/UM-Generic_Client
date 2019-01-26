package com.company.um.authz.client.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.um.authz.client.AspectInterceptor.PermissionInterceptor;
import com.company.um.authz.client.exception.ApplicationException;

@Component
@Aspect
public class PermissionValidationAspect {

	@Autowired
	PermissionInterceptor permissionInterceptor;


	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	public void controllerClass() {
	}

	@Pointcut("@annotation(com.airtel.um.authz.client.annotations.Permission)")
	public void permissionAnnotatedMethod() {
	}
	

	@Pointcut("controllerClass() && permissionAnnotatedMethod()")
	public void authorize() {
	}
	

	@Around("authorize()")
	public Object checkPermission(ProceedingJoinPoint joinPoint) throws Throwable{
		return permissionInterceptor.checkPermission(joinPoint);
	}



}
