package dev.teletubbies.donkey.controller;

import dev.teletubbies.donkey.request.SignInRequest;
import dev.teletubbies.donkey.request.UserPasswordInitRequest;
import dev.teletubbies.donkey.service.SignService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// TODO: 로그인 관련 필터 생성 시 해당 클래스 삭제

@RestController
@RequiredArgsConstructor
public class SignController {

    private final SignService signService;

    @PostMapping("/sign-in")
    public ResponseEntity<Void> signIn(HttpSession httpSession, @RequestBody SignInRequest request) {
        long userId = signService.signIn(request.username(), request.password());

        httpSession.setAttribute("userId", userId);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/sign-password")
    public ResponseEntity<Void> initPassword(HttpSession httpSession, @RequestBody UserPasswordInitRequest request) {
        Long userId = (Long) httpSession.getAttribute("userId");

        signService.changePassword(userId, request.password());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-out")
    public ResponseEntity<Void> signOut(HttpSession httpSession) {
        httpSession.invalidate();

        return ResponseEntity.ok().build();
    }
}
