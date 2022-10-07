package com.ute.sportswearbe.repositories;

import com.ute.sportswearbe.entities.FilesDeleted;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileDeletedRepository extends MongoRepository<FilesDeleted, String> {
}
