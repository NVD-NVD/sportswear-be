package com.ute.sportswearbe.controllers;

import com.ute.sportswearbe.dtos.ProductDto;
import com.ute.sportswearbe.services.cloudinary.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//@RestController
@RequestMapping("/rest/test")
public class TestController {
    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {

        String url = cloudinaryService.uploadFile(file);
        return "File uploaded successfully: File path :  " + url;
    }
}
