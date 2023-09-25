package com.example.api.preference.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class MovePreferencePK implements Serializable {
    private Long moveId;
    private Long preferenceId;
}