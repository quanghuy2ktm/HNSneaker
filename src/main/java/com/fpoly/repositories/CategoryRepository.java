package com.fpoly.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpoly.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("SELECT DISTINCT c.categoryName FROM Category c order by c.categoryName")
	List<String> findAllCategories();

	@Query(value="SELECT * FROM Category where categoryName=?1", nativeQuery = true)
	List<Category> findByCategoryName(String id);
}
