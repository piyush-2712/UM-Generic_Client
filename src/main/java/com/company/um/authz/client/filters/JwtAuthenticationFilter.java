
package com.company.um.authz.client.filters;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

//import org.eclipse.jetty.server.handler.ContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.company.um.authz.client.authentication.AuthenticateTokenService;
import com.company.um.authz.client.authentication.ErrorResponse;
import com.company.um.authz.client.authentication.JwtAuthenticationToken;
import com.company.um.authz.client.authentication.NoAuthenticationHeader;
import com.company.um.authz.client.responseWrapper.ResponseWrapper;
import com.company.um.authz.client.responseWrapper.Status;
import com.fasterxml.jackson.databind.ObjectMapper;



//@Component
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private List<String> unAuthenticatedUrls;
	
	public static final String ORIGIN = "ORIGIN";
    public static final String OPTIONS = "OPTIONS";
	

	private final Logger authLogger = LoggerFactory.getLogger(this.getClass());

	// Need these urls in this filter because filter is applied on the basis of urls only.
	// Cannot use WebSecurity for ignoring these urls.
	private Set<AntPathRequestMatcher> unAuthenticatedUrl;
	/**
	 * If authentication header is present, authentication will happen else not
	 */
	private Set<AntPathRequestMatcher> partiallyAuthenticatedUrl;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private AuthenticateTokenService authenticateTokenService;

	
	public void setUnauthenticatedUrls(List<String> unAuthenticatedUrls){
		this.unAuthenticatedUrls = unAuthenticatedUrls;
		for(String url : unAuthenticatedUrls) {
			String[] entry = url.split(":");
			if(entry.length >= 2)
				unAuthenticatedUrl.add(new AntPathRequestMatcher(entry[0], entry[1]));
		}
	}

	public JwtAuthenticationFilter() {
		super("/**");
		partiallyAuthenticatedUrl = new HashSet<>();
		//        partiallyAuthenticatedUrl.add(new AntPathRequestMatcher("/api/v1/user", "POST"));
		//        partiallyAuthenticatedUrl.add(new AntPathRequestMatcher("/api/v1/user", "GET"));
		//        partiallyAuthenticatedUrl.add(new AntPathRequestMatcher("/api/v1/circle", "GET"));
		//        partiallyAuthenticatedUrl.add(new AntPathRequestMatcher("/authenticator/accounts","POST"));
		unAuthenticatedUrl = new HashSet<>();
//		unAuthenticatedUrl.add(new AntPathRequestMatcher("/**", "OPTIONS"));
//		//unAuthenticatedUrl.add(new AntPathRequestMatcher("/api/v1/user/*", "PATCH"));
//		unAuthenticatedUrl.add(new AntPathRequestMatcher("/api/v1/user/welcome*", "GET"));
//		//  unAuthenticatedUrl.add(new AntPathRequestMatcher("/api/v1/user/get*", "GET"));
//		//  unAuthenticatedUrl.add(new AntPathRequestMatcher("/api/v1/user/**", "POST"));
//		//        unAuthenticatedUrl.add(new AntPathRequestMatcher("/api/v1/user/*", "POST"));
//		//        unAuthenticatedUrl.add(new AntPathRequestMatcher("/api/v1/user/*", "GET"));
//		//        unAuthenticatedUrl.add(new AntPathRequestMatcher("/api/v1/user/**", "GET"));
//		//        unAuthenticatedUrl.add(new AntPathRequestMatcher("/api/v1/user/*", "POST"));
//		//        unAuthenticatedUrl.add(new AntPathRequestMatcher("/api/v1/group/*", "GET"));
//		//        unAuthenticatedUrl.add(new AntPathRequestMatcher("/api/v1/role/*", "GET"));
//		//        unAuthenticatedUrl.add(new AntPathRequestMatcher("/api/v1/role/*", "POST"));
//		//        unAuthenticatedUrl.add(new AntPathRequestMatcher("/api/v1/project/*", "GET"));
//		//        unAuthenticatedUrl.add(new AntPathRequestMatcher("/api/v1/circle/*", "GET"));
//		//        unAuthenticatedUrl.add(new AntPathRequestMatcher("/api/v1/circle", "GET"));
//		//unAuthenticatedUrl.add(new AntPathRequestMatcher("/api/v1/project/create", "POST"));
//		//unAuthenticatedUrl.add(new AntPathRequestMatcher("/api/v1/project/get", "GET"));
//		unAuthenticatedUrl.add(new AntPathRequestMatcher("/authenticator/accounts", "POST"));
//		unAuthenticatedUrl.add(new AntPathRequestMatcher("/authenticator/getOtp", "GET"));
//		unAuthenticatedUrl.add(new AntPathRequestMatcher("/authenticator/verifyOtp", "POST"));
//		unAuthenticatedUrl.add(new AntPathRequestMatcher("/authenticator/validate/username", "GET"));
	}
	//
	//	@PostConstruct
	//	public void postConstruct() {
	//		setAuthenticationManager(authentication -> {
	//			JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
	//			AuthenticatedUser parsedUser = authenticateTokenService.parseToken(token.getToken());
	//			List<RoleClient> sdfhjsjdkbhf=(List<RoleClient>) parsedUser.getRoleValues();
	//
	//			if(parsedUser != null) {
	//				return new UsernamePasswordAuthenticationToken(parsedUser, null, parsedUser.getAuthorities());
	//			} else {
	//				throw new BadCredentialsException("Invalid token");
	//			}
	//		});
	//	}

	@Override
	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
		for(AntPathRequestMatcher path: unAuthenticatedUrl) {
			if(path.matches(request)) {
				return false;
			}
		}
		logger.info("requiresAuthentication: ");
		String header = request.getHeader("Authorization");
		if (header == null || !header.startsWith("Bearer ")) {
			for(AntPathRequestMatcher path: partiallyAuthenticatedUrl) {
				if(path.matches(request)) {
					return false;
				}
			}
		}
		return super.requiresAuthentication(request, response);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
		authLogger.info("Url: [{}]", request.getRequestURL());
		HttpServletResponseWrapper resp = (HttpServletResponseWrapper)response;
		authLogger.info("HttpServletResponse: [{}]", resp.getResponse().getClass().toString());
		
		if (request.getHeader(ORIGIN) != null) {
			String origin = request.getHeader(ORIGIN);
			logger.debug("origin = " + origin);
			response.addHeader("Access-Control-Allow-Origin", origin);
			response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
			response.addHeader("Access-Control-Allow-Credentials", "true");
			response.addHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
		}
		if (OPTIONS.equals(request.getMethod())) {
			response.getWriter().print("OK");
			response.getWriter().flush();
			return null;
		}
		
		
		String header = request.getHeader("Authorization");
		if(header != null) {
			String authToken = header.substring(7);
			JwtAuthenticationToken authRequest = new JwtAuthenticationToken(authToken);
			return getAuthenticationManager().authenticate(authRequest);
		}
