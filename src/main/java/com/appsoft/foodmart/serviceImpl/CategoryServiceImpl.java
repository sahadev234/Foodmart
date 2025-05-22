package com.appsoft.foodmart.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsoft.foodmart.model.Category;
import com.appsoft.foodmart.repository.CategoryRepository;
import com.appsoft.foodmart.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void addCategory(Category category) {
		
		categoryRepository.save(category);
		
	}

	@Override
	public void deleteCategoryById(int id) {
		
		categoryRepository.deleteById(id);
	}

	@Override
	public void updateCategory(Category category) {
		categoryRepository.save(category);
		
	}

	@Override
	public List<Category> getAllCategory() {
		
		return categoryRepository.findAll();
	}

	@Override
	public Category getCategoryById(int id) {
		
		return categoryRepository.getById(id);
	}



}
