package com.appsoft.foodmart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsoft.foodmart.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	List<Order> findByUserId(int userId);
	
	List<Order> findByStatus(String status);
	

}
