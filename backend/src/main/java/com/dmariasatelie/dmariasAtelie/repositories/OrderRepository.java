package com.dmariasatelie.dmariasAtelie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmariasatelie.dmariasAtelie.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
