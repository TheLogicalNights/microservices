package com.microservices.UsersApi.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		//for JWT token validation disable
		http.csrf().disable();
		// for permit all the request from mentioned path
		http.authorizeRequests().antMatchers("/users/**").permitAll();
		// to disable frameoptions
		http.headers().frameOptions().disable();
	}
}
