package com.prabal.security.models;

import java.util.List;

public class UserInfoResponse {

	private String token;
	private String username;
	
	private List<String> roles;
	

	public UserInfoResponse() {
		super();
	}


	public UserInfoResponse(String token,  String username, List<String> roles) {
		super();
		this.token = token;
		this.username = username;
		this.roles = roles;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public List<String> getRoles() {
		return roles;
	}


	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
}
