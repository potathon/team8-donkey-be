package dev.teletubbies.donkey.repository;

import dev.teletubbies.donkey.entity.MarkerEntity;
import dev.teletubbies.donkey.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MarkerJpaRepository extends JpaRepository<MarkerEntity, Long> {

    @Query("SELECT m FROM MarkerEntity m WHERE m.createdDate >= CURRENT_DATE")
    List<MarkerEntity> findAllByCreatedDateToday();

    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END " +
            "FROM MarkerEntity m " +
            "WHERE m.userEntity = :userEntity AND m.createdDate >= CURRENT_DATE")
    boolean existsByUserEntityAndToday(@Param("userEntity") UserEntity userEntity);
}
