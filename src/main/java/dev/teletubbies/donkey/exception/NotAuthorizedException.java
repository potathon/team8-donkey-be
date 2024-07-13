package dev.teletubbies.donkey.exception;

import org.springframework.http.HttpStatus;

public class NotAuthorizedException extends CustomException {

    public NotAuthorizedException() {
        super("NOT_AUTHORIZED", HttpStatus.FORBIDDEN);
    }
}
