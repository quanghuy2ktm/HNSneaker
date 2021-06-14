package com.fpoly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.model.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {

}
