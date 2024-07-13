package dev.teletubbies.donkey.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyPokedTodayException extends CustomException {

    public UserAlreadyPokedTodayException() {
        super("USER_ALREADY_POKED_TODAY", HttpStatus.BAD_REQUEST);
    }
}
