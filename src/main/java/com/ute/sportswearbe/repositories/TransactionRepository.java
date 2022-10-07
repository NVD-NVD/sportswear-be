package com.ute.sportswearbe.repositories;

import com.ute.sportswearbe.entities.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository  extends MongoRepository<Transaction, String> {
//    List<Transaction> findAll();
//
//
//    Page<Transaction> getTransactionPaging(String search, Pageable pageable);

}
