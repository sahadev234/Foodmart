package com.appsoft.foodmart.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.appsoft.foodmart.model.User;
import com.appsoft.foodmart.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class UserRestController {
	
	@Autowired
     UserService userService;
    
	@GetMapping("/api/user/list")
	public List<User> getAllUsers() {
		
		return userService.getAllUser();
	}
	
	@GetMapping("/api/user/{id}")
	public User getUserById(@PathVariable("id") int id) {
		
		
		return userService.getUserById(id);
	}
	
	@PostMapping("/api/user/add")
	public String addUser(@org.springframework.web.bind.annotation.RequestBody User user) {
		
		userService.addUser(user);
		
		return "added success";
	}
	
	@DeleteMapping("/api/user/delete/{id}")
	public String deleteUser(@PathVariable("id") int id) {
		
		userService.deleteUser(id);
		
		return "deletion success";
	}
	
	@PostMapping("/api/user/update")
	public String updateUser(@org.springframework.web.bind.annotation.RequestBody User user) {
		
		userService.updateUser(user);
		
		return "update success";
	}
	
	@GetMapping("/api/user/j2o")
	public String j2o() {
		
		RestTemplate restTemplate=new RestTemplate();
		User user=restTemplate.getForObject("http://localhost:8080/api/user/16",User.class);
		
		return "FName="+user.getFname();
	}
	
	@GetMapping("/api/user/j2oa")
	public String j2oa() {
		
		RestTemplate restTemplate=new RestTemplate();
		User users[]=restTemplate.getForObject("http://localhost:8080/api/user/list",User[].class);
		
		return "FullName="+users[0].getFname()+users[0].getLname();
	}
	
}
