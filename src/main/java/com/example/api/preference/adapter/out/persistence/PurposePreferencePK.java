package com.example.api.preference.adapter.out.persistence;

import lombok.Data;

import java.io.Serializable;

@Data
public class PurposePreferencePK implements Serializable {
    private Long purposeId;
    private Long preferenceId;
}