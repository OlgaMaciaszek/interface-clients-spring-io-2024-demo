package com.example.rsocketverification.controllers;

import java.util.Random;

import reactor.core.publisher.Mono;

import org.springframework.stereotype.Controller;

/**
 * @author Olga Maciaszek-Sharma
 */
@Controller
public class CountRsocketController implements CountService {


	private final Random random = new Random();

	@Override
	public Mono<Integer> count() {
		return Mono.just(random.nextInt(100) & Integer.MAX_VALUE);
	}
}
