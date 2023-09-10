package com.example.api.user.adapter.out.persistence;

import com.example.api.common.entity.BaseEntity;
import com.example.api.user.dto.UserDto;
import com.example.api.user.type.UserGenderEnum;
import com.example.api.user.type.UserRoleEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user")
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 30)
    private String username;

    @Column(nullable = false, length = 30)
    private String nickname;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserGenderEnum gender;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false, length = 30)
    private String phone;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 300)
    private String address;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    @Column(nullable = false)
    private Boolean blacklist;

    @Column(nullable = false, length = 300)
    private String personality;

    @Column(nullable = false, length = 300)
    private String stateMessage;

    @Column(nullable = false)
    private Integer mannerScore;

    @Column(nullable = false)
    private Long createdUserId;

    @Column(nullable = false)
    private Long updatedUserId;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(length = 100, nullable = false)
    @ColumnDefault("''")
    private String naverId;

    @Column(length = 100, nullable = false)
    @ColumnDefault("''")
    private String kakaoId;

    @Column(length = 100, nullable = false)
    @ColumnDefault("''")
    private String googleId;

    @Column(length = 100, nullable = false)
    @ColumnDefault("''")
    private String instaId;

    @Column(length = 100, nullable = false)
    @ColumnDefault("''")
    private String appleId;
    
    public UserDto toDto() {
        return UserDto.builder()
                .userId(userId)
                .username(username)
                .nickname(nickname)
                .gender(gender)
                .age(age)
                .phone(phone)
                .email(email)
                .address(address)
                .role(role)
                .blacklist(blacklist)
                .personality(personality)
                .stateMessage(stateMessage)
                .mannerScore(mannerScore)
                .createdUserId(createdUserId)
                .updatedUserId(updatedUserId)
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .isActive(isActive)
                .build();
    }
}