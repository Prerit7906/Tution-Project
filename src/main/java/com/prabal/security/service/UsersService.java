package com.prabal.security.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prabal.security.models.AppRole;
import com.prabal.security.models.LoginRequest;
import com.prabal.security.models.Role;
import com.prabal.security.models.SignUpRequest;
import com.prabal.security.models.UserInfoResponse;
import com.prabal.security.models.UserPrincipal;
import com.prabal.security.models.Users;
import com.prabal.security.repositories.UserDetailsRepo;

@Service
public class UsersService {
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	UserDetailsRepo userRepo;
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	JwtService jwtService;

	public ResponseEntity<UserInfoResponse> registerUser(SignUpRequest signUpRequest) {
		// TODO Auto-generated method stub
		Users user=new Users(signUpRequest.getUsername(),encoder.encode(signUpRequest.getPassword()));
		Set<String> strRoles=signUpRequest.getRoles();
		Set<Role> roles=new HashSet<Role>();
		if (strRoles == null) {
            Role userRole = new Role(AppRole.STUDENT);
            roles.add(userRole);
		}
		else {
			strRoles.forEach(strRole->{
				if (strRole.equalsIgnoreCase("admin")) {
        	        Role role= new Role(AppRole.ADMIN);
        	        roles.add(role);
        	    } else if(strRole.equalsIgnoreCase("tutor")){
        	        Role role = new Role(AppRole.TUTOR);
        	        roles.add(role);
        	    }
			});
		}
		if(roles.isEmpty()) {
			Role userRole = new Role(AppRole.STUDENT);
            roles.add(userRole);
		}
		user.setRoles(roles);
		userRepo.save(user);
		String token=jwtService.generateToken(user.getUsername());
		UserInfoResponse response=new UserInfoResponse(token, user.getUsername(), strRoles.stream().collect(Collectors.toList()));
		
		return ResponseEntity.ok(response);
	}

	public ResponseEntity<?> verifyUser(LoginRequest loginRequest) {
		// TODO Auto-generated method stub
		Authentication authentication=authManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		if(authentication.isAuthenticated()) {
			UserPrincipal user=(UserPrincipal) authentication.getPrincipal();
			List<String> roles=user.getAuthorities().stream().map(role->role.getAuthority()).collect(Collectors.toList());
			String token=jwtService.generateToken(user.getUsername());
			UserInfoResponse response=new UserInfoResponse(token,user.getUsername(),roles);
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	

}
