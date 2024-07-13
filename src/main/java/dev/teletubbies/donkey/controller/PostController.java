package dev.teletubbies.donkey.controller;

import dev.teletubbies.donkey.entity.PostEntity;
import dev.teletubbies.donkey.request.PostCreateRequest;
import dev.teletubbies.donkey.response.CommonResponse;
import dev.teletubbies.donkey.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<PostEntity>> search(@PathVariable("id") Long id) {
        PostEntity searchPostEntity = postService.search(id);

        return ResponseEntity.ok(new CommonResponse<>("SUCCESS", searchPostEntity));
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<PostEntity>>> searchAll() {
        List<PostEntity> postEntities = postService.searchAll();

        return ResponseEntity.ok(new CommonResponse<>("SUCCESS", postEntities));
    }

    @PostMapping
    public ResponseEntity<Void> create(HttpSession httpSession, @RequestBody PostCreateRequest request) {
        Long userId = (Long) httpSession.getAttribute("userId");

        postService.createPost(userId, request.content());

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Void> updatePost(HttpSession httpSession, @PathVariable Long postId, @RequestBody PostCreateRequest request) {
        Long userId = (Long) httpSession.getAttribute("userId");

        postService.updatePost(userId, postId, request.content());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(HttpSession httpSession, @PathVariable Long postId) {
        Long userId = (Long) httpSession.getAttribute("userId");

        postService.deletePost(userId, postId);

        return ResponseEntity.ok().build();
    }
}
