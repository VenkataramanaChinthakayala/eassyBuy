package com.vnk.eassy_buy.controller.cart;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vnk.eassy_buy.config.util.SecurityUtil;
import com.vnk.eassy_buy.dto.cart.AddCartItemRequest;
import com.vnk.eassy_buy.dto.cart.CartMergeRequest;
import com.vnk.eassy_buy.dto.cart.CartResponse;
import com.vnk.eassy_buy.dto.cart.UpdateCartItemRequest;
import com.vnk.eassy_buy.repository.UserRepository;
import com.vnk.eassy_buy.service.cart.CartService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor

public class CartController {

	private final CartService cartService;
	private final UserRepository userRepository;

	private Integer getCurrentUserId() {
		String email = SecurityUtil.userName();

		return userRepository.findByMail(email)
				.orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(HttpStatus.UNAUTHORIZED,
						"Authenticated user not found"))
				.getId();
	}

	@GetMapping
	public ResponseEntity<CartResponse> getCart(Authentication authentication) {

		return ResponseEntity.ok(cartService.getCart(getCurrentUserId()));
	}

	@PostMapping("/items")
	public ResponseEntity<CartResponse> addItem(Authentication authentication,
			@Valid @RequestBody AddCartItemRequest request) {

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(cartService.addItem(getCurrentUserId(), request));
	}

	@PutMapping("/items/{itemId}")
	public ResponseEntity<CartResponse> updateItem(Authentication authentication, @PathVariable Long itemId,
			@Valid @RequestBody UpdateCartItemRequest request) {

		return ResponseEntity.ok(cartService.updateItem(getCurrentUserId(), itemId, request));
	}

	@DeleteMapping("/items/{itemId}")
	public ResponseEntity<CartResponse> removeItem(Authentication authentication, @PathVariable Long itemId) {

		return ResponseEntity.ok(cartService.removeItem(getCurrentUserId(), itemId));
	}

	@DeleteMapping("/items")
	public ResponseEntity<Void> clearCart(Authentication authentication) {

		cartService.clearCart(getCurrentUserId());

		return ResponseEntity.noContent().build();
	}

	@PostMapping("/merge")
	public ResponseEntity<CartResponse> mergeGuestCart(Authentication authentication,
			@Valid @RequestBody CartMergeRequest request) {

		return ResponseEntity.ok(cartService.mergeGuestCart(getCurrentUserId(), request));
	}
}
