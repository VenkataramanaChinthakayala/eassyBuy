package com.vnk.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vnk.order.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
