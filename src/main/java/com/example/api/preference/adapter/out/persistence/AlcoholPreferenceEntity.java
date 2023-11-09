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
@IdClass(AlcoholPreferencePK.class)
@Table(name="userToAlcohol")
public class AlcoholPreferenceEntity {
    @Id
    private Long alcoholId;
    
    @Id
    private Long preferenceId;
}