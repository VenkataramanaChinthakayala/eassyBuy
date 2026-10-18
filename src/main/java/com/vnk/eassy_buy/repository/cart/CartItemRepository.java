package com.vnk.eassy_buy.repository.cart;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vnk.eassy_buy.Entity.cart.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	 Optional<CartItem> findByIdAndCart_Id(Long itemId, Long cartId);
}
