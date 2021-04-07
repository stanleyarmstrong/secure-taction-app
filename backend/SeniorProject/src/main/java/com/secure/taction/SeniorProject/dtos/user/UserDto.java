package com.secure.taction.SeniorProject.dtos.user;

import org.springframework.lang.NonNull;

public class UserDto {

    private String userId;
	@NonNull
	private String userName;
	@NonNull
	private String email;
	@NonNull
	private String password;
	private String phoneNumber;
	private String firstName;
	private String lastName;

	public UserDto() {}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UserDto withUserId(String userId) {
		setUserId(userId);
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public UserDto withFirstName(String firstName) {
		setFirstName(firstName);
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public UserDto withLastName(String lastName) {
		setLastName(lastName);
		return this;
	}

	@Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public UserDto withUserName(String username) {
		setUserName(username);
		return this;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserDto withEmail(String email) {
		setEmail(email);
		return this;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserDto withPassword(String password) {
		setPassword(password);
		return this;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public UserDto withPhoneNumber(String phoneNumber) {
		setPhoneNumber(phoneNumber);
		return this;
	}
}
