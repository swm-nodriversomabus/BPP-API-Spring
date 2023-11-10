package com.example.api.preference.adapter.in.rest;

import com.example.api.auth.domain.SecurityUser;
import com.example.api.common.exception.CustomException;
import com.example.api.common.type.ErrorCodeEnum;
import com.example.api.common.utils.AuthenticationUtils;
import com.example.api.preference.application.port.in.*;
import com.example.api.preference.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Preference", description = "Preference API")
public class PreferenceController {
    private final SavePreferenceUsecase savePreferenceUsecase;
    private final FindPreferenceUsecase findPreferenceUsecase;
    private final UserPreferenceUsecase userPreferenceUsecase;
    private final MatchingPreferenceUsecase matchingPreferenceUsecase;
    private final ComparePreferenceUsecase comparePreferenceUsecase;

    /**
     * 선호도 데이터 생성
     * @param preferenceDto (데이터)
     * @return preference data
     */
    @Operation(summary = "Create preference", description = "선호도 데이터를 생성한다.")
    @PostMapping("/preference")
    public FindPreferenceDto createPreference(@Valid @RequestBody SavePreferenceDto preferenceDto) {
        return savePreferenceUsecase.createPreference(preferenceDto);
    }

    /**
     * 사용자 선호도 설정
     * @param userPreferenceDto (데이터)
     * @return user preference data
     */
    @Operation(summary = "Connect user preference", description = "사용자 선호도를 설정한다.")
    @PostMapping("/user-preference")
    public UserPreferenceDto createUserPreference(@Valid @RequestBody UserPreferenceDto userPreferenceDto) {
        return userPreferenceUsecase.createUserPreference(userPreferenceDto);
    }

    /**
     * 매칭 선호도 설정
     * @param matchingPreferenceDto (데이터)
     * @return matching preference data
     */
    @Operation(summary = "Connect matching preference", description = "매칭 선호도를 설정한다.")
    @PostMapping("/matching-preference")
    public MatchingPreferenceDto createMatchingPreference(@Valid @RequestBody MatchingPreferenceDto matchingPreferenceDto) {
        return matchingPreferenceUsecase.createMatchingPreference(matchingPreferenceDto);
    }

    /**
     * 사용자 선호도 조회
     * @return user preference data
     */
    @Operation(summary = "Get user preference", description = "사용자 선호도를 조회한다.")
    @GetMapping("/user/preference")
    public FindPreferenceDto getUserPreference() {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("PreferenceController::getUserPreference: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        return findPreferenceUsecase.getUserPreference(securityUser.getUserId());
    }

    /**
     * 매칭 선호도 조회
     * @param matchingId (ID)
     * @return matching preference data
     */
    @Operation(summary = "Get matching preference", description = "매칭 선호도를 조회한다.")
    @GetMapping("/matching/{matchingId}/preference")
    public FindPreferenceDto findMatchingPreference(@PathVariable Long matchingId) {
        return findPreferenceUsecase.getMatchingPreference(matchingId);
    }

    /**
     * 사용자 선호도 변경
     * @param savePreferenceDto (데이터)
     * @return user preference data
     */
    @Operation(summary = "Update user preference", description = "사용자 선호도를 변경한다.")
    @PutMapping("/user/preference")
    public FindPreferenceDto updateUserPreference(@RequestBody SavePreferenceDto savePreferenceDto) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("PreferenceController::updateUserPreference: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        return userPreferenceUsecase.updateUserPreference(securityUser.getUserId(), savePreferenceDto);
    }

    /**
     * 매칭 선호도 변경
     * @param matchingId (ID)
     * @param savePreferenceDto (데이터)
     * @return matching preference data
     */
    @Operation(summary = "Update matching preference", description = "매칭 선호도를 변경한다.")
    @PutMapping("/matching/{matchingId}/preference")
    public FindPreferenceDto updateMatchingPreference(@PathVariable Long matchingId, @RequestBody SavePreferenceDto savePreferenceDto) {
        return  matchingPreferenceUsecase.updateMatchingPreference(matchingId, savePreferenceDto);
    }
}