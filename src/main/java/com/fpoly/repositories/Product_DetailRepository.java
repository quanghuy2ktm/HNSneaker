package com.fpoly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.model.Product_Detail;

@Repository
public interface Product_DetailRepository extends JpaRepository<Product_Detail, Integer> {

}
