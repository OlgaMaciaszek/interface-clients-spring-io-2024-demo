package com.example.http.verification.client.controllers;

import java.util.UUID;

import com.example.http.verification.client.clients.PersonService;
import com.example.http.verification.client.dto.Person;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

/**
 * @author Olga Maciaszek-Sharma
 */
@RestController
@RequestMapping("/persons")
class PersonController {

	private final PersonService personService;

	PersonController(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping("/{id}")
	public Person getPerson(@PathVariable UUID id) {
		return personService.getPerson(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void add() {
		personService.add(new Person("Tom", "Connor", "t.connor@example.com"));
	}

	@GetExchange("/test")
	String test() {
		return personService.test();
	}
}