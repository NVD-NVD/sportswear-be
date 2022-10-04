package com.ute.sportswearbe.repositories;

import com.ute.sportswearbe.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by: IntelliJ IDE
 * User: NVD-NVD
 * Date: 9/3/2022
 * Time: 9:34 PM
 * Filename: ProductRepository
 */
public interface ProductRepository extends MongoRepository<Product, String> {
//    List<Product> findAll();
//
//    @Query(value = "{$or: [{'title' : { $regex: ?0, $options: 'i' } }, {'size' : { $regex: ?0, $options: 'i' } }" +
//            ", {'fallIntoCategories.name': { $regex: ?0, $options: 'i' }} ]}"
//            , sort = "{'enable' : -1, 'email' : 1}")
//    Page<Product> getProductPaging(String search, Pageable pageable);


}
