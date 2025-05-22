package com.appsoft.foodmart.service;

import java.util.List;

import com.appsoft.foodmart.model.Product;


public interface ProductService {

	
	public void addProduct(Product pd);
	public void deleteProduct(int id);
	public void updateProduct(Product pd);
	public List<Product> getAllProduct();
	public List<Product> getProductByCategory(String category);
	public Product getProductById(int id);
}
