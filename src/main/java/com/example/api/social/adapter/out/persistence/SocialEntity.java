package com.example.api.social.adapter.out.persistence;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="social")
public class SocialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

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



}
