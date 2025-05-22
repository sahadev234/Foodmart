package com.appsoft.foodmart.controller;

import java.util.List;

import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appsoft.foodmart.model.Cart;
import com.appsoft.foodmart.model.Category;
import com.appsoft.foodmart.model.Order;
import com.appsoft.foodmart.model.User;
import com.appsoft.foodmart.service.CartService;
import com.appsoft.foodmart.service.CategoryService;
import com.appsoft.foodmart.service.OrderService;
import com.appsoft.foodmart.service.ProductService;
import com.appsoft.foodmart.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	OrderService orderService;

	@GetMapping("/add")
	public String getCategory() {
		
		return "CategoryForm";
	}
	
	@PostMapping("/add")
	public String postCategory(@ModelAttribute Category cg){
		
		categoryService.addCategory(cg);
		
		return "CategoryForm";
	}
	
	@GetMapping("/list")
	public String getCategoryList(Model model) {
		
		model.addAttribute("clist",categoryService.getAllCategory());
		
		return "CategoryListForm";
	}
	
	@GetMapping("/delete")
	public String deleteCategory(@RequestParam("id") int id) {
		
		categoryService.deleteCategoryById(id);
		return "redirect:list";
	}
	
	@GetMapping("/edit")
	public String getEditCategory(@RequestParam("id") int id, Model model) {
		
		model.addAttribute("category",categoryService.getCategoryById(id));
		return "EditCategory";
	}
	
	@PostMapping("/update")
	public String postUpdate(@ModelAttribute Category cg) {
		
		categoryService.updateCategory(cg);
		return "redirect:list";
	}
	
	@GetMapping("/drink")
	public String getDrink(@RequestParam("category") String category,@RequestParam("id") int id, Model model) {
		
		model.addAttribute("clist",categoryService.getAllCategory());
		model.addAttribute("foodlist",productService.getProductByCategory(category));
		model.addAttribute("currUser",userService.getUserById(id));
		model.addAttribute("cartlist",cartService.getCartById(id));
		model.addAttribute("totalprice",cartService.calculateTotalPrice(id));
		model.addAttribute("cartcount",cartService.countCart(id));
		
		return "FilterByCategories";
		
		
	}
	
	@PostMapping("/addCart")
	public String getCart(@ModelAttribute Cart cart, @RequestParam("userid") int userid, @RequestParam("productid") int productid,Model model) {
		
		cart.setUser(userService.getUserById(userid));
		cart.setProduct(productService.getProductById(productid));
		
		cartService.addCart(cart);
		
		
		
		return "redirect:drink?category=" + productService.getProductById(productid).getCategory() + "&id=" + userid;
		
		

	}
	
	@GetMapping("/remove")
	public String removeCart(@RequestParam("cartId") int cartId,@RequestParam("userId") int userId, Model model) {
		
		cartService.deleteCart(cartId);
		
		User usr= userService.getUserById(userId);
		
		
		 model.addAttribute("clist",categoryService.getAllCategory());
		  model.addAttribute("user",usr);
		  model.addAttribute("cartCount",cartService.countCart(usr.getId()));
		  model.addAttribute("totalprice",cartService.calculateTotalPrice(usr.getId()));
		  model.addAttribute("cartItems",cartService.getCartById(userId));
		
		return "CustomerHome";
	}
	
	
	@GetMapping("/checkout")
	public String getCheckOut(@RequestParam("id") int id,Model model) {
		
		model.addAttribute("userId",id);
		model.addAttribute("cartlist",cartService.getCartById(id));
		model.addAttribute("totalprice",cartService.calculateTotalPrice(id));
		
		return "Checkout";
	}
	
	
	@GetMapping("/profile")
	public String getProfile(@RequestParam("id") int id,Model model) {
		
		model.addAttribute("user",userService.getUserById(id));
		
		return "CustomerProfile";
	}
	
	@PostMapping("/profile")
	public String postProfile(@ModelAttribute User u) {
		
		userService.updateUser(u);
		
		return "redirect:profile?id=" +u.getId();
	}
	
	@GetMapping("/orderHistory")
	public String getOrderHistory(HttpSession session,Model model) {
		
		User user=(User) session.getAttribute("activeuser");
		
		if(user==null) {
			
			return "LoginForm";
		}
		
		int id=user.getId();
		
	     List<Order> orderList=orderService.getOrderByUserId(id);
	     
	     model.addAttribute("orderList",orderList);
		
		return "UserOrderHistory";
	}
	

}
