package com.ute.sportswearbe.services.transaction;

import com.ute.sportswearbe.entities.Transaction;

import java.security.Principal;

public interface TransactionService {
    Transaction getTransactionByID(String id);
    Transaction getTransactionByOrderID(String orderID);
    Transaction createTransaction(Transaction transaction);
    Transaction createTransaction(String amount, String bankCode, String bankTranNo, String cardType,
                      String orderInfo, String payDate, String responseCode, String tmnCode,
                      String transactionNo, String txnRef, String secureHashType,
                      String secureHash);
    Transaction updateTransaction(Transaction transaction);
    Transaction changeStatusTransaction(Transaction transaction);
    Transaction save(Transaction transaction);
}
