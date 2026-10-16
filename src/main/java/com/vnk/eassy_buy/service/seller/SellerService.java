package com.vnk.eassy_buy.service.seller;

import com.vnk.eassy_buy.dto.seller.SellerRequestDto;
import com.vnk.eassy_buy.dto.seller.SellerResponseDto;

public interface SellerService {
    String sellerRequest(SellerRequestDto sellerRequestDto);
    String sellerUpdate(SellerRequestDto sellerRequestDto);
    void sellerDelete();
	SellerResponseDto getSeller();
}
