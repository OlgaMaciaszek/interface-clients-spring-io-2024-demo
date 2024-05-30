package com.example.rsocketverificationclient.controllers;

import com.example.rsocketverificationclient.clients.CountService;
import com.example.rsocketverificationclient.clients.VerificationService;
import com.example.rsocketverificationclient.dto.VerificationRequest;
import com.example.rsocketverificationclient.dto.VerificationResult;
import reactor.core.publisher.Mono;

import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Olga Maciaszek-Sharma
 */
@RestController
@RequestMapping
public class VerificationController {

	private final VerificationService verificationService;

	private final CountService countService;

	public VerificationController(VerificationService verificationService,
			CountService countService) {
		this.verificationService = verificationService;
		this.countService = countService;
	}


	@PostMapping("/verify")
	Mono<VerificationResult> verify() {
		return verificationService.verify(new VerificationRequest("John", "Smith"));
	}

	@GetMapping("/test")
	Mono<String> test() {
		return verificationService.test("Mark");
	}

	@GetMapping("/testMetadata")
	Mono<String> testMetadata() {
		return verificationService.test("Mark", "testHeaderValue", MimeType.valueOf("application/json"));
	}

	@GetMapping("/count")
	Mono<Integer> count() {
		return countService.count();
	}

}
