package com.appsoft.foodmart.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsoft.foodmart.model.Product;
import com.appsoft.foodmart.repository.ProductRepository;
import com.appsoft.foodmart.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public void addProduct(Product pd) {
		 productRepository.save(pd);
		
	}

	@Override
	public void deleteProduct(int id) {
		
		productRepository.deleteById(id);
	}

	@Override
	public void updateProduct(Product pd) {
		productRepository.save(pd);
		
	}

	@Override
	public List<Product> getAllProduct() {
		
		return productRepository.findAll();
	}

	@Override
	public List<Product> getProductByCategory(String category) {
		
		return productRepository.findByCategory(category);
	}

	@Override
	public Product getProductById(int id) {
	        
		return productRepository.getById(id);
	}

	
	
}
