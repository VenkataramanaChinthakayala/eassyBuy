package com.vnk.eassy_buy.Entity.seller;

import com.vnk.eassy_buy.Entity.user.User;
import com.vnk.eassy_buy.base_entity.BaseEntity;
import com.vnk.eassy_buy.constants.SellerStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Seller extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	// Business information
	@Column(nullable = false)
	private String shopName;
	private String shopDescription;
	private String businessName;
	// Legal / verification information
	private String gstNumber;
	private String panNumber;
	@Enumerated(EnumType.STRING)
	private SellerStatus status;
	private Boolean verified;
	// Store information
	private String logoUrl;
	private String bannerUrl;
	// Contact information
	private String businessEmail;
	private Long businessPhone;
	// Seller statistics
	private Double rating;
	private Integer totalProducts;
	private Integer totalOrders;
	// Relationship
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false, unique = true)
	private User user;
}
