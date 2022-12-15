package com.ute.sportswearbe.controllers;

import com.ute.sportswearbe.services.transaction.TransactionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @ApiOperation(value = "")
    @GetMapping("/{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable String id){
        return new ResponseEntity<>(transactionService.getTransactionByID(id),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllTransactions(){
        return  new ResponseEntity<String>("Transactions", HttpStatus.OK);
    }

}
