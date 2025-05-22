package com.appsoft.foodmart.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsoft.foodmart.model.Cart;
import com.appsoft.foodmart.repository.CartRepository;
import com.appsoft.foodmart.service.CartService;

import jakarta.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	CartRepository cartRepository;

	@Override
	public void addCart(Cart cart) {
        // Check if the product is already in the user's cart
        Optional<Cart> existingCartItem = cartRepository.findByUserIdAndProductId(cart.getUser().getId(), cart.getProduct().getId());
        
        if (existingCartItem.isPresent()) {
            // Update the quantity of the existing cart item if it exists
            Cart existingCart = existingCartItem.get();
            existingCart.setQuantity(existingCart.getQuantity() + cart.getQuantity());
            cartRepository.save(existingCart);
        } else {
            // If no existing cart item, add the new cart item
            cartRepository.save(cart);
        }
    }

    public Cart getCartByUserAndProduct(int userId, int productId) {
        return cartRepository.findByUserIdAndProductId(userId, productId).orElse(null);
    }

    // Other cart service methods...
		
	

	@Override
	@Transactional
	public void deleteCart(int id) {
		cartRepository.deleteByUserId(id);
		
	}

	@Override
	public void updateCart(Cart cart) {
		cartRepository.save(cart);
		
	}

	@Override
	public List<Cart> getCartById(int id) {
		
		return cartRepository.findByUserId(id);
	}

	@Override
	public List<Cart> getAllCarts() {
		
		return cartRepository.findAll();
	}

	@Override
	public long calculateTotalPrice(int userid) {
		
		List<Cart> clist= cartRepository.findByUserId(userid);
		long total=0;
		for(Cart cart :clist) {
			
			total=total+(cart.getProduct().getPrice()*cart.getQuantity());
		}
		
		return total;
	}
	
	

	@Override
	public int countCart(int userid) {
		List<Cart> clist= cartRepository.findByUserId(userid);
		int total=0;
		for(Cart cart :clist) {
			
			total=total+1;
		}
		
		return total;
	}

}
