package com.example.api.user.adapter.out.persistence;

import com.example.api.user.dto.UserDto;
import com.example.api.user.type.UserGenderType;
import com.example.api.user.type.UserRoleType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 30)
    private String username;

    @Column(nullable = false, length = 30)
    private String nickname;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserGenderType gender;

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
    private UserRoleType role;

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

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private Boolean isActive;
    
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
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .isActive(isActive)
                .build();
    }
}