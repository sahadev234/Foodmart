package com.appsoft.foodmart.service;

import java.util.List;

import com.appsoft.foodmart.model.Order;

public interface OrderService {

	public void addOrder(Order order);
	public void deleteOrder(int id);
	public void updateOrder(Order order);
	public List<Order> getAllOrder();
	public List<Order> getOrderByUserId(int userid);
	public Order getOrderById(int id);
	public long getTotalOrderValue();
	public long totalOrderCount();
	public int totalApprovedOrder();
	public int totalPendingOrder();
}
