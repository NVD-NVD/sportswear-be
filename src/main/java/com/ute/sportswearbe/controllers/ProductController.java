package com.ute.sportswearbe.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by: IntelliJ IDE
 * User: NVD-NVD
 * Date: 9/26/2022
 * Time: 6:35 PM
 * Filename: ProductController
 */
@RequestMapping("/rest/products")
public class ProductController {
    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        return  new ResponseEntity<String>("products", HttpStatus.OK);
    }
}
