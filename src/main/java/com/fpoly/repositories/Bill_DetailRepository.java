package com.fpoly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.model.Bill_Detail;

@Repository
public interface Bill_DetailRepository extends JpaRepository<Bill_Detail, Integer> {

}
