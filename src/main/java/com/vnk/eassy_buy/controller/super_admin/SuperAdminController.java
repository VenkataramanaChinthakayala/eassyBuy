package com.vnk.eassy_buy.controller.super_admin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vnk.eassy_buy.dto.super_admin.AdminResponse;
import com.vnk.eassy_buy.dto.super_admin.CreateAdminRequest;
import com.vnk.eassy_buy.dto.super_admin.UpdateAdminRequest;
import com.vnk.eassy_buy.service.super_admin.SuperAdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/super/admin")
public class SuperAdminController {
	private final SuperAdminService adminService;
	
	@PostMapping
	public ResponseEntity<String> createAdmin(@RequestBody CreateAdminRequest createAdminRequest){
		return new ResponseEntity<String>(adminService.createAdmin(createAdminRequest),HttpStatus.CREATED);
	}
	@PutMapping
	public ResponseEntity<String> updateAdmin(@RequestBody UpdateAdminRequest updateAdminRequest){
		return new ResponseEntity<String>(adminService.updateAdmin(updateAdminRequest),HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteAdmin(@PathVariable Integer id){
		adminService.deleteAdmin(id);
		return ResponseEntity.noContent().build();
	}
	@GetMapping
	public ResponseEntity<List<AdminResponse>> getAllAdminDetailc(){
		return new ResponseEntity<List<AdminResponse>>(adminService.getAllAdminDetails(),HttpStatus.OK);
	}
}
