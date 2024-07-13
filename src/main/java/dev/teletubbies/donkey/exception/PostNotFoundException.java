package dev.teletubbies.donkey.exception;

import org.springframework.http.HttpStatus;

public class PostNotFoundException extends CustomException {

    public PostNotFoundException() {
        super("POST_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
}
