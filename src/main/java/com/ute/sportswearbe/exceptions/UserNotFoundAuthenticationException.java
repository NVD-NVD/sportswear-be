package com.ute.sportswearbe.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by: IntelliJ IDE
 * User: NVD-NVD
 * Date: 9/1/2022
 * Time: 4:07 PM
 * Filename: UserNotFoundAuthenticationException
 */
public class UserNotFoundAuthenticationException extends AuthenticationException {
    public UserNotFoundAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserNotFoundAuthenticationException(String msg) {
        super(msg);
    }
}
