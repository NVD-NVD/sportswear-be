package com.ute.sportswearbe.utils.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by: IntelliJ IDE
 * User: NVD-NVD
 * Date: 9/3/2022
 * Time: 6:24 PM
 * Filename: Phone
 */
@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target( {ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {
    String message() default "{Phone}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
