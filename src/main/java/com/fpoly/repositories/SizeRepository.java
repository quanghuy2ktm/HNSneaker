package com.fpoly.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpoly.model.Size;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {
	
	@Query(value = "select * from Size where value=?1", nativeQuery = true)
	public List<Size> findByValue(int id);
	
	@Query("SELECT DISTINCT s.value FROM Size s order by s.value")
	List<String> findAllSizes();
}
