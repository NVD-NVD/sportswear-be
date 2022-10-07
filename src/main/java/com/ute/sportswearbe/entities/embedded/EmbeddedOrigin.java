package com.ute.sportswearbe.entities.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddedOrigin {
    private String producer;

    private String manufacturerIn; // sản xuất tại
}
