package com.vnk.eassy_buy.service.super_admin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vnk.eassy_buy.Entity.user.User;
import com.vnk.eassy_buy.constants.LoginProvider;
import com.vnk.eassy_buy.constants.ResponseMessages;
import com.vnk.eassy_buy.constants.UserRoles;
import com.vnk.eassy_buy.dto.super_admin.AdminResponse;
import com.vnk.eassy_buy.dto.super_admin.CreateAdminRequest;
import com.vnk.eassy_buy.dto.super_admin.UpdateAdminRequest;
import com.vnk.eassy_buy.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SuperAdminServiceImpl implements SuperAdminService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public String createAdmin(CreateAdminRequest createAdminRequest) {
		User user = userRepository.findByMail(createAdminRequest.getMail()).orElse(null);
		if (user != null) {
			if (user.getRole().contains(UserRoles.ADMIN))
				throw new RuntimeException(ResponseMessages.USER_ALREADY_ADMIN.getMessage());
			user.getRole().add(UserRoles.ADMIN);
			userRepository.save(user);

			return ResponseMessages.ADMIN_CREATED.getMessage();
		}

		User admin = User.builder().mail(createAdminRequest.getMail()).mobile(createAdminRequest.getMobile())
				.username(createAdminRequest.getUsername())
				.password(passwordEncoder.encode(createAdminRequest.getPassword()))
				.role(new HashSet<>(Set.of(UserRoles.ADMIN))).provider(List.of(LoginProvider.LOCAL)).build();

		userRepository.save(admin);
		return ResponseMessages.ADMIN_CREATED.getMessage();
	}

	@Override
	public String updateAdmin(UpdateAdminRequest updateAdminRequest) {
		User user = userRepository.findById(updateAdminRequest.getId()).orElseThrow(() -> new RuntimeException(ResponseMessages.ADMIN_NOT_FOUND.getMessage()));
		if (user.getRole() != null && user.getRole().contains(UserRoles.ADMIN)) {
			user.setMobile(updateAdminRequest.getMobile());
			user.setUsername(updateAdminRequest.getUsername());
			userRepository.save(user);
			return ResponseMessages.ADMIN_UPDATED.getMessage();
		}
		throw new RuntimeException(ResponseMessages.USER_NOT_ADMIN.getMessage());
	}

	@Override
	public void deleteAdmin(Integer adminId) {
		User user = userRepository.findById(adminId).orElseThrow(() -> new RuntimeException("Admin not found"));

		if (user.getRole() == null || !user.getRole().contains(UserRoles.ADMIN))
			throw new RuntimeException(ResponseMessages.USER_NOT_ADMIN.getMessage());

		if (user.getRole().size() == 1) {
			userRepository.delete(user);
			log.info("Admin {} deleted completely because ADMIN was the only role", adminId);
			return;
		}

		user.getRole().remove(UserRoles.ADMIN);
		userRepository.save(user);
		log.info("ADMIN role removed from user {}", adminId);
	}

	@Override
	public List<AdminResponse> getAllAdminDetails() {
		List<User> admins = userRepository.findByRole(UserRoles.ADMIN);
		return mapToResponse(admins);
	}

	private List<AdminResponse> mapToResponse(List<User> users) {
		return users.stream().map(user -> AdminResponse.builder().id(user.getId()).mail(user.getMail())
				.mobile(user.getMobile()).username(user.getUsername()).role(user.getRole()).build()).toList();
	}
}
