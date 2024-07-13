package dev.teletubbies.donkey.controller;

import dev.teletubbies.donkey.dto.MarkerSearchDto;
import dev.teletubbies.donkey.request.MarkerAddRequest;
import dev.teletubbies.donkey.response.CommonResponse;
import dev.teletubbies.donkey.service.MarkerService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/marker")
public class MakerController {

    private final MarkerService markerService;

    @GetMapping
    public ResponseEntity<CommonResponse<List<MarkerSearchDto>>> searchAllByToday() {
        List<MarkerSearchDto> markerSearchDtos = markerService.searchAllByToday();

        return ResponseEntity.ok(new CommonResponse<>("SUCCESS", markerSearchDtos));
    }

    @PostMapping
    public ResponseEntity<Void> addMarker(HttpSession httpSession, @RequestBody MarkerAddRequest request) {
        Long userId = (Long) httpSession.getAttribute("userId");

        markerService.addMarker(userId, request.latitude(), request.longitude(), request.content());

        return ResponseEntity.ok().build();
    }
}
