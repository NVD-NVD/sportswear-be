package com.ute.sportswearbe.dtos.user;

import com.ute.sportswearbe.entities.embedded.EmbeddedAddress;
import com.ute.sportswearbe.utils.validator.Phone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * Created by: IntelliJ IDE
 * User: NVD-NVD
 * Date: 9/1/2022
 * Time: 4:28 PM
 * Filename: UserCoreDto
 */
@Getter
@Setter
@NoArgsConstructor
public class UserCoreDto {
    @Phone
    private String phone;

    @Email
    private String email;

    private String password;

    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past
    private Date birthday;

    private String gender;

    private EmbeddedAddress address;

    private String avatar;
}
