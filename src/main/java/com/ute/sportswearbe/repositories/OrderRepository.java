package com.ute.sportswearbe.repositories;

import com.ute.sportswearbe.entities.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by: IntelliJ IDE
 * User: NVD-NVD
 * Date: 9/3/2022
 * Time: 9:35 PM
 * Filename: OrderRepository
 */
public interface OrderRepository extends MongoRepository<Order, String> {
}
