package com.vnk.eassy_buy.service.product_search;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vnk.eassy_buy.dto.ProductDto;
import com.vnk.eassy_buy.dto.product_search.ProductSearchRequest;
import com.vnk.eassy_buy.Entity.Product;
import com.vnk.eassy_buy.repository.product.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductSearchServiceImpl implements ProductSearchService {
	private final ProductRepository productRepository;

	private static final Set<String> ALLOWED_SORT_FIELDS = Set.of("productName", "productPrice", "productDiscount",
			"stockQuantity", "productId");

	@Override
	public Page<ProductDto> searchProduct(ProductSearchRequest request) {
		log.info("Product search started");
		String search = normalize(request.getSearch());
		String brand = normalize(request.getBrand());
		String category = normalize(request.getCategory());
		log.debug("Product search parameters: search={}, brand={}, category={}, minPrice={}, maxPrice={}", search,
				brand, category, request.getMinPrice(), request.getMaxPrice());

		int page = request.getPage() != null && request.getPage() >= 0 ? request.getPage() : 0;

		int size = request.getSize() != null && request.getSize() > 0 ? Math.min(request.getSize(), 100) : 20;

		String sortBy = request.getSortBy();

		if (sortBy == null || !ALLOWED_SORT_FIELDS.contains(sortBy)) {
			log.debug("Invalid or missing sort field '{}', using default sort field 'productId'", sortBy);
			sortBy = "productId";
		}

		Sort.Direction direction = "desc".equalsIgnoreCase(request.getSortDirection()) ? Sort.Direction.DESC
				: Sort.Direction.ASC;

		Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
		log.debug("Product search pagination: page={}, size={}, sortBy={}, direction={}", page, size, sortBy,
				direction);

		Page<Product> searchProducts = productRepository.searchProducts(search, category, brand, request.getMinPrice(),
				request.getMaxPrice(), pageable);
		log.info("Product search completed: totalElements={}, totalPages={}, currentPage={}",
				searchProducts.getTotalElements(), searchProducts.getTotalPages(), searchProducts.getNumber());
		return searchProducts.map(this::convertToDto);
	}

	private String normalize(String value) {

		if (value == null || value.trim().isEmpty()) {
			return null;
		}

		return value.trim();
	}

	private ProductDto convertToDto(Product product) {
		return ProductDto.builder().productId(product.getProductId()).productName(product.getProductName())
				.description(product.getDescription()).productModel(product.getProductModel())
				.productPrice(product.getProductPrice()).productDiscount(product.getProductDiscount())
				.category(product.getCategory()).brand(product.getBrand()).stockQuantity(product.getStockQuantity())
				.attributs(product.getAttributs()).build();
	}

	@Override
	public List<ProductDto> getAllProducts() {
		log.info("Fetching all products");
		List<Product> allProducts = productRepository.findAll();
		log.info("Fetched {} products", allProducts.size());

		return allProducts.stream()
				.map(product -> new ProductDto(product.getProductId(), product.getProductName(),
						product.getDescription(), product.getProductModel(), product.getProductPrice(),
						product.getProductDiscount(), product.getCategory(), product.getBrand(),
						product.getStockQuantity(), product.getAttributs()))
				.toList();
	}

	@Override
	public ProductDto getProduct(Long productId) {
		log.info("Fetching product with id={}", productId);
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found"));
		log.info("Product found: id={}, name={}, brand={}", product.getProductId(), product.getProductName(),
				product.getBrand());

		return new ProductDto(product.getProductId(), product.getProductName(), product.getDescription(),
				product.getProductModel(), product.getProductPrice(), product.getProductDiscount(),
				product.getCategory(), product.getBrand(), product.getStockQuantity(), product.getAttributs());
	}
}
