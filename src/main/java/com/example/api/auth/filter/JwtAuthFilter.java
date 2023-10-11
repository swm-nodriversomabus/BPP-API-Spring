package com.example.api.auth.filter;


import com.example.api.auth.dto.SecurityUserDto;
import com.example.api.auth.service.JwtUtilService;
import com.example.api.chat.exception.JwtException;
import com.example.api.user.adapter.out.persistence.UserEntity;
import com.example.api.user.adapter.out.persistence.UserPersistenceAdapter;
import com.example.api.user.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtilService jwtUtilService;
    private final UserService userService;

    /**
     * 필터를 거치지 않는 경우 추가
     * @param request current HTTP request
     * @return
     * @throws ServletException
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getRequestURI().contains("auth/");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//        String atc = Arrays.stream(request.getCookies())
//                .filter(c -> c.getName().equals("access_token"))
//                .findFirst()
//                .map(Cookie::getValue)
//                .orElse("");
        Cookie[] cookies = request.getCookies();
        Optional<Cookie> accessCookie = Optional.ofNullable(cookies)
                .flatMap(cookiesArr -> Arrays.stream(cookiesArr)
                        .filter(c -> "access_token".equals(c.getName()))
                        .findFirst());

        String atc = accessCookie.map(Cookie::getValue).orElse("");
//        String atc = "";
//        String atc = request.getHeader("Authorization");
        // 토큰 검사 생략
        // permitALl인 경우
        if (!StringUtils.hasText(atc)) {
            doFilter(request, response, filterChain);
            return;
        }

        if (!jwtUtilService.verifyToken(atc)) {
            log.info("JWT 이슈 발생");
            throw new JwtException("Access Token 만료");
        }else{
            //TODO 도메인으로 변경 예정
            // 유저가 회원가입 되어 있는지에 대한 체크
            log.info("jwt 인증");

//            log.info(jwtUtilService.getId(atc));
//            log.info(jwtUtilService.getProvider(atc));
//            UserEntity user = userService.findSocialUser(jwtUtilService.getId(atc), jwtUtilService.getProvider(atc))
//                    .orElseThrow(IllegalStateException::new);
            // Security Context에 등록할 user 객체 생성
            SecurityUserDto securityUserDto = userService.findSocialUser(jwtUtilService.getId(atc), jwtUtilService.getProvider(atc));

            // Security Context에 인증 객체 등록
            Authentication authentication = getAuthentication(securityUserDto);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        filterChain.doFilter(request, response);

    }

    public Authentication getAuthentication(SecurityUserDto user){

        return new UsernamePasswordAuthenticationToken(user, "",
                List.of(new SimpleGrantedAuthority(user.getRole()))
        );
    }
}
