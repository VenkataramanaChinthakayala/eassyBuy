package com.vnk.eassy_buy.dto.seller;

import com.vnk.eassy_buy.constants.SellerStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerResponseDto {

	private Integer id;
	private String bannerUrl;
	private String businessEmail;
	private String businessName;
	private Long businessPhone;
	private String gstNumber;
	private String logoUrl;
	private String panNumber;
	private String shopDescription;
	private String shopName;
	private SellerStatus status;
	private Boolean verified;
	private Integer totalOrders;
	private Integer totalProducts;
	private Double rating;
}