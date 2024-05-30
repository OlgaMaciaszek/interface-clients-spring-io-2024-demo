package com.example.rsocketverificationclient.clients;

import reactor.core.publisher.Mono;

import org.springframework.messaging.rsocket.service.RSocketExchange;

/**
 * @author Olga Maciaszek-Sharma
 */
public interface CountService {

	@RSocketExchange("count")
	Mono<Integer> count();

}
