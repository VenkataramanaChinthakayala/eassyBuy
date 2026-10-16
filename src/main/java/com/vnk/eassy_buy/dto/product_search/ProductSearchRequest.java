package com.vnk.eassy_buy.dto.product_search;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductSearchRequest {

    private String search;
    private String brand;
    private String category;

    private BigDecimal minPrice;
    private BigDecimal maxPrice;


    private Integer page = 0;
    private Integer size = 20;

    private String sortBy="productId";
    private String sortDirection = "asc";
}

