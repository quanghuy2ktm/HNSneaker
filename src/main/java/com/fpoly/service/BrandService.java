package com.fpoly.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.Brand;
import com.fpoly.repositories.BrandRepository;

@Service
public class BrandService {

	@Autowired
	BrandRepository brandRepository;
	
	
	public Optional<Brand> findById(int id){
		return brandRepository.findById(id);
	}
	
	
}
