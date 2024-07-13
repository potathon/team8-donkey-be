package dev.teletubbies.donkey.repository;

import dev.teletubbies.donkey.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<PostEntity, Long> {
}
