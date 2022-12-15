package com.ute.sportswearbe.repositories;

import com.ute.sportswearbe.entities.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TransactionRepository  extends MongoRepository<Transaction, String> {
//    List<Transaction> findAll();
//
//
//    Page<Transaction> getTransactionPaging(String search, Pageable pageable);

    @Query(value = "{'order.id': ?0}")
    Transaction getTransactionByOrderId(String orderID);

    @Query(value = "{'id': ?0}")
    Transaction getTransactionById(String orderID);

    Transaction findTransactionByOrder_Id(String orderID);

}
