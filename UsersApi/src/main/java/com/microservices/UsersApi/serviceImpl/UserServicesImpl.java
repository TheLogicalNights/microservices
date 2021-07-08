package com.microservices.UsersApi.serviceImpl;

import java.util.UUID;

import com.microservices.UsersApi.services.UserServices;
import com.microservices.UsersApi.shared.UserDto;

public class UserServicesImpl implements UserServices {

	@Override
	public UserDto createUser(UserDto userDetails) {
		userDetails.setUserId(UUID.randomUUID().toString());
		return null;
	}

}
