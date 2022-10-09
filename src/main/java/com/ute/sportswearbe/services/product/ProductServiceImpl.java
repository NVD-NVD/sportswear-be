package com.ute.sportswearbe.services.product;

import com.ute.sportswearbe.dtos.ProductDto;
import com.ute.sportswearbe.entities.Category;
import com.ute.sportswearbe.entities.FilesDeleted;
import com.ute.sportswearbe.entities.Product;
import com.ute.sportswearbe.entities.embedded.EmbeddedCategory;
import com.ute.sportswearbe.exceptions.InvalidException;
import com.ute.sportswearbe.exceptions.NotFoundException;
import com.ute.sportswearbe.repositories.ProductRepository;
import com.ute.sportswearbe.services.category.CategoryService;
import com.ute.sportswearbe.services.cloudinary.CloudinaryService;
import com.ute.sportswearbe.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    private CategoryService categoryService;

    private CloudinaryService cloudinaryService;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, CloudinaryService cloudinaryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
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
    public Product createNewProduct(ProductDto dto, MultipartFile[] images) {
        Product product = convertDtoToProduct(dto, images);
        return save(product);
    }

    @Override
    public Product updateProduct(String id, Product dto, MultipartFile[] images) {
        Product product = getProductById(id);
        if (ObjectUtils.isEmpty(dto.getTitle()))
            throw new InvalidException("Title không được null");
        if (ObjectUtils.isEmpty(dto.getOptions()))
            throw new InvalidException("Option không được null");
        if (ObjectUtils.isEmpty(dto.getQuantity()))
            throw new InvalidException("Quantity không được null");
        if (ObjectUtils.isEmpty(dto.getPrice()))
            throw new InvalidException("Prices không được null");
        if (ObjectUtils.isEmpty(images))
            throw new InvalidException("Phải có ít nhất một ảnh");

        product.setTitle(dto.getTitle());
        product.setDescriptions(dto.getDescriptions());

        product.setOptions(dto.getOptions());
        product.setTypes(dto.getTypes());
        product.setTrademark(dto.getTrademark());
        product.setProducer(dto.getProducer());
        product.setOrigin(dto.getOrigin());
        product.setPrice(dto.getPrice());
        product.setDiscount(dto.getDiscount());
        product.setQuantity(dto.getQuantity());
        product.setFallIntoCategories(dto.getFallIntoCategories());
        product.setReviews(null);
        List<String> list = dto.getImages();
        list.addAll(cloudinaryService.uploadListImages(images));
        product.setImages(cloudinaryService.uploadListImages(images));
        product.setCreatedOn(new Date());
        product.setUpdateOn(new Date());

        return null;
    }

    @Override
    public Product changeStatusProduct(String id) {
        Product product = getProductById(id);
        product.setEnable(!product.isEnable());
        return save(product);
    }

    @Override
    public Product addCategoryToProduct(String proId, List<String> cateId) {
        Product product = getProductById(proId);

        List<EmbeddedCategory> emCate = product.getFallIntoCategories();
        cateId.forEach( i -> {
            Category category = categoryService.getCategoryById(i);
            emCate.add(new EmbeddedCategory(category.getId(), category.getTitle()));
        });
        product.setFallIntoCategories(emCate);

        return save(product);
    }

    @Override
    public Product removeCategoryFromProduct(String proId, List<String> cateId) {
        return null;
    }

    @Override
    public Product addImagesProduct(String id, MultipartFile[] images) {
        Product product = getProductById(id);
        List<String> listImages = product.getImages();
        listImages.addAll(cloudinaryService.uploadListImages(images));
        return save(product);
    }

    @Override
    public Product deleteImagesProduct(String id, List<String> images) {
        Product product = getProductById(id);

        FilesDeleted filesDeleted = new FilesDeleted();
        filesDeleted.setUId(id);
        filesDeleted.setTitle("product");
        filesDeleted.setLinks(images);

        List<String> pImages = product.getImages();
        for (int i = 0; i < images.size(); i++) {
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

    private Product convertDtoToProduct(ProductDto dto, MultipartFile[] images) {
        if (ObjectUtils.isEmpty(dto.getTitle()))
            throw new InvalidException("Title không được null");
        if (ObjectUtils.isEmpty(dto.getOptions()))
            throw new InvalidException("Option không được null");
        if (ObjectUtils.isEmpty(dto.getQuantity()))
            throw new InvalidException("Quantity không được null");
        if (ObjectUtils.isEmpty(dto.getPrice()))
            throw new InvalidException("Prices không được null");
        if (ObjectUtils.isEmpty(images))
            throw new InvalidException("Phải có ít nhất một ảnh");

        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setDescriptions(dto.getDescriptions());

        product.setOptions(dto.getOptions());
        product.setTypes(dto.getTypes());
        product.setTrademark(dto.getTrademark());
        product.setProducer(dto.getProducer());
        product.setOrigin(dto.getOrigin());
        product.setPrice(dto.getPrice());
        product.setDiscount(dto.getDiscount());
        product.setQuantity(dto.getQuantity());
        product.setFallIntoCategories(dto.getFallIntoCategories());
        product.setReviews(null);
        product.setImages(cloudinaryService.uploadListImages(images));
        product.setCreatedOn(new Date());
        product.setUpdateOn(new Date());

        product.setEnable(true);

        return product;
    }
}
