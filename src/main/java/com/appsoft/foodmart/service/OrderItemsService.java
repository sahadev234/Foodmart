package com.appsoft.foodmart.service;

import java.util.List;

import com.appsoft.foodmart.model.OrderItems;


public interface OrderItemsService {
  
	public void addOrderItems(OrderItems emp);
	public void deleteById(int id);
	public List<OrderItems> getAllOrderItem();
}
