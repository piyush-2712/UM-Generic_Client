package com.company.um.authz.client.authentication;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.company.um.authz.client.exception.ApplicationException;
import com.company.um.authz.client.model.AuthenticatedUser;
import com.company.um.authz.client.responseWrapper.ResponseWrapper;
import com.company.um.authz.client.responseWrapper.Status;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;

@Component
public class AuthenticateTokenService {


//	@Autowired
//	RestTemplate restTemplate;


	

	@Value("${token.uri}")
	private String ROOT_URI;

	//final String ROOT_URI = "http://localhost:8081/api/v1/user/getTokenInfo";
//	final String ROOT_URI = "http://10.12.68.178:8081/api/v1/user/getTokenInfo";

	private AuthenticatedUser getUser(String token) throws RestClientException, JsonParseException, JsonMappingException, IOException{

		
		RestTemplate restTemplate= new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
	//	headers.set("Authorization", "Bearer "+"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJCMDA5NjcwMCIsInVzZXJJbmZvIjoie1wiaWRcIjpcIjVjMDNjNTVjNWVlZWNjMjYxN2MwYjUyOFwiLFwidXNlcklkXCI6XCJCMDA5NjcwMFwiLFwiZmlyc3ROYW1lXCI6XCJQSVlVU0hcIixcImxhc3ROYW1lXCI6XCJTSFJJVkFTVEFWXCIsXCJkaXNwbGF5TmFtZVwiOlwiUElZVVNIIFNIUklWQVNUQVZcIixcImVtYWlsXCI6XCJwaXl1c2gxLnNocml2YXN0YXZAYWlydGVsLmNvbVwiLFwibW9iaWxlTnVtYmVyXCI6XCI5NTYwMDMxNzg4XCIsXCJlbmFibGVkXCI6dHJ1ZSxcInByb2plY3RBZG1pblwiOmZhbHNlLFwiZ3JvdXBBZG1pblwiOmZhbHNlLFwic3lzdGVtQWRtaW5cIjp0cnVlLFwiYXBwcm92ZXJcIjpmYWxzZSxcImVtcGxveWVlSWRcIjpcIkIwMDk2NzAwXCIsXCJsb2dpblR5cGVcIjpcIk9MTUlEXCJ9In0.fqZzCGmMBqoiJJXwJaGVG3y_mzMJUm82opxyAnDOU-gXHebg9qaFXR0zsrkMjYvhGDhozgG68CooEE4nqmH7NA");

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ROOT_URI)
				.queryParam("token", token);

		HttpEntity<?> entity = new HttpEntity<>(headers);

		//ResponseWrapper<AuthenticatedUser> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,  new ParameterizedTypeReference<ResponseWrapper<AuthenticatedUser>>() {}).getBody();
////		
//ResponseEntity response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,  ResponseWrapper.class);
////		return response.getBody();		
////		
////restTemplate.exchange(loginUrl, 
////                HttpMethod.GET, 
////                null, 
////                new ParameterizedTypeReference<Wrapper<Model>>() {}).getBody()
//		
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//		mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
//		ResponseWrapper a = (ResponseWrapper)response.getBody();
//		//ResponseWrapper a= mapper.readValue(response.getBody());
////		//AuthenticatedUser user =(AuthenticatedUser)a.getResult();
//		String userInString = mapper.writeValueAsString(a.getResult());
//		
//		AuthenticatedUser user= mapper.readValue(userInString, AuthenticatedUser.class);
//		//return response.getResult();
		
		ResponseWrapper<AuthenticatedUser> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,  new ParameterizedTypeReference<ResponseWrapper<AuthenticatedUser>>() {}).getBody();
		
		
		return response.getResult();

	}
	public AuthenticatedUser parseToken(String token) throws ApplicationException {
		AuthenticatedUser user;
		try {
			user =  getUser(token);
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new ApplicationException(new Status(500, "Something went wrong"), "Could not get Authenticated User.");
		}
		return user;
	}
	
	
	public static void main(String args[]) throws Exception{
		AuthenticateTokenService obj = new AuthenticateTokenService();
		obj.getUser("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJCMDA5NjcwMCIsInVzZXJJbmZvIjoie1wiaWRcIjpcIjVjMDNjNTVjNWVlZWNjMjYxN2MwYjUyOFwiLFwidXNlcklkXCI6XCJCMDA5NjcwMFwiLFwiZmlyc3ROYW1lXCI6XCJQSVlVU0hcIixcImxhc3ROYW1lXCI6XCJTSFJJVkFTVEFWXCIsXCJkaXNwbGF5TmFtZVwiOlwiUElZVVNIIFNIUklWQVNUQVZcIixcImVtYWlsXCI6XCJwaXl1c2gxLnNocml2YXN0YXZAYWlydGVsLmNvbVwiLFwibW9iaWxlTnVtYmVyXCI6XCI5NTYwMDMxNzg4XCIsXCJlbmFibGVkXCI6dHJ1ZSxcInByb2plY3RBZG1pblwiOmZhbHNlLFwiZ3JvdXBBZG1pblwiOmZhbHNlLFwic3lzdGVtQWRtaW5cIjp0cnVlLFwiYXBwcm92ZXJcIjpmYWxzZSxcImVtcGxveWVlSWRcIjpcIkIwMDk2NzAwXCIsXCJsb2dpblR5cGVcIjpcIk9MTUlEXCJ9In0.fqZzCGmMBqoiJJXwJaGVG3y_mzMJUm82opxyAnDOU-gXHebg9qaFXR0zsrkMjYvhGDhozgG68CooEE4nqmH7NA");
	}

}
