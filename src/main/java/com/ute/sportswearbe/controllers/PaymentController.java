package com.ute.sportswearbe.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/payments")
public class PaymentController {
    @GetMapping
    public ResponseEntity<?> getAllPayments(){
        return  new ResponseEntity<String>("payments", HttpStatus.OK);
    }
}
