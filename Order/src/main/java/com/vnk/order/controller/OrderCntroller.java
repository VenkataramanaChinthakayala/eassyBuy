package com.vnk.order.controller;

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

import com.vnk.order.entity.OrderEntity;
import com.vnk.order.service.OrderService;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderCntroller {
	private final OrderService orderService;
	
	@PostMapping
	public ResponseEntity<String> createOrder(@RequestBody OrderEntity orderEntity) throws Exception {
		return new ResponseEntity<String>(orderService.addOrder(orderEntity),HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<String> updateOrder(@RequestBody OrderEntity orderEntity) throws Exception{
		return new ResponseEntity<String>(orderService.updateOrder(orderEntity),HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<OrderEntity> getOrder(@PathVariable Long id) throws Exception{
		return new ResponseEntity<OrderEntity>(orderService.getOrder(id),HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<OrderEntity>> getAllOrders(){
		return new ResponseEntity<List<OrderEntity>>(orderService.getAllOrders(),HttpStatus.OK);
	}
	
}
