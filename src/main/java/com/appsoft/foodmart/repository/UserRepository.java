package com.appsoft.foodmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsoft.foodmart.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsernameAndPassword(String username,String password);
	
}
