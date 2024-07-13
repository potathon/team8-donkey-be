package dev.teletubbies.donkey.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyMarkedTodayException extends CustomException {

    public UserAlreadyMarkedTodayException() {
        super("USER_ALREADY_MARKED_TODAY", HttpStatus.BAD_REQUEST);
    }
}
