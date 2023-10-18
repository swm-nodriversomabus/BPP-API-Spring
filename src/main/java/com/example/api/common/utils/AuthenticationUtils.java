package com.example.api.common.utils;

import com.example.api.user.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * 이걸로 유저 정보를 가져오자
 */
public class AuthenticationUtils {
    public static User getCurrentUserAuthentication(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User){
            return (User) authentication.getPrincipal();
        }
        return null;
    }
}
