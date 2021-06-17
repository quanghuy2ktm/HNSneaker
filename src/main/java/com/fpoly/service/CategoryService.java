package com.fpoly.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.Category;
import com.fpoly.repositories.CategoryRepository;

@Service
public class CategoryService {

	
	@Autowired
	CategoryRepository categoryRepository;
	
	
	public Optional<Category> findById(int id){
		return categoryRepository.findById(id);
	}
	
	
}
