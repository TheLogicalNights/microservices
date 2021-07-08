package com.microservices.UsersApi.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.UsersApi.model.UserModel;

@RestController
@RequestMapping("/users")
public class UserController {

	@GetMapping("/status")
	public String status()
	{
		return "Working...";
	}
	
	@PostMapping
	public String createUser(@Valid @RequestBody UserModel userObj)
	{
		return "createUser was called";
	}
}
