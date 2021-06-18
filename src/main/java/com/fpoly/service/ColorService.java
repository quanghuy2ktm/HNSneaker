package com.fpoly.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.Color;
import com.fpoly.repositories.ColorRepository;

@Service

public class ColorService {
	@Autowired
	ColorRepository colorRepository;
	
	public Optional<Color> findByID(int id){
		return colorRepository.findById(id);
	}
	
}
