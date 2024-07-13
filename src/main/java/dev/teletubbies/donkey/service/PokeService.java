package dev.teletubbies.donkey.service;

import dev.teletubbies.donkey.dto.PokedTop3SearchDto;
import dev.teletubbies.donkey.entity.PokeEntity;
import dev.teletubbies.donkey.entity.UserEntity;
import dev.teletubbies.donkey.exception.InvalidPokedUserException;
import dev.teletubbies.donkey.exception.UserAlreadyPokedTodayException;
import dev.teletubbies.donkey.exception.UserNotFoundException;
import dev.teletubbies.donkey.repository.PokeJpaRepository;
import dev.teletubbies.donkey.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PokeService {

    private final PokeJpaRepository pokeJpaRepository;
    private final UserJpaRepository userJpaRepository;

    @Transactional(readOnly = true)
    public long searchPokedCount(long userId) {
        UserEntity foundUserEntity = userJpaRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return pokeJpaRepository.countByToUserEntity(foundUserEntity);
    }

    @Transactional(readOnly = true)
    public List<PokedTop3SearchDto> searchPokedTop3() {
        return pokeJpaRepository.findPokedTop3();
    }

    public void pokeUser(long userId, String toUsername) {
        UserEntity foundUserEntity = userJpaRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        if (pokeJpaRepository.existsByFromUserEntityAndCreatedDate(foundUserEntity)) {
            throw new UserAlreadyPokedTodayException();
        }

        if (foundUserEntity.getUsername().equals(toUsername)) {
            throw new InvalidPokedUserException();
        }

        UserEntity foundPokedUserEntity = userJpaRepository.findByUsername(toUsername)
                .orElseThrow(UserNotFoundException::new);

        pokeJpaRepository.save(new PokeEntity(foundUserEntity, foundPokedUserEntity));
    }
}
