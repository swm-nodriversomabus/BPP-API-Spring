package com.example.api.user.adapter.out.persistence;

import com.example.api.social.adapter.out.persistence.SocialEntity;
import com.example.api.common.entity.BaseEntity;
import com.example.api.user.type.UserGenderEnum;
import com.example.api.user.type.UserRoleEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(24)")
    private UUID userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name="social_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @ToString.Exclude
    private SocialEntity socialId;

    @Column(nullable = false, length = 30)
    private String username;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserGenderEnum gender;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false, length = 30)
    private String phone;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    @Column(nullable = false)
    private Boolean blacklist;

    @Column(nullable = false, length = 300)
    private String stateMessage;

    @Column(nullable = false)
    private Integer mannerScore;

    @Column(nullable = false)
    private Boolean isActive;
}