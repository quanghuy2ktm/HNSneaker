package com.fpoly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.repositories.Product_DetailRepository;

@Service
public class Product_DetailService {

	@Autowired
	Product_DetailRepository product_DetailRepository;
	
}
