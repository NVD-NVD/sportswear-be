package com.ute.sportswearbe.repositories;

import com.ute.sportswearbe.entities.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PaymentRepository extends MongoRepository<Payment, String> {
    @Query(value = "{'gateway': {$regex : ?0, $options: 'i'}}")
    Page<Payment> getPaymentPaging(String search, Pageable pageable);
}
