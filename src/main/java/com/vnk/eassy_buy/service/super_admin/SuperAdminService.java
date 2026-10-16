package com.vnk.eassy_buy.service.super_admin;

import java.util.List;

import com.vnk.eassy_buy.dto.super_admin.AdminResponse;
import com.vnk.eassy_buy.dto.super_admin.CreateAdminRequest;
import com.vnk.eassy_buy.dto.super_admin.UpdateAdminRequest;

public interface SuperAdminService {
	
	String createAdmin(CreateAdminRequest createAdminRequest);

	String updateAdmin(UpdateAdminRequest updateAdminRequest);

	void deleteAdmin(Integer adminId);

	List<AdminResponse> getAllAdminDetails();

}
