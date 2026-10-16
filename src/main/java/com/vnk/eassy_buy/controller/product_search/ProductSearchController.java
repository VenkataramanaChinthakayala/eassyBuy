package com.vnk.eassy_buy.controller.product_search;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vnk.eassy_buy.dto.ProductDto;
import com.vnk.eassy_buy.dto.product_search.ProductSearchRequest;
import com.vnk.eassy_buy.service.product_search.ProductSearchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductSearchController {
	private final ProductSearchService productSearchService;

	@GetMapping("/search")
	public ResponseEntity<Page<ProductDto>> searchProducts(@ModelAttribute ProductSearchRequest productSearchRequest,
			Pageable pageable) {
		return new ResponseEntity<Page<ProductDto>>(productSearchService.searchProduct(productSearchRequest),
				HttpStatus.OK);
	}

	@GetMapping("/get_product/{id}")
	public ProductDto getProduct(@PathVariable Long id) {
		return productSearchService.getProduct(id);
	}

	@GetMapping("/view_all_products")
	public List<ProductDto> viewAllProducts() {
		return productSearchService.getAllProducts();
	}

}
