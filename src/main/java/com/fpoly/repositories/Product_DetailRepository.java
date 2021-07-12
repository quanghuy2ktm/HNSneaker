package com.fpoly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpoly.model.Product_Detail;

@Repository
public interface Product_DetailRepository extends JpaRepository<Product_Detail, Integer>, JpaSpecificationExecutor<Product_Detail> {

	@Query(value = "select * from Product_Detail where id=:idp",nativeQuery = true)
	Product_Detail findByIdp(@Param("idp")Integer id);
}
