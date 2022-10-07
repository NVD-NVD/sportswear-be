package com.ute.sportswearbe.entities.embedded;

import com.ute.sportswearbe.utils.enums.EnumSize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddedSize {
    private EnumSize size;
    private int quantity;
}
