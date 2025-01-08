package com.prabal.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prabal.security.models.UserPrincipal;
import com.prabal.security.models.Users;
import com.prabal.security.repositories.UserDetailsRepo;

@Service 
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserDetailsRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user= userRepo.findByUsername(username);
		if(user!=null) {
			return new UserPrincipal(user);
		}
		throw new UsernameNotFoundException("Username not found");
	}

}
