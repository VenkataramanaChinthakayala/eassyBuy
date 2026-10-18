package com.vnk.eassy_buy.dto.cart;

import java.math.BigDecimal;
import java.util.List;

public record CartResponse(
	    Long cartId,
	    List<CartItemResponse> items,
	    Integer totalItems,
	    BigDecimal totalAmount
	) {}