package com.appsoft.foodmart.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appsoft.foodmart.model.Cart;
import com.appsoft.foodmart.model.Category;
import com.appsoft.foodmart.model.Order;
import com.appsoft.foodmart.model.User;
import com.appsoft.foodmart.service.CartService;
import com.appsoft.foodmart.service.CategoryService;
import com.appsoft.foodmart.service.OrderService;
import com.appsoft.foodmart.service.UserService;
import com.appsoft.foodmart.utils.MailUtils;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	MailUtils mailUtils;

	@GetMapping("/pendingList")
	public String getOrderList(Model model) {
		
		List<Order> orderList=orderService.getAllOrder();
		List<Order> newOrderList=new ArrayList<>();
		
		for(Order order:orderList) {
			
			if(order.getStatus().equals("PENDING")) {
				
				newOrderList.add(order);
				
			}
		}
		
		model.addAttribute("orderlist",newOrderList);
		
		return "OrderListForm";
	}
	
	@GetMapping("/place")
	public String placeOrder(HttpSession session,Model model) {
		
		User user= (User) session.getAttribute("activeuser");
		
		if(user==null) {
			
			return "LoginForm";
		}
		
		int activeid=user.getId();
		
		Order order=new Order();
		
		order.setUser(userService.getUserById(activeid));
		order.setTotal_amount(cartService.calculateTotalPrice(activeid));
		order.setPayment_details(null);
		
	    orderService.addOrder(order);
	    
	    cartService.deleteCart(activeid);
	    
	    
	    String toEmail=user.getEmail();
	    String subject = "Order Confirmation - FoodMart";
	    String message = """
	    Dear Customer,

	    Thank you for shopping with FoodMart! 🎉

	    We’re pleased to inform you that your order has been successfully placed. Our team is currently reviewing your order, and you will receive an update once it has been approved and is ready for delivery.

	    If you have any questions or need support, feel free to reply to this email or contact our customer service.

	    Thank you for choosing FoodMart. We look forward to serving you again!

	    Best regards,  
	    The FoodMart Team  
	    """;

	    		
	    mailUtils.sendEmail(toEmail,subject,message);
	    
	    model.addAttribute("id",activeid);
		return "OrderPlaced";
	}
	
	@GetMapping("/home")
	public String BackHome(HttpSession session,Model model) {
		
		User user=(User) session.getAttribute("activeuser");
		
		if(user==null) {
			
			return "LoginForm";
		}
				int id=user.getId();
		
		 model.addAttribute("clist",categoryService.getAllCategory());
		  model.addAttribute("user",userService.getUserById(id));
		  model.addAttribute("cartCount",cartService.countCart(id));
		  model.addAttribute("totalprice",cartService.calculateTotalPrice(id));
		  model.addAttribute("cartItems",cartService.getCartById(id));
		
		
		return "CustomerHome";
	}
	
	@GetMapping("/details")
	public String ViewDetails() {
		
		return "OrderDetails";
	}
	
	@GetMapping("/approve")
	public String acceptOrder(@RequestParam("id") int id,HttpSession session) {
		
		Order order=orderService.getOrderById(id);
		order.setStatus("APPROVED");
		
		orderService.updateOrder(order);
		
		
        int userId=order.getUser().getId();
        
        User user=userService.getUserById(userId);
		
		String toEmail=user.getEmail();
		String subject = "Your FoodMart Order Has Been Approved & Is On Its Way!";
		String message = """
		Dear Customer,

		Great news! 🎉 Your order has been approved and is now being ready for delivery.

		Our team is working to get your items to you as quickly as possible. Your order will be delivered to the address you provided during checkout.

		You’ll be notified once your order is delivered to you.

		Thank you for choosing FoodMart — we appreciate your business!

		Best regards,  
		The FoodMart Team
		""";

		
		mailUtils.sendEmail(toEmail, subject, message);
		
		return "redirect:pendingList";
	}
	@GetMapping("/reject")
	public String rejectOrder(@RequestParam("id") int id) {
		
		Order order=orderService.getOrderById(id);
		order.setStatus("REJECTED");
		
		orderService.updateOrder(order);
		
		return "redirect:pendingList";
	}
	
	
}
