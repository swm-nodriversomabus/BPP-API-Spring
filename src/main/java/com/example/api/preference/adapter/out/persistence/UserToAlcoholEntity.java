package com.example.api.preference.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
@IdClass(UserToAlcoholPK.class)
@Table(name="userToAlcohol")
public class UserToAlcoholEntity {
    @Id
    private Long userId;
    
    @Id
    private Long alcoholId;
}