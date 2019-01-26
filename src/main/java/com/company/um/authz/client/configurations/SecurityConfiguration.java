package com.company.um.authz.client.configurations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

import com.company.um.authz.client.authentication.CustomAccessDeniedHandler;
import com.company.um.authz.client.authentication.JwtAuthenticationProvider;
import com.company.um.authz.client.authentication.JwtAuthenticationToken;
import com.company.um.authz.client.filters.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Value("#{'${app.unrestricted.urls}'.split(',')}") 
	private List<String> unAuthenticatedUrls;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtAuthenticationProvider jwtAuthenticationProvider;

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	@Override
    @Autowired
	protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
		authManagerBuilder.authenticationProvider(jwtAuthenticationProvider);
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		/*web.ignoring().regexMatchers(HttpMethod.GET,"/companies").and().ignoring().antMatchers(HttpMethod.POST,"/Authenticator/accounts");
    web.ignoring().antMatchers(HttpMethod.POST, "/users");*/
//		web.ignoring().antMatchers("/**");
//		web.ignoring().antMatchers("/error");
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
	//	.authorizeRequests().anyRequest().authenticated().and()
		.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
		.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
	}
	
	

	private JwtAuthenticationFilter authenticationTokenFilterBean() {
		JwtAuthenticationFilter tokenAuthenticationProcessingFilter = new JwtAuthenticationFilter();
		tokenAuthenticationProcessingFilter.setAuthenticationManager(authenticationManager);
		tokenAuthenticationProcessingFilter.setUnauthenticatedUrls(unAuthenticatedUrls);
		return tokenAuthenticationProcessingFilter;
	}


//	@Override
//	public void configure(AuthenticationManagerBuilder auth) 
//			throws Exception {
//		auth.authenticationProvider(jwtAuthenticationProvider);
//
//	}


	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	public RestTemplate restTemplate() {
//	    return new RestTemplate();
//	}
}
