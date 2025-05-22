package com.appsoft.foodmart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.appsoft.foodmart.service.OrderService;

@Controller
@RequestMapping("/sales")
public class SalesReportController {
	
	@Autowired
	OrderService orderService;

	@GetMapping("/view")
	public String viewSalesReport(Model model) {
		
		model.addAttribute("totalRevenue",orderService.getTotalOrderValue());
		model.addAttribute("totalOrders",orderService.totalOrderCount());
	
		return "SalesReport";
	}
}
