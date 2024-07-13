package dev.teletubbies.donkey.exception;

import org.springframework.http.HttpStatus;

public class AuthenticationFailedException extends CustomException {

    public AuthenticationFailedException() {
        super("AUTHENTICATION_FAILED", HttpStatus.UNAUTHORIZED);
    }
}
