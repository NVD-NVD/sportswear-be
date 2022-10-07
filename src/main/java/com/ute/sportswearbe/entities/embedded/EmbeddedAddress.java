package com.ute.sportswearbe.entities.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
