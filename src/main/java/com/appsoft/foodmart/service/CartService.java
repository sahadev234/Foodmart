package com.appsoft.foodmart.service;

import java.util.List;

import com.appsoft.foodmart.model.Cart;

public interface CartService {

	public void addCart(Cart cart);
	public void deleteCart(int id);
	public void updateCart(Cart cart);
	public List<Cart> getCartById(int id);
	List<Cart> getAllCarts();
     long calculateTotalPrice(int userid);
     int countCart(int userid);
    
}
