package com.appsoft.foodmart.controller;

import java.awt.geom.CubicCurve2D;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appsoft.foodmart.model.User;
import com.appsoft.foodmart.service.CartService;
import com.appsoft.foodmart.service.CategoryService;
import com.appsoft.foodmart.service.OrderService;
import com.appsoft.foodmart.service.UserService;
import com.appsoft.foodmart.utils.VerifyRecaptcha;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	OrderService orderService;
	
	

	@GetMapping({"/","/login"})
	public String getLogin() {
		
		return "LoginForm";
	}
	
	@PostMapping({"/","/login"})
	public String postLogin(@ModelAttribute User u,@RequestParam("g-recaptcha-response") String gCaptCode,HttpSession session,Model model) throws IOException {
		
		if(VerifyRecaptcha.verify(gCaptCode))
		{
		
			u.setPassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));
		User usr=userService.login(u.getUsername(),u.getPassword());
		
		if(usr!=null) {
			
			session.setAttribute("activeuser", usr);
			session.setMaxInactiveInterval(300);
			
			if(usr.getRole().equals("customer")) {
				
				  model.addAttribute("clist",categoryService.getAllCategory());
				  model.addAttribute("user",usr);
				  model.addAttribute("cartCount",cartService.countCart(usr.getId()));
				  model.addAttribute("totalprice",cartService.calculateTotalPrice(usr.getId()));
				  model.addAttribute("cartItems",cartService.getCartById(usr.getId()));
				return "CustomerHome";
			}
			else {
				
				model.addAttribute("usercount",userService.countUsers());
				model.addAttribute("totalOrderValue",orderService.getTotalOrderValue());
				model.addAttribute("totalApprovedOrders",orderService.totalApprovedOrder());
				model.addAttribute("totalPendingOrders",orderService.totalPendingOrder());
				model.addAttribute("totalOrders",orderService.totalOrderCount());
				return "index";
			}
		}
		else {
	    model.addAttribute("message","User Not found");
	    return "LoginForm";
		}
		
	}
	
		model.addAttribute("message","You are a robot");
		return "LoginForm";
	
	}
	
	@GetMapping("/signup")
	public String getSignup( ) {
		
		return "SignupForm";
	}
	
	@PostMapping("/signup")
	public String postSignup(@ModelAttribute User u,Model model) {
		
		
		u.setPassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));
		userService.addUser(u);
		
		return "LoginForm";
	}
	
	@GetMapping("/dashboard")
	public String getDashboard(Model model) {
		
		model.addAttribute("usercount",userService.countUsers());
		model.addAttribute("totalOrderValue",orderService.getTotalOrderValue());
		model.addAttribute("totalApprovedOrders",orderService.totalApprovedOrder());
		model.addAttribute("totalPendingOrders",orderService.totalPendingOrder());
		model.addAttribute("totalOrders",orderService.totalOrderCount());
	
		return "index";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "LoginForm";
	}
	
	

	
}
