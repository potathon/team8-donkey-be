package dev.teletubbies.donkey.dto;

public record MarkerSearchDto(
        double latitude,
        double longitude,
        String content
) {
}
