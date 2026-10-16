package com.vnk.eassy_buy.dto.super_admin;

import java.util.Set;

import com.vnk.eassy_buy.constants.UserRoles;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AdminResponse {
	private Integer id;
	private String username;
	private String mail;
	private Long mobile;
	private Set<UserRoles> role;
}
