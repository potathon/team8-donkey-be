package dev.teletubbies.donkey.service;

import dev.teletubbies.donkey.dto.MarkerSearchDto;
import dev.teletubbies.donkey.entity.MarkerEntity;
import dev.teletubbies.donkey.entity.UserEntity;
import dev.teletubbies.donkey.exception.UserAlreadyMarkedTodayException;
import dev.teletubbies.donkey.exception.UserNotFoundException;
import dev.teletubbies.donkey.repository.MarkerJpaRepository;
import dev.teletubbies.donkey.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MarkerService {

    private final MarkerJpaRepository markerJpaRepository;
    private final UserJpaRepository userJpaRepository;

    @Transactional(readOnly = true)
    public List<MarkerSearchDto> searchAllByToday() {
        List<MarkerEntity> markerEntities = markerJpaRepository.findAllByCreatedDateToday();

        return markerEntities.stream()
                .map(markerEntity -> new MarkerSearchDto(markerEntity.getLatitude(), markerEntity.getLongitude(), markerEntity.getContent()))
                .toList();
    }

    public void addMarker(long userId, double latitude, double longitude, String content) {
        UserEntity foundUserEntity = userJpaRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        if (markerJpaRepository.existsByUserEntityAndToday(foundUserEntity)) {
            throw new UserAlreadyMarkedTodayException();
        }

        markerJpaRepository.save(new MarkerEntity(foundUserEntity, latitude, longitude, content));
    }
}
