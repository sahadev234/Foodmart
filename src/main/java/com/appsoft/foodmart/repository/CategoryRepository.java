package com.appsoft.foodmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsoft.foodmart.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	
	
}
