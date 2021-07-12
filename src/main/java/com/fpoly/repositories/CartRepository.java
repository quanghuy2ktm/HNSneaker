package com.fpoly.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.model.Cart;
import com.fpoly.model.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	List<Cart> findByUser(User user);

}
