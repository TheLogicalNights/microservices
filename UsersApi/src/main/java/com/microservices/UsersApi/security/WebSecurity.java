package com.microservices.UsersApi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.microservices.UsersApi.services.UserServices;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
	private Environment enviromentObj;
	private UserServices usersServiceObj;
	private BCryptPasswordEncoder bCryptPasswordEncoderObj;
	
	@Autowired
	public WebSecurity(Environment enviromentObj,UserServices usersServiceObj, BCryptPasswordEncoder bCryptPasswordEncoderObj)
	{
		this.enviromentObj = enviromentObj;
		this.usersServiceObj = usersServiceObj;
		this.bCryptPasswordEncoderObj = bCryptPasswordEncoderObj;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		//for JWT token validation disable
		http.csrf().disable();
		// for permit all the request from mentioned path
		http.authorizeRequests().antMatchers("/users/**").permitAll()
		.and()
		.addFilter(getAuthenticationFilter());
		// to disable frameoptions
		http.headers().frameOptions().disable();
	}
	
	private AuthenticationFilter getAuthenticationFilter() throws Exception
	{
		AuthenticationFilter authenticationFilterObj = new AuthenticationFilter(usersServiceObj,enviromentObj);
		authenticationFilterObj.setAuthenticationManager(authenticationManager());
		//for custom path
		authenticationFilterObj.setFilterProcessesUrl(enviromentObj.getProperty("login.url.path"));
		return authenticationFilterObj;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(usersServiceObj).passwordEncoder(bCryptPasswordEncoderObj);
	}
}
