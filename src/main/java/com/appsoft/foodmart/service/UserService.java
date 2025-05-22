package com.appsoft.foodmart.service;

import java.util.List;

import com.appsoft.foodmart.model.User;

public interface UserService {
     
	public void addUser(User emp);
	public void deleteUser(int id);
	public void updateUser(User emp);
	public User login(String username,String password);
	public List<User> getAllUser();
	public User getUserById(int id);
	public int countUsers();
}

