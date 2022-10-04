package com.ute.sportswearbe.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by: IntelliJ IDE
 * User: NVD-NVD
 * Date: 9/1/2022
 * Time: 4:05 PM
 * Filename: TokenDetails.java
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TokenDetails {
    private String token;
    private long expired;
    private List<String> roles;
    private String token_type;
}