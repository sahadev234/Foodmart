package com.appsoft.foodmart.service;

import java.util.List;

import com.appsoft.foodmart.model.Category;

public interface CategoryService {

	
	public void addCategory(Category category);
	public void updateCategory(Category category);
	public List<Category> getAllCategory();
	public Category getCategoryById(int id);
	public void deleteCategoryById(int id);
}
