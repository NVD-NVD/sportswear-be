package com.ute.sportswearbe.services.product;

import com.ute.sportswearbe.dtos.ProductDto;
import com.ute.sportswearbe.entities.FilesDeleted;
import com.ute.sportswearbe.entities.Product;
import com.ute.sportswearbe.exceptions.NotFoundException;
import com.ute.sportswearbe.repositories.ProductRepository;
import com.ute.sportswearbe.services.cloudinary.CloudinaryService;
import com.ute.sportswearbe.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    private CloudinaryService cloudinaryService;

    public ProductServiceImpl(ProductRepository productRepository, CloudinaryService cloudinaryService) {
        this.productRepository = productRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Sản phẩm có id %s không tồn tại", id)));
    }

    @Override
    public Product getProductByTitle(String title) {
        return productRepository.getProductByTitle(title)
                .orElseThrow(() -> new NotFoundException(String.format("Không tìm thấy sản phẩm có title là %s", title)));
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> getProductPaging(String search, int page, int size, String sort, String column) {
        Pageable pageable = PageUtils.createPageable(page, size, sort, column);
        return productRepository.getProductPaging(search, pageable);
    }

    @Override
    public List<Product> getAllProductFromCategory(String name) {
        return null;
    }

    @Override
    public Product createNewProduct(ProductDto dto, MultipartFile[] files) {
        return null;
    }

    @Override
    public Product updateProduct(String id, ProductDto dto, MultipartFile[] files) {
        return null;
    }

    @Override
    public Product changeStatusProduct(String id) {
        return null;
    }

    @Override
    public Product addCategoryToProduct(String ProductId, String cateId) {
        return null;
    }

    @Override
    public Product removeCategoryFromProduct(String ProductId, String cateId) {
        return null;
    }

    @Override
    public List<Product> searchProduct(String search) {
        return null;
    }

    @Override
    public Product addImagesProduct(String id, MultipartFile[] files) {
        return null;
    }

    @Override
    public Product deleteImagesProduct(String id, List<String> images) {
        Product product = getProductById(id);

        FilesDeleted filesDeleted = new FilesDeleted();
        filesDeleted.setUId(id);
        filesDeleted.setTitle("product");
        filesDeleted.setLinks(images);

        List<String> pImages = product.getImages();
        for (int i = 0; i < images.size(); i++){
            String image = images.get(i);
            pImages.removeIf(img -> img.equals(image));
        }
        product.setImages(pImages);
        return product;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    private Product convertDtoToProduct(ProductDto dto) {
        return null;
    }
}
