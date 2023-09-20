package com.example.api.fcm.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class FcmTokenPK implements Serializable {
    private Long userId;
}