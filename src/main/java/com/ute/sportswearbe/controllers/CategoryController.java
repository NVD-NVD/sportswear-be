package com.ute.sportswearbe.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by: IntelliJ IDE
 * User: NVD-NVD
 * Date: 9/26/2022
 * Time: 6:36 PM
 * Filename: CategoryController
 */
@RequestMapping("/rest/categories")
public class CategoryController {

    @GetMapping
    public ResponseEntity<?> getAllCategories(){
        return  new ResponseEntity<String>("categories", HttpStatus.OK);
    }
}
