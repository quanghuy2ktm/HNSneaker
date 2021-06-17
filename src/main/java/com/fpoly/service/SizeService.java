package com.fpoly.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.Size;
import com.fpoly.repositories.SizeRepository;

@Service
public class SizeService {

	@Autowired
	SizeRepository sizeRepository;
	
	public Optional<Size> findById(int id){
		return sizeRepository.findById(id);
	}
	
}
