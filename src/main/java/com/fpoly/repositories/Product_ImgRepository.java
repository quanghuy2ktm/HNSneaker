package com.fpoly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.model.Product_Img;

@Repository
public interface Product_ImgRepository extends JpaRepository<Product_Img, Integer> {

	
}
