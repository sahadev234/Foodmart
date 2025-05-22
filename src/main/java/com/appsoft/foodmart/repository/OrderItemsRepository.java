package com.appsoft.foodmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsoft.foodmart.model.OrderItems;

public interface OrderItemsRepository extends JpaRepository<OrderItems,Integer>{

}
