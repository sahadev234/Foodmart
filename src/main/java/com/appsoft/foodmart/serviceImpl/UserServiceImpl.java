package com.appsoft.foodmart.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsoft.foodmart.model.User;
import com.appsoft.foodmart.repository.UserRepository;
import com.appsoft.foodmart.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	

	@Override
	public void addUser(User emp) {
		 userRepository.save(emp);
	
	}

	@Override
	public void deleteUser(int id) {
		 userRepository.deleteById(id);
		
	}

	@Override
	public void updateUser(User emp) {
		 userRepository.save(emp);
		
	}

	@Override
	public User login(String username, String password) {
		 
	
		
		return userRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public List<User> getAllUser() {
		
		List<User> ulist=userRepository.findAll();
		return ulist;
	}

	@Override
	public User getUserById(int id) {
		
		return userRepository.getById(id);
	}

	@Override
	public int countUsers() {
		
		List<User>ulist=userRepository.findAll();
		int total=0;
		for(User u:ulist) {
			
			if(u.getRole().equals("customer")) {
				
				total=total+1;
			}
			
			
		}
		
		return total;
	}
	
	

}
