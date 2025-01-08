package com.prabal.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prabal.security.models.LoginRequest;
import com.prabal.security.models.SignUpRequest;
import com.prabal.security.models.Users;
import com.prabal.security.service.UsersService;

@RestController
@RequestMapping("/api/v1.0.0/auth")
public class UsersController {
	
	@Autowired
	private UsersService service;

	@PostMapping("/register")
	ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest){
		return service.registerUser(signUpRequest);
	}
	
	@GetMapping("/login")
	ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
		return service.verifyUser(loginRequest);
	}
}
