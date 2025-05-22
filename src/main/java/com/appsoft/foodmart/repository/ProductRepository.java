package com.appsoft.foodmart.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsoft.foodmart.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	
	   List<Product> findByCategory(String category);
}
