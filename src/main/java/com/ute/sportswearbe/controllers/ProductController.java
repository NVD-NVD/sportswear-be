package com.ute.sportswearbe.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ute.sportswearbe.dtos.ProductDto;
import com.ute.sportswearbe.entities.Product;
import com.ute.sportswearbe.services.file.FilesStorageService;
import com.ute.sportswearbe.services.product.ProductService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/rest/products")
public class ProductController {

    @Autowired
    private ProductService productService;

//    @Autowired
//    private FilesStorageService storageService;

    @ApiOperation(value = "Get tất cả sản phẩm, không phân trang")
    @GetMapping
    public ResponseEntity<List<?>> getAllProducts(){
        return  new ResponseEntity<>(productService.getAllProduct() , HttpStatus.OK);
    }

    @ApiOperation(value = "Get danh sach sản phẩm có phân trang")
    @GetMapping("/paging")
    public ResponseEntity<Page<?>> getProductPaging(
            @RequestParam(name = "search", required = false, defaultValue = "") String search,
            @RequestParam(name = "page", required = false, defaultValue = "${paging.default.page}") int page,
            @RequestParam(name = "size", required = false, defaultValue = "${paging.default.size}") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "asc") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "id") String column){
        return new ResponseEntity<>(productService.getProductPaging(search, page, size, sort, column), HttpStatus.OK);
    }
    @ApiOperation(value = "Get sản phẩm bằng id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductByID(@PathVariable String id){
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Admin tạo mới 1 Product.(Không gữi kèm hình ảnh)", notes = "Admin")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/noImages")
    public ResponseEntity<Product> createNewProductNoImage(
            @RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.createNewProduct(productDto, null), HttpStatus.OK);
    }

    @ApiOperation(value = "Admin tạo mới 1 Product.(Có gữi kèm hình ảnh)", notes = "Admin\n Lưu ý: " +
            "productDto là kiểu String, có cấu trúc JSON theo ProductDto(Giống với API /rest/products/noImages)" )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/hasImages" , consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Product> createNewProductHasImages(
            @RequestParam(name = "productDto", required = true) String productDto,
            @RequestParam MultipartFile[] files) {
        ProductDto dto = null;
        System.out.println(productDto);
        try {
            dto = new ObjectMapper().readValue(productDto, ProductDto.class);
        }catch (Exception e){
            System.out.println("ObjectMapper error");
            e.printStackTrace();
        }
        return new ResponseEntity<>(productService.createNewProduct(dto, files), HttpStatus.OK);
    }

    @ApiOperation(value = "Admin thay đổi trạng thái kích hoạt của Product", notes = "Admin")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> changStatusProduct(@PathVariable String id){
        return new ResponseEntity<>(productService.changeStatusProduct(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Admin cập nhập thông tin Product", notes = "Admin")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable String id, @RequestBody Product dto,
            @RequestParam(name = "files") MultipartFile[] files){
        return new ResponseEntity<>(productService.updateProduct(id, dto, files), HttpStatus.OK);
    }

    @ApiOperation(value = "Admin thêm hình ảnh cho Product", notes = "Admin")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/images/{id}")
    public ResponseEntity<Product> addImagesProduct(
            @PathVariable String id, @RequestParam(name = "files") MultipartFile[] files){
        return new ResponseEntity<>(productService.addImagesProduct(id,  files), HttpStatus.OK);
    }

    @ApiOperation(value = "Admin xóa hình ảnh cho Product", notes = "Admin")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/images/{id}")
    public ResponseEntity<Product> deleteImagesProduct(
            @PathVariable(value = "id") String id, @RequestParam(value = "images") List<String> images){
        return new ResponseEntity<>(productService.deleteImagesProduct(id, images), HttpStatus.OK);
    }

}
