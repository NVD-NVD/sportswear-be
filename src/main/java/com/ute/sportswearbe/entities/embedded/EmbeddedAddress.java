package com.ute.sportswearbe.entities.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by: IntelliJ IDE
 * User: NVD-NVD
 * Date: 9/2/2022
 * Time: 6:39 PM
 * Filename: EmbeddedAddrees
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddedAddress {
    private String number;

    private String street;

    private String district;

    private String city;

    private String country;
}
