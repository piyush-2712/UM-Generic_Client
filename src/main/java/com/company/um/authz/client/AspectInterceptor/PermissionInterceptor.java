package com.company.um.authz.client.AspectInterceptor;

import org.aspectj.lang.ProceedingJoinPoint;

import com.company.um.authz.client.exception.ApplicationException;

public interface PermissionInterceptor {

	Object checkPermission(ProceedingJoinPoint joinPoint) throws Throwable;
}
