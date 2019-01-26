package com.company.um.authz.client.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.company.um.authz.client.responseWrapper.ResponseWrapper;
import com.company.um.authz.client.responseWrapper.Status;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomAccessDeniedHandler implements AccessDeniedHandler{
	
	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public void handle(HttpServletRequest arg0, HttpServletResponse response, AccessDeniedException e)
			throws IOException, ServletException {
		
		response.setStatus(HttpStatus.OK.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		mapper.writeValue(response.getWriter(),
				ResponseWrapper.getFailureResponse(new Status(401, e.getMessage())));
		
	}

}