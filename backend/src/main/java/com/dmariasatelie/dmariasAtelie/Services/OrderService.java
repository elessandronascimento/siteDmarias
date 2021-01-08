package com.dmariasatelie.dmariasAtelie.Services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmariasatelie.dmariasAtelie.dto.OrderDTO;
import com.dmariasatelie.dmariasAtelie.dto.ProductDTO;
import com.dmariasatelie.dmariasAtelie.entities.Order;
import com.dmariasatelie.dmariasAtelie.entities.OrderStatus;
import com.dmariasatelie.dmariasAtelie.entities.Product;
import com.dmariasatelie.dmariasAtelie.repositories.OrderRepository;
import com.dmariasatelie.dmariasAtelie.repositories.ProductRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		List<Order> list = repository.findOrdersWithProduts();
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public OrderDTO insert(OrderDTO dto){
		Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(), Instant.now(), OrderStatus.PENDING);
	
		for (ProductDTO p : dto.getProducts()) {
			Product  product = productRepository.getOne(p.getId());
			order.getProduts().add(product);
		}
		order = repository.save(order);
		return new OrderDTO(order);
	}
	
}
