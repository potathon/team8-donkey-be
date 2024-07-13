package dev.teletubbies.donkey.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends CustomException {

    public UserNotFoundException() {
        super("USER_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
}
