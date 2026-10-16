package com.vnk.order.service;

import java.util.List;

import com.vnk.order.entity.OrderEntity;

public interface OrderService {
	
	String addOrder(OrderEntity orderEntity) throws Exception;

	String updateOrder(OrderEntity orderEntity) throws Exception;

	OrderEntity getOrder(Long id) throws Exception;

	List<OrderEntity> getAllOrders();

	void deleteOrder(Long id);
}
