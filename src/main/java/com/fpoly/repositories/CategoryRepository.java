package com.fpoly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
