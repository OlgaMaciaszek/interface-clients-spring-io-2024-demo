package com.example.rsocketverification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.rsocket.messaging.RSocketStrategiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.util.MimeType;

@SpringBootApplication
public class RsocketVerificationApplication {




	public static void main(String[] args) {
		SpringApplication.run(RsocketVerificationApplication.class, args);
	}


	public static final String TEST_HEADER = "test-header";
	public static final MimeType TEST_MIME_TYPE = MimeType.valueOf("application/json");

	@Bean
	RSocketStrategiesCustomizer rSocketStrategiesCustomizer() {
		return strategies -> strategies.metadataExtractorRegistry(
						registry -> registry.metadataToExtract(TEST_MIME_TYPE, String.class, TEST_HEADER))
				.decoder(new Jackson2JsonDecoder())
				.encoder(new Jackson2JsonEncoder());

	}


}
