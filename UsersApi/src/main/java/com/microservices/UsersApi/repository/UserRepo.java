package com.microservices.UsersApi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.microservices.UsersApi.entity.UserEntity;

@Service
public interface UserRepo extends CrudRepository<UserEntity, Long> {
	public UserEntity findByEmail(String email); 
}
