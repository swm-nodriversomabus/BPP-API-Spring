package com.example.api.common.utils;

import com.example.api.auth.domain.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 이걸로 유저 정보를 가져오자
 */
public class AuthenticationUtils {
    public static SecurityUser getCurrentUserAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof SecurityUser) {
            return (SecurityUser) authentication.getPrincipal();
        }
        return null;
    }
}