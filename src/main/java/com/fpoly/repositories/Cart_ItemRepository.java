package com.fpoly.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.model.Cart_Item;

@Repository
public interface Cart_ItemRepository extends JpaRepository<Cart_Item, Integer> {
	
	@EntityGraph(attributePaths = { "productDetail" })
	List<Cart_Item> findAllByUserAndCartIsNull(com.fpoly.model.User user);
	
	void deleteAllByUserAndCartIsNull(com.fpoly.model.User user);

	int countDistinctByUserAndCartIsNull(com.fpoly.model.User user);

}
