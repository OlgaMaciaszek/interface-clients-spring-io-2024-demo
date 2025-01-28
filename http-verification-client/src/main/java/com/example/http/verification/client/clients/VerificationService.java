package com.example.http.verification.client.clients;

import java.net.URI;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import com.example.http.verification.client.dto.VerificationRequest;
import com.example.http.verification.client.dto.VerificationResult;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.util.UriBuilderFactory;

/**
 * @author Olga Maciaszek-Sharma
 */
@HttpExchange("/verification")
public interface VerificationService {

	@HttpExchange(method = "GET", url = "/count")
	int count(Optional<URI> uri);

	@HttpExchange(method = "GET", url = "/count")
	int countFactory(Optional<UriBuilderFactory> factory);

	@GetExchange(url = "/count")
	int countWithMetaAnnotation();

	@PostExchange(url = "/count")
	int countWithMethod(Optional<HttpMethod> method);

	@GetExchange("/test")
	String test();

	@GetExchange("/header")
	String header(@RequestHeader Optional<String> header);

	@GetExchange("/headers")
	String headers(@RequestHeader Map<String, String> headers);

	@GetExchange("/headerValues")
	String headerValues(@RequestHeader Collection<String> headerValues);

	@GetExchange("/param")
	String requestParam(@RequestParam String param);

	@GetExchange("/params")
	String requestParams(@RequestParam Map<String, String> params);

	@GetExchange("/paramValues")
	String requestParamValues(@RequestParam Collection<String> paramValues);

	@GetExchange("/cookie")
	String cookie(@CookieValue String cookie);

	@GetExchange("/cookies")
	String cookies(@CookieValue Map<String, String> cookie);

	@GetExchange("/cookieValues")
	String cookieValues(@CookieValue Collection<String> cookieValues);

	@GetExchange("/{variable}")
	String pathVariable(@Nullable @PathVariable String variable);

	@PostExchange()
	VerificationResult verify(@RequestBody Optional<VerificationRequest> request);


	@PostExchange(value = "/parts", contentType = MediaType.MULTIPART_FORM_DATA_VALUE)
	String postParts(@RequestPart String part1, @RequestPart HttpEntity<String> part2);

	@PostExchange(value = "/multipart", contentType = MediaType.MULTIPART_FORM_DATA_VALUE)
	String postMultipart(MultipartFile file);


//	Supported by WebClient only at this point
//	@GetExchange("/attribute")
//	Mono<String> attribute(@RequestAttribute String org);

}
