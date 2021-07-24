package com.fpoly.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpoly.model.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {

	@Query("SELECT DISTINCT s.ColorName FROM Color s order by s.ColorName")
	List<String> findAllColor();
	
	@Query(value="SELECT * FROM Color where ColorName = ?1",nativeQuery = true)
	List<Color> findByColor(String id);
	
}