//		throw new NoAuthenticationHeader("No Authorization header found");
		throw new AuthenticationServiceException("No Authorization header found");
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication)
			throws IOException, ServletException {
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authentication);
		SecurityContextHolder.setContext(context);
		//super.successfulAuthentication(request, response, chain, authentication);

		// As this authentication is in HTTP header, after success we need to continue the request normally
		// and return the response as if the resource was not secured at all
		chain.doFilter(request, response);
	}


	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		SecurityContextHolder.clearContext();
//		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), HttpServletResponse.SC_UNAUTHORIZED);
//		response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
//		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		
		response.setStatus(HttpStatus.OK.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		
		authLogger.info(exception.getClass().toString());
		
		if (exception instanceof BadCredentialsException) {
			objectMapper.writeValue(response.getWriter(), ResponseWrapper
					.getFailureResponse(new Status(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage())));
		} else if (exception instanceof AuthenticationServiceException) {
			objectMapper.writeValue(response.getWriter(), ResponseWrapper
					.getFailureResponse(new Status(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage())));
		}else {
			objectMapper.writeValue(response.getWriter(), ResponseWrapper
					.getFailureResponse(new Status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, exception.getMessage())));
		}
//		response.getOutputStream().print(objectMapper.writeValueAsString(ResponseWrapper.getFailureResponse(new Status(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage()))));
//		response.getOutputStream().flush();
//		response.getOutputStream().close();

		// As this authentication is in HTTP header, after success we need to continue the request normally
		// and return the response as if the resource was not secured at all
		//chain.doFilter(request, response);
	}

}

