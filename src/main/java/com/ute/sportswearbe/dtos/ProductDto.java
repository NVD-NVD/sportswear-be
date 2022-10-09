package com.ute.sportswearbe.dtos;

import com.ute.sportswearbe.entities.embedded.*;
import com.ute.sportswearbe.entities.embedded.reviews.EmbeddedReviews;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    @NotNull(message = "Title can 'not null'")
    private String title;

    private List<EmbeddedDescription> descriptions = new ArrayList<>();

    private EmbeddedOption options;

    private List<String> types;

    private String trademark; // nhãn hiệu

    private String producer;

    private EmbeddedOrigin origin;// nguồn gốc

    private List<EmbeddedPrice> price = new ArrayList<>();

    @Min(value = 0, message = "Discount không thể nhỏ hơn 0%")
    @Max(value = 100, message = "Discount không thể lớn hơn 100%")
    private float discount;

    private long quantity;

    private List<EmbeddedCategory> fallIntoCategories = new ArrayList<>();

    private EmbeddedReviews reviews;

    private Date createdOn;

    private Date updateOn;
}
