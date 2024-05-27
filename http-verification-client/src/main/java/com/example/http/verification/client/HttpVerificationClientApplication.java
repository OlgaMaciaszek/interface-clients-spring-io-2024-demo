package com.example.http.verification.client;

import com.example.http.verification.client.clients.VerificationService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
public class HttpVerificationClientApplication {

	@Value("${http.verification.uri}")
	private String verificationServiceUrl;

	public static void main(String[] args) {
		SpringApplication.run(HttpVerificationClientApplication.class, args);
	}


	@Bean
	public HttpServiceProxyFactory httpServiceProxyFactory(RestClient.Builder restClientBuilder) {
		RestClient restClient = restClientBuilder
				.baseUrl(verificationServiceUrl)
				.build();
		RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);
		return HttpServiceProxyFactory.builderFor(restClientAdapter)
				.build();
	}

	@Bean
	public VerificationService verificationService(HttpServiceProxyFactory httpServiceProxyFactory) {
		return httpServiceProxyFactory.createClient(VerificationService.class);
	}
}


