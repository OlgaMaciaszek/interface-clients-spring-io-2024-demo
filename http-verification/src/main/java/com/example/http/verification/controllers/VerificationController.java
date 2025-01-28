package com.example.http.verification.controllers;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import com.example.http.verification.dto.VerificationRequest;
import com.example.http.verification.dto.VerificationResult;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Olga Maciaszek-Sharma
 */
@RestController
@RequestMapping
public class VerificationController {

	private final Random random = new Random();

	@GetMapping("/verification/count")
	int count() {
		return random.nextInt() & Integer.MAX_VALUE;
	}

	@GetMapping("/count")
	int secondCount() {
		return random.nextInt() & Integer.MAX_VALUE;
	}


	@GetMapping("/verification/test")
	String test() {
		return "test";
	}

	@GetMapping("/verification/header")
	String header(@RequestHeader String header) {
		return "Header: " + header;
	}

	@GetMapping("/verification/headers")
	String headers(@RequestHeader Map<String, String> headers) {
		return "Headers: " + headers;
	}

	@GetMapping("/verification/headerValues")
	String headerValues(@RequestHeader Collection<String> headerValues) {
		return "Header values: " + headerValues;
	}

	@GetMapping("/verification/param")
	String requestParam(@RequestParam String param) {
		return "RequestParam: " + param;
	}

	@GetMapping("/verification/params")
	String requestParams(@RequestParam Map<String, String> params) {
		return "RequestParams: " + params;
	}

	@GetMapping("/verification/paramValues")
	String requestParamValues(@RequestParam Collection<String> paramValues) {
		return "RequestParamValues: " + paramValues;
	}

	@GetMapping("/verification/cookie")
	String cookie(@CookieValue String cookie) {
		return "Cookie: " + cookie;
	}

	@GetMapping("/verification/cookies")
	String cookies(@CookieValue String cookie) {
		return "Cookies: " + cookie;
	}

	@GetMapping("/verification/cookieValues")
	String cookieValues(@CookieValue Collection<String> cookieValues) {
		return "CookieValues: " + cookieValues;
	}

	@PostMapping("/verification")
	VerificationResult verify(@RequestBody VerificationRequest request) {
		return new VerificationResult(request.firstName(), request.lastName(), UUID.randomUUID());
	}

	@PostMapping(value = "/verification/parts", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	String postParts(@RequestParam String part1, @RequestParam String part2) {
		return "Parts: " + part1 + ", " + part2;
	}

	@PostMapping(value = "/verification/multipart", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	String postMultipart(@RequestBody MultipartFile file) throws IOException {
		return "File: " + file.getName() + ": " + new String(file.getBytes(), Charset.defaultCharset());
	}
}
