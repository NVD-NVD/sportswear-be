package com.ute.sportswearbe.services.category;

import com.ute.sportswearbe.dtos.CategoryDto;
import com.ute.sportswearbe.entities.Category;
import com.ute.sportswearbe.entities.Product;
import com.ute.sportswearbe.exceptions.InvalidException;
import com.ute.sportswearbe.exceptions.ServerException;
import com.ute.sportswearbe.repositories.CategoryRepository;
import com.ute.sportswearbe.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category getCategoryByTitle(String title) {
        return categoryRepository.findCategoryByTitle(title);
//        return categoryRepository.getCategoryByTitle(title).orElse(null);
    }

    @Override
    public Category getCategoryById(String id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Category> getCategoryPaging(String search, int page, int size, String sort, String column) {
        Pageable pageable = PageUtils.createPageable(page, size, sort, column);
        return categoryRepository.getCategoryPaging(search, pageable);
    }

    @Override
    public Page<Product> getProductFromCategoryPaging(String search, int page, int size, String sort, String column) {
        return null;
    }

    @Override
    public Category createNewCategory(String title) {
        if (getCategoryByTitle(title)!= null)
            throw new InvalidException(String.format("Category có Title %s đã tồn tại.", title));
        return save(new Category(null, title, null, new Date(), new Date(), true));
    }

    @Override
    public Category deleteCategory(String id) {
        Category category = getCategoryById(id);
        if (category == null)
            throw new InvalidException(String.format("Category có ID %s không tồn tại.", id));
        category.setEnable(false);
        return save(category);
    }

    @Override
    public Category createNewCategory(String name, String... id) {
        return null;
    }

    @Override
    public Category addProductToCategory(String categoryId, String... ProductID) {
        return null;
    }

    @Override
    public Category removeProductFromCategory(String categoryId, String... ProductID) {
        return null;
    }

    @Override
    public Category changeStatusCategory(String id) {
        return null;
    }

    @Override
    public Category updateCategory(Category category) {
        return null;
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<String> getListNameCategory() {
        List<Category> categories = categoryRepository.findAll();
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            titles.set(i, categories.get(i).getTitle());
        }
        return titles;
    }
}
