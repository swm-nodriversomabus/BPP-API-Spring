package com.example.api.common.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public enum ApplicationStateEnum {
    Pending("대기", 0),
    Canceled("취소", 1),
    Declined("거절", 2),
    Approved("수락", 3),
    Owner("작성자", 4);
    
    private final String applicationState;
    private final Integer stateCode;
}