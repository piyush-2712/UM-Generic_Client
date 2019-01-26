package com.company.um.authz.client.AspectInterceptor;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.um.authz.client.annotations.Permission;
import com.company.um.authz.client.exception.ApplicationException;
import com.company.um.authz.client.exception.AuthorisationException;
import com.company.um.authz.client.exception.ServerErrorException;
import com.company.um.authz.client.exception.UserErrorException;
import com.company.um.authz.client.model.AuthenticatedUser;
import com.company.um.authz.client.model.PermissionClient;
import com.company.um.authz.client.model.PermissionType;
import com.company.um.authz.client.model.RoleClient;
import com.company.um.authz.client.permission.BasePermission;
import com.company.um.authz.client.request.RequestObject;
import com.company.um.authz.client.responseWrapper.Status;


@Service
public class PermissionInterceptorImpl implements PermissionInterceptor {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private ServletContext servletContext;



	@Override
	public Object checkPermission(ProceedingJoinPoint joinPoint) throws ApplicationException, Throwable {
		if (SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
			AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			checkRoleOfUser(joinPoint, authUser);

		}else {
			throw new ApplicationException(new Status(401,"Not logged in"),"UnAuthenticated user error");
		}
		try {
			return joinPoint.proceed();
		} catch (Throwable e) {
			throw e;
		}
	}


	private void checkRoleOfUser(ProceedingJoinPoint joinPoint, AuthenticatedUser authUser) throws ApplicationException{
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Class<? extends BasePermission> [] permissionsOnMethod = signature.getMethod().getAnnotation(Permission.class).permission();
		Boolean isValidPermission = false;
		List <RoleClient> roles = authUser.getRoleValues();
		try {
			if(roles  != null) {
				for (RoleClient role : roles) {
					if ( isValidPermission ) break;
					Set< String > rolePermissions = getRolePermissionSet(role, PermissionType.PROJECT);
					for (Class<? extends BasePermission> permissionClass : permissionsOnMethod) {
						BasePermission permission = context.getBean(permissionClass);
						if (rolePermissions.contains(permissionClass.getSimpleName().toLowerCase())) {
							isValidPermission = validatePermission(authUser, permission, joinPoint);
						}
						if (isValidPermission) break;
					}
				}
			}
		}catch (BeansException ex) {
			throw new ServerErrorException("Cannot get permission/rule class bean");
		} catch (AuthorisationException ex) {
			throw new UserErrorException(ex.getMessage(), HttpStatus.FORBIDDEN);
		}
		if(!isValidPermission) {
			throw new UserErrorException("You are not authorized for the given action!", HttpStatus.FORBIDDEN);
		}
	}

	private boolean validatePermission(AuthenticatedUser user,BasePermission permission,ProceedingJoinPoint joinPoint) throws AuthorisationException {
		boolean permissionMatched = false;
		RequestObject requestObject = getRequestObject(joinPoint);
		try {
			// validate permission
			if (permission.isAuthorised(user, requestObject)) {
				permissionMatched = true;
			}
		} catch (AuthorisationException ex) {
			throw ex;
		}
		return permissionMatched;
	}


	/**
	 * Extract request parameters
	 *
	 * @param joinPoint
	 * @return
	 */
	private RequestObject getRequestObject(ProceedingJoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Map<String, Object> queryParameters = new HashMap<String, Object>();
		Map<String, Object> pathParameters = new HashMap<String, Object>();
		Object requestBody = null;
		for (int i = 0; i < signature.getMethod().getParameterCount(); i++) {
			for (Annotation parameterAnn : signature.getMethod()
					.getParameterAnnotations()[i]) {
				if (parameterAnn.annotationType().equals(RequestParam.class)) {
					RequestParam ann = (RequestParam) parameterAnn;
					queryParameters.put(ann.value(), joinPoint.getArgs()[i]);
				} else if (parameterAnn.annotationType().equals(PathVariable.class)) {
					PathVariable ann = (PathVariable) parameterAnn;
					pathParameters.put(ann.value(), joinPoint.getArgs()[i]);
				} else if (parameterAnn.annotationType().equals(RequestBody.class)) {
					requestBody = joinPoint.getArgs()[i];
				}
			}
		}
		return new RequestObject(queryParameters, pathParameters,
				requestBody);
	}


	private Set<String> getRolePermissionSet(RoleClient role, PermissionType type){
		if(role.getPermission() != null && role.getPermission().size() > 0 ) {
			return  role.getPermission().stream().filter(permission -> permission.getPermissionType().equals(type))    // -> Stream<Map<String, List<String>>>
					.map(permission -> permission.getName().toLowerCase()).collect(Collectors.toSet());
		}
		return new HashSet<>();
	}
	
	
}
