package com.vnk.eassy_buy.dto.super_admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAdminRequest {
	private Integer id;
	private String username;
	private Long mobile;
}
