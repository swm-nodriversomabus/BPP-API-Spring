package com.example.api.common.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {
    // 200 OK
    SUCCESS(HttpStatus.OK, "정상 처리되었습니다"),
    // 201 Created
    CREATED(HttpStatus.CREATED, "생성되었습니다"),
    // 400 Bad Request
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "유저 정보가 없습니다"),
    CODE_IS_EXPIRED(HttpStatus.BAD_REQUEST, "휴대전화를 인증해주세요"),
    CODE_IS_NOT_VALID(HttpStatus.BAD_REQUEST, "잘못된 인증번호입니다"),
    MATCHING_NOT_FOUND(HttpStatus.BAD_REQUEST, "매칭 정보가 없습니다"),
    PREFERENCE_NOT_FOUND(HttpStatus.BAD_REQUEST, "선호도 정보가 없습니다"),
    // 401 Unauthorized
    LOGIN_IS_NOT_DONE(HttpStatus.UNAUTHORIZED, "로그인 정보가 없습니다"),
    // 403 Forbidden
    INVALID_PERMISSION(HttpStatus.FORBIDDEN, "권한이 없습니다"),
    // 500 Internal Server Error
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "데이터베이스 오류");
    
    private final HttpStatus httpStatus;
    private final String message;
}