package com.appsoft.foodmart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appsoft.foodmart.model.User;
import com.appsoft.foodmart.service.UserService;

@Controller
@RequestMapping("/user")
public class ViewUserController {

	@Autowired
	UserService userService;

	@GetMapping("/list")
	public String getUserList(Model model) {

		model.addAttribute("userlist", userService.getAllUser());

		return "ViewUsers";
	}
     
	@GetMapping("/edit")
	public String editUser(@RequestParam("id") int id, Model model) {

		model.addAttribute("user", userService.getUserById(id));

		return "EditUserList";
	}
    
	@GetMapping("/delete")
	public String deleteUser(@RequestParam("id") int id) {

		userService.deleteUser(id);

		return "redirect:list";
	}
	
	@PostMapping("/update")
	public String postUpdate(@ModelAttribute User u) {
		
		userService.updateUser(u);
		
		return "redirect:list";
	}
	



}
