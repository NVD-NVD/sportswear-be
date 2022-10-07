package com.ute.sportswearbe.services.category;

import com.ute.sportswearbe.dtos.CategoryDto;
import com.ute.sportswearbe.entities.Category;
import com.ute.sportswearbe.entities.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService{
    @Override
    public Category getCategoryByName(String name) {
        return null;
    }

    @Override
    public Category getCategoryById(String id) {
        return null;
    }

    @Override
    public List<Category> getAllCategory() {
        return null;
    }

    @Override
    public Page<Category> getCategoryPaging(String search, int page, int size, String sort, String column) {
        return null;
    }

    @Override
    public Page<Product> getProductFromCategoryPaging(String search, int page, int size, String sort, String column) {
        return null;
    }

    @Override
    public Category createNewCategory(String name) {
        return null;
    }

    @Override
    public Category deleteCategory(String id) {
        return null;
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
        return null;
    }

    @Override
    public List<CategoryDto> getListNameCategory() {
        return null;
    }
}
