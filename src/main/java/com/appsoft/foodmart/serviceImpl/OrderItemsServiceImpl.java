package com.appsoft.foodmart.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsoft.foodmart.model.OrderItems;
import com.appsoft.foodmart.repository.OrderItemsRepository;
import com.appsoft.foodmart.service.OrderItemsService;

@Service
public class OrderItemsServiceImpl implements OrderItemsService{
	
	@Autowired
	OrderItemsRepository orderItemsRepository;
	

	@Override
	public void addOrderItems(OrderItems emp) {
		
		orderItemsRepository.save(emp);
	}
	@Override
	public void deleteById(int id) {         orderItemsRepository.deleteById(id);
		
	}

	@Override
	public List<OrderItems> getAllOrderItem() {
		return orderItemsRepository.findAll();
	}


}