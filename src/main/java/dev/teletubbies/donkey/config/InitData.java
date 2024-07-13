package dev.teletubbies.donkey.config;

import dev.teletubbies.donkey.entity.UserEntity;
import dev.teletubbies.donkey.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {

    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        userJpaRepository.save(new UserEntity("eric.shin (신정엽)", passwordEncoder.encode("donkey")));
        userJpaRepository.save(new UserEntity("hazel.kwon (권기연)", passwordEncoder.encode("donkey")));
        userJpaRepository.save(new UserEntity("hyden.lee (이현도)", passwordEncoder.encode("donkey")));
        userJpaRepository.save(new UserEntity("noah.jo (조태현)", passwordEncoder.encode("donkey")));
    }
}
