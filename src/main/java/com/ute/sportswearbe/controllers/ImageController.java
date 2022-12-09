package com.ute.sportswearbe.controllers;

import com.ute.sportswearbe.services.file.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.annotation.AccessType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/image")
public class ImageController {

    @Autowired
    private FilesStorageService storageService;
    @GetMapping("/{type}/{filename}")
    public ResponseEntity<?> getImage(
            @PathVariable("type") String type, @PathVariable("filename") String filenam){
        Resource resource = getImageFromLocal(type, filenam);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
    private Resource getImageFromLocal(String type, String filename){
        if (type.equals("avatar")){
            return getAvatar(filename);
        }
        if (type.equals("products")){
            return getProduct(filename);
        }
        return null;
    }
    private Resource getAvatar(String filename){
        String filePath = "avatar/" + filename;
        Resource resource = storageService.loadFile(filePath);
        return resource;
    }
    private Resource getProduct(String filename){
        String filePath = "products/" + filename;
        Resource resource = storageService.loadFile(filePath);
        return resource;
    }
}
