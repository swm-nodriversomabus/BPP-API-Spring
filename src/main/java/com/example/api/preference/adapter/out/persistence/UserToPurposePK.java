package com.example.api.preference.adapter.out.persistence;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserToPurposePK implements Serializable {
    private Long userId;
    private Long purposeId;
}