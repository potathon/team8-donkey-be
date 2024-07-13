package dev.teletubbies.donkey.service;

import dev.teletubbies.donkey.entity.UserEntity;
import dev.teletubbies.donkey.exception.AuthenticationFailedException;
import dev.teletubbies.donkey.exception.UserNotFoundException;
import dev.teletubbies.donkey.repository.UserJpaRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SignService {

    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;

    public long signIn(String username, String password) {
        UserEntity foundUserentity = userJpaRepository.findByUsername(username)
                .orElseThrow(AuthenticationFailedException::new);

        if (!passwordEncoder.matches(password, foundUserentity.getPassword())) {
            throw new AuthenticationFailedException();
        }

        return foundUserentity.getId();
    }

    public void changePassword(long userId, String password) {
        UserEntity foundUserEntity = userJpaRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        foundUserEntity.changePassword(passwordEncoder.encode(password));
    }

    @PostConstruct
    private void postConstruct() {
        userJpaRepository.save(new UserEntity("eric.shin (신정엽)", passwordEncoder.encode("donkey")));
        userJpaRepository.save(new UserEntity("hazel.kwon (권기연)", passwordEncoder.encode("donkey")));
        userJpaRepository.save(new UserEntity("hyden.lee (이현도)", passwordEncoder.encode("donkey")));
        userJpaRepository.save(new UserEntity("noah.jo (조태현)", passwordEncoder.encode("donkey")));
    }
}
