package com.example.rsocketverificationclient.clients;

import com.example.rsocketverificationclient.dto.VerificationRequest;
import com.example.rsocketverificationclient.dto.VerificationResult;
import reactor.core.publisher.Mono;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.rsocket.service.RSocketExchange;
import org.springframework.util.MimeType;

/**
 * @author Olga Maciaszek-Sharma
 */
public interface VerificationService {

	@RSocketExchange("verify")
	Mono<VerificationResult> verify(@Payload VerificationRequest request);

	@RSocketExchange("verify")
	VerificationResult verifyBlocking(@Payload VerificationRequest request);

	@RSocketExchange("/test/{name}")
	Mono<String> test(@DestinationVariable("name") String name);

	@RSocketExchange("/testMetadata/{name}")
	Mono<String> test(@DestinationVariable("name") String name,
			String metadataEntry, MimeType mimeType);

}


