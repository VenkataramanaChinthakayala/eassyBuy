package com.vnk.eassy_buy.dto.cart;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CartMergeRequest(@NotNull @Valid List<GuestCartItem> items) {
	public record GuestCartItem(@NotNull Long productId, @NotNull @Min(1) Integer quantity) {
	}
}
