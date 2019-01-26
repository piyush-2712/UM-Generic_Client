package com.company.um.authz.client.configurations;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.ProxyAuthenticationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

	private static final int CONNECT_TIMEOUT = 30000;
    
    // The timeout when requesting a connection from the connection manager.
    private static final int REQUEST_TIMEOUT = 30000;
     
    // The timeout for waiting for data
    private static final int SOCKET_TIMEOUT = 60000;
    
    @Bean
    public CloseableHttpClient httpClient() {
//        RequestConfig requestConfig = RequestConfig.custom()
//                .setConnectionRequestTimeout(REQUEST_TIMEOUT)
//                .setConnectTimeout(CONNECT_TIMEOUT)
//                .setSocketTimeout(SOCKET_TIMEOUT).build();
// 
//        return HttpClients.custom()
//                .setDefaultRequestConfig(requestConfig)
////                .setConnectionManager(poolingConnectionManager())
////                .setKeepAliveStrategy(connectionKeepAliveStrategy())
//                .build();
		
		
		
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//		credentialsProvider.setCredentials(new AuthScope("airtelproxy.airtel.com", 4145),
//				new UsernamePasswordCredentials("B0096703", "Termina$orflash@1505"));
		HttpClientBuilder clientBuilder = HttpClientBuilder.create();
//
//		clientBuilder.useSystemProperties();
//		clientBuilder.setProxy(new HttpHost("airtelproxy.airtel.com", 4145));
//		clientBuilder.setDefaultCredentialsProvider(credentialsProvider);
//		clientBuilder.setProxyAuthenticationStrategy(new ProxyAuthenticationStrategy());
//		clientBuilder.setSSLHostnameVerifier(new NoopHostnameVerifier());
		

//		RequestConfig requestConfig = RequestConfig.custom()
//                .setConnectionRequestTimeout(REQUEST_TIMEOUT)
//                .setConnectTimeout(CONNECT_TIMEOUT)
//                .setSocketTimeout(SOCKET_TIMEOUT).build();
		
		
		CloseableHttpClient client = clientBuilder
	             .build();
		return client;
    }
    
    @Bean
    public RestTemplate restTemplate() {
//        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
//        return restTemplate;
        
        RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(clientHttpRequestFactory());
		return restTemplate;
    }
 
    @Bean
    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
//        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
//        clientHttpRequestFactory.setHttpClient(httpClient());
//        return clientHttpRequestFactory;
    	
    	
    	HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setHttpClient(httpClient());
		factory.setConnectTimeout(CONNECT_TIMEOUT);
		factory.setReadTimeout(SOCKET_TIMEOUT);
		factory.setConnectionRequestTimeout(REQUEST_TIMEOUT);
		return factory;
    }
}
