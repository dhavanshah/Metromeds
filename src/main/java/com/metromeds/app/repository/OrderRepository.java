package com.metromeds.app.repository;

import com.metromeds.app.models.order.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}

