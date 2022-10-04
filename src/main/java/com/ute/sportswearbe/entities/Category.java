package com.ute.sportswearbe.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Created by: IntelliJ IDE
 * User: NVD-NVD
 * Date: 9/3/2022
 * Time: 9:27 PM
 * Filename: Category
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "categories")
public class Category {
    @Id
    private String id;

    @NotNull(message = "Title can 'not null'")
    @Indexed(unique = true)
    private String title;

    @DBRef
    private Set<Product> productsOfCategory = new HashSet<>();

    private Date createdOn;

    private Date updateOn;

    private boolean enable = true;
}
