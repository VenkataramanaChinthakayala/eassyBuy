package com.vnk.eassy_buy.service.cart;

import com.vnk.eassy_buy.dto.cart.AddCartItemRequest;
import com.vnk.eassy_buy.dto.cart.CartMergeRequest;
import com.vnk.eassy_buy.dto.cart.CartResponse;
import com.vnk.eassy_buy.dto.cart.UpdateCartItemRequest;

public interface CartService {

	CartResponse getCart(Integer userId);

	CartResponse addItem(Integer userId, AddCartItemRequest request);

	CartResponse updateItem(Integer userId, Long itemId, UpdateCartItemRequest request);

	CartResponse removeItem(Integer userId, Long itemId);

	void clearCart(Integer userId);

	CartResponse mergeGuestCart(Integer userId, CartMergeRequest request);

}
