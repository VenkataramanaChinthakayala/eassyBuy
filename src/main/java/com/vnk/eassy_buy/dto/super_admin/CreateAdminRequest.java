package com.vnk.eassy_buy.dto.super_admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAdminRequest {
	private String username;
	private String mail;
	private String password;
	private Long mobile;
}
