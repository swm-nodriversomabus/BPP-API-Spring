package com.example.api.preference.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserToMovePK.class)
@Table(name="userToMove")
public class UserToMoveEntity {
    @Id
    private Long userId;
    
    @Id
    private Long moveId;
}