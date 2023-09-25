package com.example.api.auth.type;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenResponseStatus {
    private Integer status;
    private Object data;
    private String accessToken;
    public TokenResponseStatus(Integer status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
    }

    public static TokenResponseStatus addStatus(Integer status, String accessToken) {
        return new TokenResponseStatus(status, accessToken);
    }
}
