package com.ute.sportswearbe.services.category;

import com.ute.sportswearbe.dtos.CategoryDto;
import com.ute.sportswearbe.entities.Product;
import com.ute.sportswearbe.entities.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    Category getCategoryByTitle(String Title);

    Category getCategoryById(String id);

    List<Category> getAllCategory();

    Page<Category> getCategoryPaging(String search, int page, int size, String sort, String column);

    Page<Product> getProductFromCategoryPaging(String search, int page, int size, String sort, String column);

    Category createNewCategory(String name);

    Category deleteCategory(String id);

    Category createNewCategory(String name, String...id);

    Category addProductToCategory(String categoryId, String...ProductID);

    Category removeProductFromCategory(String categoryId, String...ProductID);

    Category changeStatusCategory(String id);

    Category updateCategory(Category category);

    Category save(Category category);

    List<String> getListNameCategory();
}
