package dev.teletubbies.donkey.exception;

import org.springframework.http.HttpStatus;

public class InvalidPokedUserException extends CustomException {

    public InvalidPokedUserException() {
        super("INVALID_POKED_USER", HttpStatus.BAD_REQUEST);
    }
}
