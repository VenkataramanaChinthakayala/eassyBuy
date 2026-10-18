package com.vnk.eassy_buy.dto.cart;

import java.math.BigDecimal;

public record CartItemResponse(Long itemId, Long productId, String productName, BigDecimal unitPrice, Integer quantity,
		BigDecimal subtotal) {
}
