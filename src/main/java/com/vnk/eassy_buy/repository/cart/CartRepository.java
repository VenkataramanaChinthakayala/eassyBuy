package com.vnk.eassy_buy.repository.cart;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vnk.eassy_buy.Entity.cart.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	Optional<Cart> findByUser_Id(Integer userId);
}
