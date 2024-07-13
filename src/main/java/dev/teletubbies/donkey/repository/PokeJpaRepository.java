package dev.teletubbies.donkey.repository;

import dev.teletubbies.donkey.dto.PokedTop3SearchDto;
import dev.teletubbies.donkey.entity.PokeEntity;
import dev.teletubbies.donkey.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PokeJpaRepository extends JpaRepository<PokeEntity, Long> {

    long countByToUserEntity(UserEntity userEntity);

    @Query("SELECT new dev.teletubbies.donkey.dto.PokedTop3SearchDto(p.toUserEntity.username, COUNT(p)) " +
            "FROM PokeEntity p " +
            "GROUP BY p.toUserEntity " +
            "ORDER BY COUNT(p) DESC")
    List<PokedTop3SearchDto> findPokedTop3();

    @Query("SELECT COUNT(p) > 0 FROM PokeEntity p " +
            "WHERE p.fromUserEntity = :fromUser AND DATE(p.createdDate) = CURRENT_DATE")
    boolean existsByFromUserEntityAndCreatedDate(UserEntity fromUser);
}
