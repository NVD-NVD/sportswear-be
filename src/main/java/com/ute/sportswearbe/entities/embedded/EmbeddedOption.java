package com.ute.sportswearbe.entities.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddedOption {
    private List<EmbeddedSize> sizes;

    private List<String> color;

    private List<String> features;

}
