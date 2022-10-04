package com.ute.sportswearbe.entities.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by: IntelliJ IDE
 * User: NVD-NVD
 * Date: 9/21/2022
 * Time: 10:31 PM
 * Filename: EmbeddedPrice
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddedPrice {
    private String currency;
    private String price;
}
