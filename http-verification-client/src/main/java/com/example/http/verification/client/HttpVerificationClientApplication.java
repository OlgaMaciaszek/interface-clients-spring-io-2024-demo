package com.example.http.verification.client;

import com.example.http.verification.client.clients.VerificationService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.service.registry.InterfaceClient;

@SpringBootApplication
//@InterfaceClient(value = "http://localhost:8081", basePackageClasses = PersonService.class)
@InterfaceClient(value = "http://localhost:8081", name = "verificationClient", httpServiceTypes = {VerificationService.class})
public class HttpVerificationClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpVerificationClientApplication.class, args);
	}

}

