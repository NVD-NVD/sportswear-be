package com.ute.sportswearbe.dtos.user;

import com.ute.sportswearbe.utils.validator.Phone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: IntelliJ IDE
 * User: NVD-NVD
 * Date: 9/1/2022
 * Time: 4:05 PM
 * Filename: UserDto
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @Phone
    private String phone;
    private String email;

    private String password;

    private String name;

    private List<String> roles = new ArrayList<>();
}
