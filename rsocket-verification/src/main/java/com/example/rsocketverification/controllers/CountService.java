package com.example.rsocketverification.controllers;

import reactor.core.publisher.Mono;

import org.springframework.messaging.rsocket.service.RSocketExchange;

/**
 * @author Olga Maciaszek-Sharma
 */
public interface CountService {

	@RSocketExchange("count")
	Mono<Integer> count();

}
