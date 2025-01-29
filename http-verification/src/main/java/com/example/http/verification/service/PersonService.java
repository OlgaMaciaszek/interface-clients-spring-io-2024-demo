package com.example.http.verification.service;


import java.util.UUID;

import com.example.http.verification.dto.Person;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

/**
 * @author Olga Maciaszek-Sharma
 */
@HttpExchange("/persons")
public interface PersonService {

	@GetExchange("/{id}")
	Person getPerson(@PathVariable UUID id);

	@PostExchange
	void add(@RequestBody Person person);

	@GetExchange("/test")
	String test();
}