package com.ute.sportswearbe.repositories;

import com.ute.sportswearbe.entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
//
//    List<Category> findAll();
//
//    Optional<Category> findCategoriesByTitle(String title);
}
