package com.ute.sportswearbe.repositories;

import com.ute.sportswearbe.entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by: IntelliJ IDE
 * User: NVD-NVD
 * Date: 9/3/2022
 * Time: 9:34 PM
 * Filename: CategoryRepository
 */
public interface CategoryRepository extends MongoRepository<Category, String> {
//
//    List<Category> findAll();
//
//    Optional<Category> findCategoriesByTitle(String title);
}
