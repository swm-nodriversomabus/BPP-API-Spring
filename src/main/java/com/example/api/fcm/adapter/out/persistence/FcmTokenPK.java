package com.example.api.fcm.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
public class FcmTokenPK implements Serializable {
    private UUID userId;
}