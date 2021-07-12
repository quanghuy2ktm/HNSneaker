package com.fpoly.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpoly.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

	@Query("SELECT DISTINCT s.name FROM Brand s order by s.name")
	List<String> findAllBrand();
}
