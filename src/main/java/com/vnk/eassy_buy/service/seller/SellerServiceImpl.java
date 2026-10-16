package com.vnk.eassy_buy.service.seller;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vnk.eassy_buy.Entity.seller.Seller;
import com.vnk.eassy_buy.Entity.user.User;
import com.vnk.eassy_buy.config.util.SecurityUtil;
import com.vnk.eassy_buy.constants.ResponseMessages;
import com.vnk.eassy_buy.constants.SellerStatus;
import com.vnk.eassy_buy.dto.seller.SellerRequestDto;
import com.vnk.eassy_buy.dto.seller.SellerResponseDto;
import com.vnk.eassy_buy.repository.UserRepository;
import com.vnk.eassy_buy.repository.seller.SellerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SellerServiceImpl implements SellerService {
	private final SellerRepository sellerRepository;
	private final UserRepository userRepository;

	@Transactional
	@Override
	public String sellerRequest(SellerRequestDto sellerRequestDto) {
		String userName = SecurityUtil.userName();
		log.info("Seller request initiated by user: {}", userName);
		User user = userRepository.findByMail(userName).orElseThrow(() -> {
			log.warn("User not found while submitting seller request: {}", userName);
			return new RuntimeException(ResponseMessages.INVALID_MAIL.getMessage());
		});
		if (user.getSeller() != null) {
			Seller existingSeller = user.getSeller();
			log.info("Existing seller profile found for user: {} with status: {}", userName,
					existingSeller.getStatus());
			if (SellerStatus.ACTIVE == existingSeller.getStatus()) {
				log.warn("User {} is already an active seller", userName);
				throw new RuntimeException(ResponseMessages.SELLER_ALREADY_ACTIVE.getMessage());
			}
			if (SellerStatus.PENDING == existingSeller.getStatus()) {
				log.warn("Seller request already pending for user: {}", userName);
				throw new RuntimeException(ResponseMessages.SELLER_REQUEST_ALREADY_PENDING.getMessage());
			}
		}

		Seller seller = toSeller(sellerRequestDto, user);
		seller.setStatus(SellerStatus.PENDING);
		seller.setVerified(false);
		seller.setTotalOrders(0);
		seller.setTotalProducts(0);
		seller.setRating(0.0);
		sellerRepository.save(seller);
		return ResponseMessages.SELLER_REQUEST_SUBMITTED.getMessage();
	}

	private Seller toSeller(SellerRequestDto sellerRequestDto, User user) {
		return Seller.builder().bannerUrl(sellerRequestDto.getBannerUrl())
				.businessEmail(sellerRequestDto.getBusinessEmail()).businessName(sellerRequestDto.getBusinessName())
				.businessPhone(sellerRequestDto.getBusinessPhone()).gstNumber(sellerRequestDto.getGstNumber())
				.logoUrl(sellerRequestDto.getLogoUrl()).panNumber(sellerRequestDto.getPanNumber())
				.shopDescription(sellerRequestDto.getShopDescription()).shopName(sellerRequestDto.getShopName())
				.user(user).build();
	}

	@Transactional
	@Override
	public String sellerUpdate(SellerRequestDto sellerRequestDto) {
		String userName = SecurityUtil.userName();
		log.info("Seller request initiated by user: {}", userName);
		User user = userRepository.findByMail(userName).orElseThrow(() -> {
			log.warn("User not found while submitting seller request: {}", userName);
			return new RuntimeException(ResponseMessages.INVALID_MAIL.getMessage());
		});
		Seller existingSeller = user.getSeller();
		if (existingSeller == null) {
			throw new RuntimeException(ResponseMessages.SELLER_NOT_FOUND.getMessage());
		}

		if (!existingSeller.getStatus().equals(SellerStatus.ACTIVE)) {
			log.warn("Seller is not active. User: {}, Status: {}", userName, existingSeller.getStatus());
			throw new RuntimeException(ResponseMessages.SELLER_NOT_FOUND.getMessage());
		}
		existingSeller.setBannerUrl(sellerRequestDto.getBannerUrl());
		existingSeller.setBusinessEmail(sellerRequestDto.getBusinessEmail());
		existingSeller.setBusinessName(sellerRequestDto.getBusinessName());
		existingSeller.setBusinessPhone(sellerRequestDto.getBusinessPhone());
		existingSeller.setGstNumber(sellerRequestDto.getGstNumber());
		existingSeller.setLogoUrl(sellerRequestDto.getLogoUrl());
		existingSeller.setPanNumber(sellerRequestDto.getPanNumber());
		existingSeller.setShopDescription(sellerRequestDto.getShopDescription());
		existingSeller.setShopName(sellerRequestDto.getShopName());
		sellerRepository.save(existingSeller);
		log.info("Seller updated successfully for user: {}", userName);
		return ResponseMessages.SELLER_UPDATED_SUCCESSFULLY.getMessage();
	}

	@Override
	@Transactional
	public void sellerDelete() {
		String userName = SecurityUtil.userName();
		log.info("Seller delete initiated by user: {}", userName);
		User user = userRepository.findByMail(userName).orElseThrow(() -> {
			log.warn("User not found while deleting seller: {}", userName);
			return new RuntimeException(ResponseMessages.INVALID_MAIL.getMessage());
		});
		Seller seller = user.getSeller();
		if (seller == null) {
			log.warn("Seller not found for user: {}", userName);
			throw new RuntimeException(ResponseMessages.SELLER_NOT_FOUND.getMessage());
		}
		sellerRepository.delete(seller);
		log.info("Seller deleted successfully for user: {}", userName);
	}

	@Override
	@Transactional(readOnly = true)
	public SellerResponseDto getSeller() {
		String userName = SecurityUtil.userName();
		log.info("Fetching seller details for user: {}", userName);
		User user = userRepository.findByMail(userName).orElseThrow(() -> {
			log.warn("User not found while fetching seller: {}", userName);
			return new RuntimeException(ResponseMessages.INVALID_MAIL.getMessage());
		});
		Seller seller = user.getSeller();
		if (seller == null) {
			log.warn("Seller not found for user: {}", userName);
			throw new RuntimeException(ResponseMessages.SELLER_NOT_FOUND.getMessage());
		}

		return toSellerResponse(seller);
	}

	private SellerResponseDto toSellerResponse(Seller seller) {

		return SellerResponseDto.builder().id(seller.getId()).bannerUrl(seller.getBannerUrl())
				.businessEmail(seller.getBusinessEmail()).businessName(seller.getBusinessName())
				.businessPhone(seller.getBusinessPhone()).gstNumber(seller.getGstNumber()).logoUrl(seller.getLogoUrl())
				.panNumber(seller.getPanNumber()).shopDescription(seller.getShopDescription())
				.shopName(seller.getShopName()).status(seller.getStatus()).verified(seller.getVerified())
				.totalOrders(seller.getTotalOrders()).totalProducts(seller.getTotalProducts())
				.rating(seller.getRating()).build();
	}
}
