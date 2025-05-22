package com.appsoft.foodmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appsoft.foodmart.model.Product;
import com.appsoft.foodmart.service.CategoryService;
import com.appsoft.foodmart.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;

	@GetMapping("/add")
	public String getProduct(Model model) {
		
		model.addAttribute("categorylist",categoryService.getAllCategory());
		
		return "ProductForm";
	}
	
	@PostMapping("/add")
	public String postProduct(@ModelAttribute Product pd) {
	
		productService.addProduct(pd);
		return "redirect:add";
	}
	
	@GetMapping("/list")
    public String getProductList(Model model) {
    	
		model.addAttribute("plist",productService.getAllProduct());
		
    	return "ProductListForm";
    }
	
	@GetMapping("/delete")
	public String deleteProduct(@RequestParam("id") int id) {
		
		productService.deleteProduct(id);
		
		return "redirect:list";
	}
	
	@GetMapping("/edit")
	public String editProduct(@RequestParam("id") int id,Model model) {
		
		model.addAttribute("product",productService.getProductById(id));
		
		return "ProductEditForm";
	}
	
	@PostMapping("/update")
	public String postUpdate(@ModelAttribute Product pd) {
		
		productService.updateProduct(pd);
		
		return "redirect:list";
	}
	
   
}
