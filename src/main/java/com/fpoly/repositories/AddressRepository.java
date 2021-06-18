package com.fpoly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
