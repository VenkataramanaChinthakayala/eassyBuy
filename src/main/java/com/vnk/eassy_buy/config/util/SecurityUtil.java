package com.vnk.eassy_buy.config.util;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.server.ResponseStatusException;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SecurityUtil {

	private static Jwt getJwt() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()
				|| !(authentication.getPrincipal() instanceof Jwt jwt)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Valid JWT authentication required");
		}
		return jwt ;
	}

	public static String userName() {
		return getJwt().getSubject();
	}

	public static String getRole() {
		return getJwt().getClaimAsString("role");
	}

}