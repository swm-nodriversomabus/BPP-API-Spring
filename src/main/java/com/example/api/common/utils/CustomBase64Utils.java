package com.example.api.common.utils;


import java.util.Base64;

public class CustomBase64Utils {
    // [base64 인코딩 수행 메소드 : 문자열 >> base64 문자열 데이터]
    public static String getBase64EncodeString(String content){
        return Base64.getEncoder().encodeToString(content.getBytes()); // Base64 암호화된 문자열로 반환
    }


    // [base64 인코딩 수행 메소드 : 바이트값 >> base64 문자열 데이터]
    public static String getBase64EncodeByte(byte[] content){
        return Base64.getEncoder().encodeToString(content);
    }


    // [base64 디코딩 수행 메소드 : base64 문자열 >> 문자열 데이터]
    public static String getBase64DecodeString(String content){
        return new String(Base64.getDecoder().decode(content.getBytes()));
    }


    // [base64 디코딩 수행 메소드 : base64 바이트값 >> 문자열 데이터]
    public static String getBase64DecodeByte(byte[] content){
        return new String(Base64.getDecoder().decode(content));
    }
}
