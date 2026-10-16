package com.vnk.eassy_buy.service.product_search;


import java.util.List;

import org.springframework.data.domain.Page;

import com.vnk.eassy_buy.dto.ProductDto;
import com.vnk.eassy_buy.dto.product_search.ProductSearchRequest;

public interface ProductSearchService {

	List<ProductDto> getAllProducts();

	Page<ProductDto> searchProduct(ProductSearchRequest productSearchRequest);

	ProductDto getProduct(Long productId);

}
