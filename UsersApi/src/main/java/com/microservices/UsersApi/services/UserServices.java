package com.microservices.UsersApi.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.microservices.UsersApi.shared.UserDto;

public interface UserServices extends UserDetailsService{
	Boolean createUser(UserDto userDetails);
}
