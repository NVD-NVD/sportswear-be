package com.ute.sportswearbe.entities;

import com.ute.sportswearbe.entities.embedded.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by: IntelliJ IDE
 * User: NVD-NVD
 * Date: 9/2/2022
 * Time: 11:31 PM
 * Filename: ProductRepository
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    private String id;

    @NotNull(message = "Title can 'not null'")
    private String title;

    private List<EmbeddedDescription> descriptions = new ArrayList<>();;

    private List<String> images = new ArrayList<>();

    private EmbeddedOption option;

    private List<String> types;

    private String trademark; // nhãn hiệu

    private String producer;

    private EmbeddedOrigin origin;// nguồn gốc

    private List<EmbeddedPrice> prices = new ArrayList<>();

    private long quantity;

    private List<EmbeddedCategory> fallIntoCategories = new ArrayList<>();

    private EmbeddedReviews reviews;

    private Date createdOn;

    private Date updateOn;

    private boolean enable = true;
}
