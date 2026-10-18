package com.vnk.eassy_buy.controller.product;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vnk.eassy_buy.dto.ProductDto;
import com.vnk.eassy_buy.service.product.ProductService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
	private final ProductService productService;

	@PostMapping
	public String addProduct(@RequestBody ProductDto productDto) {

		return productService.addProduct(productDto);
	}

	@DeleteMapping("/{productId}")
	public String deleteProduct(@PathVariable Long productId) {
		return productService.deleteProduct(productId);
	}

	@PutMapping("/{productId}")
	public String putMethodName(@PathVariable Long productId, @RequestBody ProductDto productDto) {
		return productService.updateProduct(productId, productDto);
	}

}
