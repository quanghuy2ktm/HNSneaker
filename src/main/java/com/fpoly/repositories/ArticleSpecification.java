package com.fpoly.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.fpoly.model.Brand;
import com.fpoly.model.Category;
import com.fpoly.model.Product_Detail;
import com.fpoly.model.Size;


public class ArticleSpecification {
	
	private ArticleSpecification() {}
	
	@SuppressWarnings("serial")
	public static Specification<Product_Detail> filterBy(Integer priceLow, Integer priceHigh, List<String> sizes, 
												  List<String> categories, List<String> brands, String search) {	
		
		return new Specification<Product_Detail>() {
            @Override
            public Predicate toPredicate(Root<Product_Detail> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();                
                query.distinct(true);                
                if (sizes!=null && !sizes.isEmpty()) {
                	Join<Product_Detail, Size> joinSize = root.join("size");
                	predicates.add(criteriaBuilder.and(joinSize.get("value").in(sizes)));
                }
                if (categories!=null && !categories.isEmpty()) {
                	Join<Product_Detail, Category> joinSize = root.join("category");
                	predicates.add(criteriaBuilder.and(joinSize.get("categoryName").in(categories)));
                }   
                if (brands!=null && !brands.isEmpty()) {
                	Join<Product_Detail, Brand> joinSize = root.join("brand");
                	predicates.add(criteriaBuilder.and(joinSize.get("name").in(brands)));
                }  
                
                if(search!=null && !search.isEmpty()) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("title"), "%"+search+"%")));
                }
                if (priceLow!=null && priceLow >= 0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), priceLow)));
                }
                if (priceHigh!=null && priceHigh >= 0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("price"), priceHigh)));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };		
	}
}
