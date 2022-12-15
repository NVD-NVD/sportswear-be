package com.ute.sportswearbe.services.transaction;

import com.ute.sportswearbe.entities.Transaction;
import com.ute.sportswearbe.repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction getTransactionByID(String id) {
        return transactionRepository.getTransactionById(id);
    }

    @Override
    public Transaction getTransactionByOrderID(String orderID) {
        return transactionRepository.getTransactionByOrderId(orderID);
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public Transaction createTransaction(String amount, String bankCode, String bankTranNo, String cardType, String orderInfo, String payDate, String responseCode, String tmnCode, String transactionNo, String txnRef, String secureHashType, String secureHash) {
        return null;
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public Transaction changeStatusTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
