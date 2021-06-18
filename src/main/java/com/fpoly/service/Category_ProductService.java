package com.fpoly.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.Category_Product;
import com.fpoly.repositories.Category_ProductRepository;

@Service
public class Category_ProductService {

	@Autowired
	Category_ProductRepository category_ProductRepository;
	
	public Optional<Category_Product> findById(int id){
		return category_ProductRepository.findById(id);
	}
	
	
}
