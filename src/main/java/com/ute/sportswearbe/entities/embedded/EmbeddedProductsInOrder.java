package com.ute.sportswearbe.entities.embedded;

import com.ute.sportswearbe.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddedProductsInOrder {
    private Product product;
    private long quantity;
    private EmbeddedOption options;
    private float total;
}
