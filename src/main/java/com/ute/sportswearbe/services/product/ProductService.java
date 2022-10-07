package com.ute.sportswearbe.services.product;

import com.ute.sportswearbe.dtos.ProductDto;
import com.ute.sportswearbe.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    Product getProductById(String id);

    Product getProductByTitle(String title);

    List<Product> getAllProduct();

    Page<Product> getProductPaging(String search, int page, int size, String sort, String column);

    List<Product> getAllProductFromCategory(String name);

    Product createNewProduct(ProductDto dto, MultipartFile[] files);

    Product updateProduct(String id, ProductDto dto, MultipartFile[] files);

    Product changeStatusProduct(String id);

    Product addCategoryToProduct(String ProductId, String cateId);

    Product removeCategoryFromProduct(String ProductId, String cateId);

    List<Product> searchProduct(String search);

    Product addImagesProduct(String id, MultipartFile[] files);

    Product deleteImagesProduct(String id, List<String> images);

    Product save(Product product);
}
