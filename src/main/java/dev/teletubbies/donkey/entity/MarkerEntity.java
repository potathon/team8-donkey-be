package dev.teletubbies.donkey.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "marker")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MarkerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    private double latitude;

    private double longitude;

    private String content;

    @CreatedDate
    private LocalDateTime createdDate;

    public MarkerEntity(UserEntity userEntity, double latitude, double longitude, String content) {
        this.userEntity = userEntity;
        this.latitude = latitude;
        this.longitude = longitude;
        this.content = content;
    }
}
