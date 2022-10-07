package com.ute.sportswearbe.repositories;

import com.ute.sportswearbe.entities.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
