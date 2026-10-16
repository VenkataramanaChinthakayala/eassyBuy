package com.vnk.order.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vnk.order.entity.OrderEntity;
import com.vnk.order.globalexception.OrderException;
import com.vnk.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	@Override
	public String addOrder(OrderEntity orderEntity) throws Exception {
		try {
			orderRepository.save(orderEntity);
			return "Order SuccessFully created";
		} catch (Exception exception) {
			throw new OrderException("Unable to create the Order due to :" + exception.getMessage(), exception);
		}

	}

	@Override
	public String updateOrder(OrderEntity orderEntity) throws Exception {
		if (orderRepository.existsById(orderEntity.getId())) {
			orderRepository.save(orderEntity);
			return "Order SuccessFully updated";
		} else {
			throw new OrderException("Unable to update the Order");
		}
	}

	@Override
	public OrderEntity getOrder(Long id) throws Exception {
		if (orderRepository.existsById(id))
			return orderRepository.findById(id).get();
		else
			throw new OrderException("Order not found this id :" + id);
	}

	@Override
	public List<OrderEntity> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public void deleteOrder(Long id) {
		orderRepository.deleteById(id);
	}
}
