package com.example.http.verification.controllers;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.example.http.verification.dto.Person;
import com.example.http.verification.service.PersonService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Olga Maciaszek-Sharma
 */
@RestController
class PersonController implements PersonService {

	private final ConcurrentHashMap<UUID, Person> persons = new ConcurrentHashMap<>();

	public Person getPerson(@PathVariable UUID id) {
		return persons.get(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	public void add(@RequestBody Person person) {
		persons.put(UUID.randomUUID(), person);
	}

	@Override
	public String test() {
		return "test";
	}
}