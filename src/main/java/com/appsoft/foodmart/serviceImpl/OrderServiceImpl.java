package com.appsoft.foodmart.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsoft.foodmart.model.Order;
import com.appsoft.foodmart.repository.OrderRepository;
import com.appsoft.foodmart.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepository orderRepository;

	@Override
	public void addOrder(Order order) {
		orderRepository.save(order);
		
	}

	@Override
	public void deleteOrder(int id) {
		
		orderRepository.deleteById(id);
	}

	@Override
	public void updateOrder(Order order) {
		
		orderRepository.save(order);
	}

	@Override
	public List<Order> getAllOrder() {
		
		return orderRepository.findAll();
	}

	@Override
	public List<Order> getOrderByUserId(int userid) {
		
		
		
		return orderRepository.findByUserId(userid);
	}
	
	@Override
	public Order getOrderById(int id) {
		
		
		
		return orderRepository.getById(id);
	}

	@Override
	public long getTotalOrderValue() {
		  List<Order> orderlist= orderRepository.findAll();
		  long total=0;
		  for(Order order:orderlist) {
			  
			  total=total+order.getTotal_amount();
			  
		  }
		return total;
	}

	@Override
	public long totalOrderCount() {
		
		long count=orderRepository.count();
		
		return count;
	}

	@Override
	public int totalApprovedOrder() {
		
		List<Order>orderList=orderRepository.findByStatus("APPROVED");
		int approvedOrder=0;
		for(Order order:orderList) {
			
			approvedOrder=approvedOrder+1;
		}
		
		return approvedOrder;
	}

	@Override
	public int totalPendingOrder() {
	

		
		List<Order>orderList=orderRepository.findByStatus("PENDING");
		int pendingOrder=0;
		for(Order order:orderList) {
			
			pendingOrder=pendingOrder+1;
		}
		
		return pendingOrder;
	}
	
	

}
