package com.microservices.UsersApi.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.UsersApi.model.UserModel;
import com.microservices.UsersApi.serviceImpl.UserServicesImpl;
import com.microservices.UsersApi.shared.UserDto;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	UserServicesImpl userServiceImplObj;

	@GetMapping("/status")
	public String status()
	{
		return "Working...with secret token - " + environment.getProperty("token.secret");
	}
	
	@PostMapping
	public String createUser(@Valid @RequestBody UserModel userObj)
	{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDtoObj = modelMapper.map(userObj, UserDto.class);
		Boolean bResult = userServiceImplObj.createUser(userDtoObj);
		if(bResult)
			return "User Created successfully";
		else
			return "Unable to create user";
	}
}
