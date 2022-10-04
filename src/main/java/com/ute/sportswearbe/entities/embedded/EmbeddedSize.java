package com.ute.sportswearbe.entities.embedded;

import com.ute.sportswearbe.utils.enums.EnumSize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by: IntelliJ IDE
 * User: NVD-NVD
 * Date: 9/25/2022
 * Time: 10:14 PM
 * Filename: EmbeddedSize
 */
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddedSize {
    private EnumSize size;
    private int quantity;
}
