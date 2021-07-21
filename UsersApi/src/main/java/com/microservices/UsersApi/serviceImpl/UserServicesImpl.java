package com.microservices.UsersApi.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.UsersApi.entity.UserEntity;
import com.microservices.UsersApi.model.PostModel;
import com.microservices.UsersApi.repository.UserRepo;
import com.microservices.UsersApi.services.UserServices;
import com.microservices.UsersApi.shared.UserDto;

@Service
public class UserServicesImpl implements UserServices {

	@Autowired
	UserRepo userRepositoryObj;
	RestTemplate restTemplate;
	//for password encryption
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Autowired
	public UserServicesImpl(BCryptPasswordEncoder bCryptPasswordEncoder,RestTemplate restTemplate) 
	{
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.restTemplate = restTemplate;
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



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntityObj = userRepositoryObj.findByEmail(username);
		
		if(userEntityObj==null) throw new UsernameNotFoundException(username);
		
		return new User(userEntityObj.getEmail(),userEntityObj.getEncryptedPassword(),true,true,true,true,new ArrayList<>());
	}



	@Override
	public UserDto getUserByEmail(String email) {
		UserEntity userEntityObj = userRepositoryObj.findByEmail(email);
		
		if(userEntityObj==null) throw new UsernameNotFoundException(email);
		
		return new ModelMapper().map(userEntityObj, UserDto.class);
	}



	public UserDto getUserByUserId(String userId) {
		UserEntity userEntity = userRepositoryObj.findByUserId(userId);
		if(userEntity==null) throw new UsernameNotFoundException(userId);
		UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);
		String postUrl = String.format("http://POSTS-WS/users/%s/posts",userId);
		ResponseEntity<List<PostModel>> postListResponse = restTemplate.exchange(postUrl,HttpMethod.GET,null,new ParameterizedTypeReference<List<PostModel>>() {
		});
		List<PostModel> postList = postListResponse.getBody();
		userDto.setPosts(postList);
		return userDto;
	}

}
