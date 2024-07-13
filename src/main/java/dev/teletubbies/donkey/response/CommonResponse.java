package dev.teletubbies.donkey.response;

public record CommonResponse<T>(
        String message,
        T data
) {
}
