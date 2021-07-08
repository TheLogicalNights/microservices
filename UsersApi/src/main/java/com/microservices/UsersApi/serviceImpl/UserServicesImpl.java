package com.microservices.UsersApi.serviceImpl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservices.UsersApi.entity.UserEntity;
import com.microservices.UsersApi.repository.UserRepo;
import com.microservices.UsersApi.services.UserServices;
import com.microservices.UsersApi.shared.UserDto;

@Service
public class UserServicesImpl implements UserServices {

	@Autowired
	UserRepo userRepositoryObj;
	//for password encryption
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Autowired
	public UserServicesImpl(BCryptPasswordEncoder bCryptPasswordEncoder) 
	{
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}



	@Override
	public Boolean createUser(UserDto userDetails) {
		//userid generation
		userDetails.setUserId(UUID.randomUUID().toString());
		//password encryption
		userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		ModelMapper modelMapper = new ModelMapper();
		//for strict mapping of member variables
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		//for mapping of member variables from source object to destination object 
		UserEntity userEntityObj=modelMapper.map(userDetails, UserEntity.class);
		userRepositoryObj.save(userEntityObj);
		return true;
	}

}
