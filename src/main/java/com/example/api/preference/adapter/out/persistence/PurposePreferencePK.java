package com.example.api.preference.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class PurposePreferencePK implements Serializable {
    private Long purposeId;
    private Long preferenceId;
}