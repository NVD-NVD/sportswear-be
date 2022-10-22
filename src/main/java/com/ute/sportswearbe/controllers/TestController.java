package com.ute.sportswearbe.controllers;

import com.ute.sportswearbe.dtos.ProductDto;
import com.ute.sportswearbe.entities.Category;
import com.ute.sportswearbe.services.category.CategoryService;
import com.ute.sportswearbe.services.cloudinary.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//@RestController
@RequestMapping("/rest/test")
public class TestController {
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {

        String url = cloudinaryService.uploadFile(file);
        return "File uploaded successfully: File path :  " + url;
    }
    @GetMapping("/categories")
    public ResponseEntity<?> getCategoryByTitle(@RequestParam(name = "title") String title){
        Category category = categoryService.getCategoryByTitle(title);
        if (category == null){
            System.out.println("khong tim thay category");
        }
        return new ResponseEntity(category, HttpStatus.OK);
    }
}
