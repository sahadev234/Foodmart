package com.appsoft.foodmart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/useractivity")
public class UserActivityController {

	@GetMapping("/view")
	public String viewUserActivity() {
		
		
		return "UserActivity";
	}
}
