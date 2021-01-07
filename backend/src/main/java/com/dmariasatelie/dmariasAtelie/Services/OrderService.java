package com.dmariasatelie.dmariasAtelie.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmariasatelie.dmariasAtelie.dto.OrderDTO;
import com.dmariasatelie.dmariasAtelie.entities.Order;
import com.dmariasatelie.dmariasAtelie.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		List<Order> list = repository.findOrdersWithProduts();
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
}
