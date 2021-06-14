package com.fpoly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

}
