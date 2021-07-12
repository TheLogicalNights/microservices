package com.microservices.UsersApi.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.UsersApi.model.UserLoginRequestModel;
import com.microservices.UsersApi.services.UserServices;
import com.microservices.UsersApi.shared.UserDto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private UserServices userServiceObj;
	private Environment environmentObj;
	
	public AuthenticationFilter(UserServices userServiceObj, Environment environmentObj) {
		this.userServiceObj = userServiceObj;
		this.environmentObj = environmentObj;
	}
	
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

	@Override
	protected void successfulAuthentication(HttpServletRequest request, 
											HttpServletResponse response, 
											FilterChain chain,
											Authentication authResult) throws IOException, ServletException {
		String userName = ((User) authResult.getPrincipal()).getUsername();
		UserDto userDetails = userServiceObj.getUserByEmail(userName);
		
		String token = Jwts.builder()
				.setSubject(userDetails.getUserId())
				.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environmentObj.getProperty("token.expiration_time"))))
				.signWith(SignatureAlgorithm.HS512, environmentObj.getProperty("token.secret"))
				.compact();
		
		response.addHeader("token", token);
		response.addHeader("userId", userDetails.getUserId());
	}

}
