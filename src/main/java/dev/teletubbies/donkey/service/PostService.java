package dev.teletubbies.donkey.service;

import dev.teletubbies.donkey.entity.PostEntity;
import dev.teletubbies.donkey.entity.UserEntity;
import dev.teletubbies.donkey.exception.NotAuthorizedException;
import dev.teletubbies.donkey.exception.PostNotFoundException;
import dev.teletubbies.donkey.exception.UserNotFoundException;
import dev.teletubbies.donkey.repository.PostJpaRepository;
import dev.teletubbies.donkey.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostJpaRepository postJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public PostEntity search(long id) {
        return postJpaRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);
    }

    public List<PostEntity> searchAll() {
        return postJpaRepository.findAll();
    }

    public void createPost(long userId, String content) {
        UserEntity foundUserEntity = userJpaRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        postJpaRepository.save(new PostEntity(foundUserEntity, content));
    }

    public void updatePost(long userId, long postId, String content) {
        if (userJpaRepository.existsById(userId)) {
            throw new UserNotFoundException();
        }

        PostEntity foundPostEntity = postJpaRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        if (foundPostEntity.getUserEntity().getId() != userId) {
            throw new NotAuthorizedException();
        }

        foundPostEntity.updateContent(content);
    }

    public void deletePost(long userId, long postId) {
        if (userJpaRepository.existsById(userId)) {
            throw new UserNotFoundException();
        }

        PostEntity foundPostEntity = postJpaRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        if (foundPostEntity.getUserEntity().getId() != userId) {
            throw new NotAuthorizedException();
        }

        postJpaRepository.deleteById(postId);
    }
}
