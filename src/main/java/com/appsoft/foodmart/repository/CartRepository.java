package com.appsoft.foodmart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsoft.foodmart.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	  // Find a cart item by user ID and product ID (to check for duplicates)
    Optional<Cart> findByUserIdAndProductId(int userId, int productId);
    
    List<Cart> findByUserId(int userId);
    
    void deleteByUserId(int userId);
    
   
	
}
