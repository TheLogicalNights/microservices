package com.microservices.UsersApi.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserModel {
	@NotNull(message = "First Name cannot be null")
	private String firstName;
	@NotNull(message = "Last Name cannot be null")
	private String lastName;
	@NotNull(message = "Email address cannot be null")
	@Email
	private String email;
	@NotNull(message = "Password cannot be null")
	@Size(min = 6, max = 12,message = "Password must be of min length=6 and max length=12")
	private String password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
