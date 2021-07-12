package com.fpoly.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fpoly.model.Product_Detail;

@Service
public interface ArticleService {

	Page<Product_Detail> findArticlesByCriteria(Pageable pageable, Integer priceLow, Integer priceHigh, List<String> sizes,
			List<String> categories, List<String> brands, String search);

	Product_Detail findArticleById(Integer idProductDetail);

}
