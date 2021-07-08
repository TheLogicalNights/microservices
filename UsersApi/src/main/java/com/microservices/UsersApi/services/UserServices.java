package com.microservices.UsersApi.services;

import com.microservices.UsersApi.shared.UserDto;

public interface UserServices {
	Boolean createUser(UserDto userDetails);
}
