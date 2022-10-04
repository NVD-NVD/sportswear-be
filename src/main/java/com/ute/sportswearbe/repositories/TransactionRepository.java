package com.ute.sportswearbe.repositories;

import com.ute.sportswearbe.entities.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by: IntelliJ IDE
 * User: NVD-NVD
 * Date: 9/3/2022
 * Time: 9:35 PM
 * Filename: TransactionRepository
 */
public interface TransactionRepository  extends MongoRepository<Transaction, String> {
//    List<Transaction> findAll();
//
//
//    Page<Transaction> getTransactionPaging(String search, Pageable pageable);

}
