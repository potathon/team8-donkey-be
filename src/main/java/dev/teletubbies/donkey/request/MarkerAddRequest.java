package dev.teletubbies.donkey.request;

public record MarkerAddRequest(
        double latitude,
        double longitude,
        String content
) {
}
