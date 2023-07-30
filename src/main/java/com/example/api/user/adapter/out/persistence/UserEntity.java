package com.example.api.user.adapter.out.persistence;

import com.example.api.user.type.UserGenderType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
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

    @Column(nullable = false, length = 300)
    private String chatroomName;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false, length = 30)
    private String phone;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 300)
    private String address;

    @Column(nullable = false, length = 300)
    private String personality;

    @Column(nullable = false, length = 300)
    private String stateMessage;

    @Column(nullable = false)
    private Integer mannerScore;

    @Column(nullable = false)
    private Long createdUserId;

    @Column(nullable = false)
    private Long lastUpdateUserId;

    @Column(nullable = false)
    private Boolean blacklist;

    @Column(nullable = false)
    private Boolean isActive;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
