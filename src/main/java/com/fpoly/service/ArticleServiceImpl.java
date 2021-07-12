package com.fpoly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fpoly.model.Product_Detail;
import com.fpoly.repositories.ArticleSpecification;
import com.fpoly.repositories.Product_DetailRepository;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	Product_DetailRepository prodDetailRepos;
	
	@Override
	public Page<Product_Detail> findArticlesByCriteria(Pageable pageable, Integer priceLow, Integer priceHigh, List<String> sizes, List<String> categories, List<String> brands, String search) {		
		Page<Product_Detail> page = prodDetailRepos.findAll(ArticleSpecification.filterBy(priceLow, priceHigh, sizes, categories, brands, search), pageable);
        return page;		
	}

	@Override
	public Product_Detail findArticleById(Integer idProductDetail) {
		Optional<Product_Detail> opt = prodDetailRepos.findById(idProductDetail);
		return opt.get();
	}	

}
