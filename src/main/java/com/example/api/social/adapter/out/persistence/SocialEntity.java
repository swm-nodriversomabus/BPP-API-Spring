package com.example.api.social.adapter.out.persistence;


import com.example.api.user.adapter.out.persistence.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "social")
public class SocialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long socialId;

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


//    @OneToMany(mappedBy = "social", fetch = FetchType.LAZY)
//    @ToString.Exclude
//    private List<UserEntity> users;
}

