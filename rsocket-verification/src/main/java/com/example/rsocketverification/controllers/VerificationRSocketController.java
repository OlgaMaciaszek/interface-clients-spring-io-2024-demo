package com.example.rsocketverification.controllers;

import java.util.UUID;

import com.example.rsocketverification.dto.VerificationRequest;
import com.example.rsocketverification.dto.VerificationResult;
import reactor.core.publisher.Mono;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

/**
 * @author Olga Maciaszek-Sharma
 */
@Controller
public class VerificationRSocketController {

	@MessageMapping("verify")
	Mono<VerificationResult> verify(VerificationRequest request) {
		return Mono.just(new VerificationResult(request.firstName(), request.lastName(), UUID.randomUUID()));
	}

	@MessageMapping("/test/{name}")
	Mono<String> test(@DestinationVariable("name") String name) {
		return Mono.just("Test: " + name);
	}

	@MessageMapping("/testMetadata/{name}")
	Mono<String> test(@DestinationVariable("name") String name,
			@Header("test-header") String headerValue) {
		return Mono.just("Test: " + name + ", header: " + headerValue);
	}

}
