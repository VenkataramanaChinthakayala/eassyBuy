package com.vnk.eassy_buy.dto.seller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerRequestDto {

	private String shopName;
	private String shopDescription;
	private String businessName;
	private String gstNumber;
	private String panNumber;
	private String logoUrl;
	private String bannerUrl;
	private String businessEmail;
	private Long businessPhone;
}
