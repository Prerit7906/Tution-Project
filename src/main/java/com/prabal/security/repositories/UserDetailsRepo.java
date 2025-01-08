package com.prabal.security.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prabal.security.models.Users;

@Repository
public interface UserDetailsRepo extends CrudRepository<Users, Long> {

	Users findByUsername(String username);

}
