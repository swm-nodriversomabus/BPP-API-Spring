package com.example.api.preference.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class AlcoholPreferencePK implements Serializable {
    private Long alcoholId;
    private Long preferenceId;
}