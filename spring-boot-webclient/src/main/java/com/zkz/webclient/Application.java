package com.zkz.webclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * Synchronous(TongBu) client to perform HTTP requests,
	 * exposing a simple, template method API over underlying HTTP client libraries such as the JDK HttpURLConnection, Apache HttpComponents, and others.
	 * The RestTemplate offers templates for common scenarios by HTTP method,
	 * in addition to the generalized exchange and execute methods that support of less frequent cases.
	 * For example, the restTemplate can be injected to service to cal other Http endpoints
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate(){
		return  new RestTemplate();
	}
}
