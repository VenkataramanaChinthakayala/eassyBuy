package com.vnk.eassy_buy.service.cart;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.vnk.eassy_buy.Entity.Product;
import com.vnk.eassy_buy.Entity.cart.Cart;
import com.vnk.eassy_buy.Entity.cart.CartItem;
import com.vnk.eassy_buy.Entity.user.User;
import com.vnk.eassy_buy.dto.cart.AddCartItemRequest;
import com.vnk.eassy_buy.dto.cart.CartItemResponse;
import com.vnk.eassy_buy.dto.cart.CartMergeRequest;
import com.vnk.eassy_buy.dto.cart.CartResponse;
import com.vnk.eassy_buy.dto.cart.UpdateCartItemRequest;
import com.vnk.eassy_buy.repository.UserRepository;
import com.vnk.eassy_buy.repository.cart.CartItemRepository;
import com.vnk.eassy_buy.repository.cart.CartRepository;
import com.vnk.eassy_buy.repository.product.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {
	
	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	private final ProductRepository productRepository;
	private final UserRepository userRepository;

	@Override
	public CartResponse getCart(Integer userId) {
		Cart cart = getOrCreateCart(userId);
		return toResponse(cart);
	}
	
	@Override
	@Transactional
	public CartResponse addItem(Integer userId, AddCartItemRequest request) {

		Cart cart = getOrCreateCart(userId);

		Product product = productRepository.findById(request.productId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

		validateProduct(product, request.quantity());

		CartItem existing = cart.getCartItem().stream()
				.filter(item -> item.getProduct().getProductId().equals(product.getProductId())).findFirst()
				.orElse(null);

		if (existing != null) {
			int newQuantity = existing.getQuantity() + request.quantity();

			validateProduct(product, newQuantity);
			existing.setQuantity(newQuantity);
		} else {
			CartItem item = new CartItem();
			item.setCart(cart);
			item.setProduct(product);
			item.setQuantity(request.quantity());
			item.setUnitPrice(product.getProductPrice());

			cart.getCartItem().add(item);
		}

		cartRepository.save(cart);

		return toResponse(cart);
	}
	
	@Override
	@Transactional
	public CartResponse updateItem(Integer userId, Long itemId, UpdateCartItemRequest request) {

		Cart cart = getOrCreateCart(userId);

		CartItem item = cartItemRepository.findByIdAndCart_Id(itemId, cart.getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart item not found"));

		validateProduct(item.getProduct(), request.quantity());

		item.setQuantity(request.quantity());

		return toResponse(cart);
	}
	
	@Override
	@Transactional
	public CartResponse removeItem(Integer userId, Long itemId) {
		Cart cart = getOrCreateCart(userId);

		CartItem item = cartItemRepository.findByIdAndCart_Id(itemId, cart.getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart item not found"));

		cart.getCartItem().remove(item);
		cartItemRepository.delete(item);

		return toResponse(cart);
	}
	
	@Override
	@Transactional
	public void clearCart(Integer userId) {
		Cart cart = getOrCreateCart(userId);
		cart.getCartItem().clear();
		cartRepository.save(cart);
	}
	
	@Override
	@Transactional
	public CartResponse mergeGuestCart(Integer userId, CartMergeRequest request) {

		Cart cart = getOrCreateCart(userId);

		for (CartMergeRequest.GuestCartItem guestItem : request.items()) {

			Product product = productRepository.findById(guestItem.productId())
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
							"Product not found: " + guestItem.productId()));

			CartItem existing = cart.getCartItem().stream()
					.filter(item -> item.getProduct().getProductId().equals(product.getProductId())).findFirst()
					.orElse(null);

			int mergedQuantity = guestItem.quantity();

			if (existing != null) {
				mergedQuantity += existing.getQuantity();
			}

			validateProduct(product, mergedQuantity);

			if (existing != null) {
				existing.setQuantity(mergedQuantity);
			} else {
				CartItem item = new CartItem();
				item.setCart(cart);
				item.setProduct(product);
				item.setQuantity(mergedQuantity);
				item.setUnitPrice(product.getProductPrice());
				cart.getCartItem().add(item);
			}
		}

		cartRepository.save(cart);

		return toResponse(cart);
	}

	private Cart getOrCreateCart(Integer userId) {
		return cartRepository.findByUser_Id(userId).orElseGet(() -> {
			User user = userRepository.findById(userId)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

			Cart cart = new Cart();
			cart.setUser(user);

			return cartRepository.save(cart);
		});
	}

	private void validateProduct(Product product, int quantity) {
		if (!Boolean.TRUE.equals(product.getActive())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product is inactive");
		}

		if (product.getStockQuantity() == null || product.getStockQuantity() < quantity) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Insufficient product stock");
		}

		if (product.getProductPrice() == null || product.getProductPrice().signum() < 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid product price");
		}
	}

	private CartResponse toResponse(Cart cart) {
		List<CartItemResponse> items = cart.getCartItem().stream()
				.map(item -> new CartItemResponse(item.getId(), item.getProduct().getProductId(),
						item.getProduct().getProductName(), item.getUnitPrice(), item.getQuantity(),
						item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()))))
				.toList();

		int totalItems = items.stream().mapToInt(CartItemResponse::quantity).sum();

		BigDecimal totalAmount = items.stream().map(CartItemResponse::subtotal).reduce(BigDecimal.ZERO,
				BigDecimal::add);

		return new CartResponse(cart.getId(), items, totalItems, totalAmount);
	}
}
