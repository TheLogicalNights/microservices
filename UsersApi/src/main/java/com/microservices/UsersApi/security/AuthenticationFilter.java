package com.microservices.UsersApi.security;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.UsersApi.model.UserLoginRequestModel;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException 
	{
		try
		{
			UserLoginRequestModel userRequestModelObj = new ObjectMapper().readValue(request.getInputStream(),UserLoginRequestModel.class);
			return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
					userRequestModelObj.getEmail(),userRequestModelObj.getPassword(),new ArrayList<>()));
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
}
