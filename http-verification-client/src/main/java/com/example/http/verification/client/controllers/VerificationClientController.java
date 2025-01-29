package com.example.http.verification.client.controllers;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.example.http.verification.client.clients.VerificationService;
import com.example.http.verification.client.dto.VerificationRequest;
import com.example.http.verification.client.dto.VerificationResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.registry.HttpServiceProxyRegistry;
import org.springframework.web.util.DefaultUriBuilderFactory;

/**
 * @author Olga Maciaszek-Sharma
 */
@RestController
@RequestMapping
public class VerificationClientController {

	// For debugging
	@Autowired
	ConfigurableApplicationContext context;

	private final VerificationService verificationService;

	private final VerificationService verificationServiceFromRegistry;

	private final HttpServiceProxyRegistry registry;

	public VerificationClientController(VerificationService verificationService,
			HttpServiceProxyRegistry registry) {
		this.verificationService = verificationService;
		this.registry = registry;
		this.verificationServiceFromRegistry = registry.getClient(VerificationService.class);
	}

	@RequestMapping("/count")
	int count() {
		return verificationService.count(Optional.of(URI.create("http://localhost:8081/count")));
	}

	@RequestMapping("/count/factory")
	int countFactory() {
		return verificationService.countFactory(Optional.of(new DefaultUriBuilderFactory("http://localhost:8081/count")));
	}

	@GetMapping("/count/meta")
	int countWithMetaAnnotation(URI uri) {
		return verificationService.countWithMetaAnnotation();
	}

	@RequestMapping("/count/method")
	int countWithMethod() {
		return verificationService.countWithMethod(Optional.empty());
	}

	@GetMapping("/test")
	String test() {
		return verificationService.test();
	}

	@GetMapping("/test2")
	String test2() {
		return verificationServiceFromRegistry.test();
	}

	@PostMapping()
	VerificationResult verify() {
		return verificationService.verify(Optional.of(new VerificationRequest("Anna", "Smith")));
	}

	@GetMapping("/header")
	String header() {
		return verificationService.header(Optional.of("headerValue"));
	}

	@GetMapping("/headers")
	String headers() {
		return verificationService.headers(Map.of("headerName", "headerValue"));
	}

	@GetMapping("/headerValues")
	String headerValues() {
		return verificationService.headerValues(List.of("headerValue1", "headerValue2", "headerValue3"));
	}

	@GetMapping("/param")
	String requestParam() {
		return verificationService.requestParam("param");
	}

	@GetMapping("/params")
	String requestParams() {
		return verificationService.requestParams(Map.of("paramName", "paramValue"));
	}

	@GetMapping("/paramValues")
	String requestParamValues() {
		return verificationService.requestParamValues(Set.of("paramValue1", "paramValue2", "paramValue3"));
	}

	@GetMapping("/cookie")
	String cookie() {
		return verificationService.cookie("cookie");
	}

	@GetMapping("/cookies")
	String cookies() {
		return verificationService.cookies(Map.of("cookie", "cookieValue"));
	}

	@GetMapping("/cookieValues")
	String cookieValues() {
		return verificationService.cookieValues(List.of("cookieValue1", "cookieValue2", "cookieValue3"));
	}

	@GetMapping("/path")
	String pathVariable() {
		return verificationService.pathVariable(null);
	}

	@PostMapping("/parts")
	String postParts() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("testHeader", "testValue");
		HttpEntity<String> part2 = new HttpEntity<>("part2", headers);
		return verificationService.postParts("part1", part2);
	}

	@PostMapping(value = "/multipart")
	String postMultipart() {
		return verificationService.postMultipart(
				new MockMultipartFile("testFileName", "originalTestFileName",
						"text/plain", "test".getBytes()));
	}
}
