package dev.teletubbies.donkey.controller;

import dev.teletubbies.donkey.dto.PokedTop3SearchDto;
import dev.teletubbies.donkey.dto.PokedCountSearchDto;
import dev.teletubbies.donkey.request.UserPokeRequest;
import dev.teletubbies.donkey.response.CommonResponse;
import dev.teletubbies.donkey.service.PokeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/poke")
public class PokeController {

    private final PokeService pokeService;

    @GetMapping
    public ResponseEntity<CommonResponse<PokedCountSearchDto>> searchPokedCount(HttpSession httpSession) {
        Long userId = (Long) httpSession.getAttribute("userId");

        long pokedCount = pokeService.searchPokedCount(userId);

        return ResponseEntity.ok(new CommonResponse<>("SUCCESS", new PokedCountSearchDto(pokedCount)));
    }

    @GetMapping("/ranking")
    public ResponseEntity<CommonResponse<List<PokedTop3SearchDto>>> searchPokedTop3() {
        List<PokedTop3SearchDto> pokedTop3SearchDtos = pokeService.searchPokedTop3();

        return ResponseEntity.ok(new CommonResponse<>("SUCCESS", pokedTop3SearchDtos));
    }

    @PostMapping
    public void pokeUser(HttpSession httpSession, @RequestBody UserPokeRequest request) {
        Long userId = (Long) httpSession.getAttribute("userId");

        pokeService.pokeUser(userId, request.username());
    }
}
