package com.example.http.verification.client;

import com.example.http.verification.client.clients.PersonService;
import com.example.http.verification.client.clients.VerificationService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
public class HttpVerificationClientApplication {

//	Get URI directly from properties
//	@Value("${http.verification.uri}")
//	private String verificationServiceUrl;

	public static void main(String[] args) {
		SpringApplication.run(HttpVerificationClientApplication.class, args);
	}


//	Set hard-coded URI
//	@Bean
//	public HttpServiceProxyFactory httpServiceProxyFactory(RestClient.Builder restClientBuilder) {
//		RestClient restClient = restClientBuilder
//				.baseUrl(verificationServiceUrl)
//				.build();
//		RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);
//		return HttpServiceProxyFactory.builderFor(restClientAdapter)
//				.build();
//	}

	// Using load-balancing
	@Bean
	public HttpServiceProxyFactory httpServiceProxyFactory(@LoadBalanced RestClient.Builder restClientBuilder) {
		RestClient restClient = restClientBuilder
				// serviceId instead of host:port
				.baseUrl("http://http-verification")
				.build();
		RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);
		return HttpServiceProxyFactory.builderFor(restClientAdapter)
				.build();
	}


	@Bean
	public VerificationService verificationService(HttpServiceProxyFactory httpServiceProxyFactory) {
		return httpServiceProxyFactory.createClient(VerificationService.class);
	}

	@Bean
	public PersonService personService(HttpServiceProxyFactory httpServiceProxyFactory) {
		return httpServiceProxyFactory.createClient(PersonService.class);
	}
}


