package com.ute.sportswearbe.dtos.user;

import com.ute.sportswearbe.entities.embedded.EmbeddedAddress;
import com.ute.sportswearbe.utils.validator.Phone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
