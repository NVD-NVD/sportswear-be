package com.ute.sportswearbe.entities.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by: IntelliJ IDE
 * User: NVD-NVD
 * Date: 9/29/2022
 * Time: 11:03 PM
 * Filename: EmbeddedOption
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddedOption {
    private List<EmbeddedSize> sizes;

    private List<String> color;

    private List<String> features;

}
