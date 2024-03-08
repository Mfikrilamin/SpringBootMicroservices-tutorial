package com.springbootmicroservices.Advertisementservice;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class AdvertisementApplication {
	public static void main(String[] args) {
		SpringApplication.run(AdvertisementApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		RestTemplate template = new RestTemplate();
		List<ClientHttpRequestInterceptor> interceptors = template.getInterceptors();
		if (interceptors.isEmpty()) {
			// template.setInterceptors(Collections.singletonList(new UserContextIntercep));
		} else {
			// interceptors.add();
			template.setInterceptors(interceptors);
		}

		return template;
	}
}
