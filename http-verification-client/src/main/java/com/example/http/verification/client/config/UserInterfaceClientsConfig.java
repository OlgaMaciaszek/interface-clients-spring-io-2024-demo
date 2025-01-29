package com.example.http.verification.client.config;

import com.example.http.verification.client.clients.PersonService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientProxyRegistry;

/**
 * @author Olga Maciaszek-Sharma
 */
@Configuration
public class UserInterfaceClientsConfig {

	@Bean
	public RestClientProxyRegistry.Builder restClientProxyRegistryBuilder(RestClient.Builder restClientBuilder) {
		return RestClientProxyRegistry.builder(restClientBuilder)
				.addClient("http://localhost:8081", "programmaticVerificationClient",
						httpServiceConfigurer -> httpServiceConfigurer.addServiceTypes(PersonService.class),
						clientBuilder -> { // dosth
						}, proxyFactoryBuilderConsumer -> {
							// do sth
						});
	}
}
