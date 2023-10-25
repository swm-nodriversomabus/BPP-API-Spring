package com.example.api.common.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public enum ApplicationStateEnum {
    Pending("대기", 0),
    Declined("거절", 1),
    Approved("수락", 2),
    Owner("작성자", 3);
    
    private final String applicationState;
    private final Integer stateCode;
}