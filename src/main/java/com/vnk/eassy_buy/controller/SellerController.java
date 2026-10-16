package com.vnk.eassy_buy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vnk.eassy_buy.dto.seller.SellerRequestDto;
import com.vnk.eassy_buy.dto.seller.SellerResponseDto;
import com.vnk.eassy_buy.service.seller.SellerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("seller")
@RequiredArgsConstructor
public class SellerController {
	private final SellerService sellerService;

	@PostMapping
	public ResponseEntity<String> sellerRequest(@RequestBody SellerRequestDto sellerRequestDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(sellerService.sellerRequest(sellerRequestDto));
	}

	@PutMapping
	public ResponseEntity<String> updateSellerRequest(@RequestBody SellerRequestDto sellerRequestDto) {
		return ResponseEntity.ok(sellerService.sellerUpdate(sellerRequestDto));
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteSellerRequest() {
		sellerService.sellerDelete();
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<SellerResponseDto> getSeller() {
		return ResponseEntity.ok(sellerService.getSeller());
	}
}
