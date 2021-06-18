package com.fpoly.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.Product_Img;
import com.fpoly.repositories.Product_ImgRepository;

@Service
public class Product_ImgService {
	
	@Autowired
	Product_ImgRepository product_ImgRepository;
	
	public Optional<Product_Img> findById(int id){
		return product_ImgRepository.findById(id);
	}
	
}
