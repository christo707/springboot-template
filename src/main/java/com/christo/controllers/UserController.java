package com.christo.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.christo.entities.Users;
import com.christo.services.UserService;


@RestController
@RequestMapping("/v1")
public class UserController {

	@Autowired
	UserService userServiceImpl;
	
	@GetMapping("/users")
	public List<Users> retrieveAllUsers() {
		return (List<Users>) userServiceImpl.getUsers();
	}

	@GetMapping("/users/{id}")
	public Users retrieveUser(@PathVariable int id) {
		Users user = userServiceImpl.getUser(id);

		return user;
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userServiceImpl.deleteUser(id);
	}


	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody Users user) {
		Users savedUser = userServiceImpl.addUser(user);

		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId())
						.toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/users")
	public ResponseEntity<Object> updateUser(@Valid @RequestBody Users user) {
		Users savedUser = userServiceImpl.updateUser(user.getId(), user);

		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId())
						.toUri();

		return ResponseEntity.created(location).build();

	}
	
}
