package com.example.api.common.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {
    LOGIN_IS_NOT_DONE(HttpStatus.UNAUTHORIZED, "로그인 정보가 없습니다"),
    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "권한이 없습니다"),
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "데이터베이스 오류"),
    CODE_IS_NOT_VALID(HttpStatus.BAD_REQUEST, "잘못된 인증번호입니다"),
    CODE_IS_EXPIRED(HttpStatus.BAD_REQUEST, "휴대전화를 인증해주세요");
    private final HttpStatus httpStatus;
    private final String message;
}