package com.ute.sportswearbe.controllers;

import org.springframework.data.annotation.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by: IntelliJ IDE
 * User: NVD-NVD
 * Date: 9/26/2022
 * Time: 6:36 PM
 * Filename: OrderController
 */
@RequestMapping("/rest/orders")
public class OrderController {
    @GetMapping
    public ResponseEntity<?> getAllOrders(){
        return  new ResponseEntity<String>("orders", HttpStatus.OK);
    }
}
