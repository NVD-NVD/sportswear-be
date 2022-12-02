package com.ute.sportswearbe.services.product;

import com.ute.sportswearbe.dtos.ProductDto;
import com.ute.sportswearbe.entities.Category;
import com.ute.sportswearbe.entities.Product;
import com.ute.sportswearbe.entities.embedded.EmbeddedCategory;
import com.ute.sportswearbe.exceptions.InvalidException;
import com.ute.sportswearbe.exceptions.NotFoundException;
import com.ute.sportswearbe.repositories.ProductRepository;
import com.ute.sportswearbe.services.category.CategoryService;
import com.ute.sportswearbe.services.cloudinary.CloudinaryService;
import com.ute.sportswearbe.services.file.FilesStorageService;
import com.ute.sportswearbe.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private FilesStorageService storageService;

    @Override
    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Sản phẩm có id %s không tồn tại", id)));
    }

    @Override
    public Product getProductByIdReturnNull(String id) {
        return productRepository.findById(id).orElse(null);
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
    public Page<Product> getProductsPaging(String search, int page, int size, String sort, String column) {
        Pageable pageable = PageUtils.createPageable(page, size, sort, column);
        return productRepository.getProductsPaging(search, pageable);
    }

    @Override
    public Product createNewProduct(ProductDto dto, MultipartFile[] images) {
        Product product = convertDtoToProduct(dto, images);
        product = productRepository.save(product);
        List<EmbeddedCategory> embeddedCategoryList = dto.getFallIntoCategories();
        Category category = new Category();
        for (int i = 0; i < embeddedCategoryList.size(); i++) {
            EmbeddedCategory e = embeddedCategoryList.get(i);
            if (e.getId().isEmpty() && !e.getTitle().isEmpty()) {
                if (categoryService.getCategoryByTitle(e.getTitle()) == null) {
                    Category cate = new Category();
                    cate.setTitle(e.getTitle());
                    cate.setCreatedOn(new Date());
                    cate.setUpdateOn(new Date());
                    cate.setEnable(true);
//                        new Category(null, e.getTitle(), null, new Date(), new Date(), true)
                    category = categoryService.save(new Category(null, e.getTitle(), null, new Date(), new Date(), true));
                } else {
                    category = categoryService.getCategoryByTitle(e.getTitle());
                }
                e.setId(category.getId());
                category = categoryService.getCategoryById(e.getId());
                List<Product> products = new ArrayList<>();
                products.addAll(category.getProductsOfCategory());
                products.add(product);
                category.setProductsOfCategory(products);

            }
        }
        categoryService.updateCategory(category);
        product.setImages(storageService.uploadFiles(images, "products", product.getId()));
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
        product.setOrigin(dto.getOrigin());
        product.setPrice(dto.getPrice());
        product.setDiscount(dto.getDiscount());
        product.setQuantity(dto.getQuantity());
        product.setFallIntoCategories(dto.getFallIntoCategories());
        product.setReviews(dto.getReviews());

        Iterator<String> iterator = product.getImages().iterator();
        List<String> list = dto.getImages();
        while (iterator.hasNext()) {
            String img = iterator.next();
            boolean flag = false;
            for (int i = 0; i < list.size(); i++) {
                if (img.equals(list.get(i))) {
                    flag = true;
                    break;
                }
            }
            if (!flag)
                iterator.remove();
        }

        if (images.length > 0) {
            list.addAll(cloudinaryService.uploadListImages(images));
            product.setImages(cloudinaryService.uploadListImages(images));
        }
        product.setCreatedOn(dto.getCreatedOn());
        product.setUpdateOn(new Date());

        return save(product);
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
        cateId.forEach(i -> {
            Category category = categoryService.getCategoryById(i);
            emCate.add(new EmbeddedCategory(category.getId(), category.getTitle()));
        });
        product.setFallIntoCategories(emCate);

        return save(product);
    }

    @Override
    public Product removeCategoryFromProduct(String proId, List<String> cateId) {
        Product product = getProductById(proId);

        Iterator<EmbeddedCategory> iterator = product.getFallIntoCategories().iterator();
        while (iterator.hasNext()) {
            EmbeddedCategory emCate = iterator.next();
            for (int i = 0; i < cateId.size(); i++) {
                if (emCate.equals(cateId.get(i))) {
                    iterator.remove();
                    break;
                }
            }
        }

        return save(product);
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
        List<String> pImages = product.getImages();
        for (int i = 0; i < images.size(); i++) {
            String image = images.get(i);
            pImages.removeIf(img -> img.equals(image));
        }
        product.setImages(pImages);
        return save(product);
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
        product.setOrigin(dto.getOrigin());
        product.setPrice(dto.getPrice());
        product.setDiscount(dto.getDiscount());
        product.setQuantity(dto.getQuantity());
        product.setFallIntoCategories(dto.getFallIntoCategories());
        product.setReviews(null);
        product.setCreatedOn(new Date());
        product.setUpdateOn(new Date());

        product.setEnable(true);

        return product;
    }
}
