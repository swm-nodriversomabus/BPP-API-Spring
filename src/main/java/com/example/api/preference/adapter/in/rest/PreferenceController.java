package com.example.api.preference.adapter.in.rest;

import com.example.api.preference.application.port.in.ComparePreferenceUsecase;
import com.example.api.preference.application.port.in.MatchingPreferenceUsecase;
import com.example.api.preference.application.port.in.SavePreferenceUsecase;
import com.example.api.preference.application.port.in.UserPreferenceUsecase;
import com.example.api.preference.dto.ComparePreferenceDto;
import com.example.api.preference.dto.MatchingPreferenceDto;
import com.example.api.preference.dto.SavePreferenceDto;
import com.example.api.preference.dto.UserPreferenceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Preference", description = "Preference API")
public class PreferenceController {
    private final SavePreferenceUsecase savePreferenceUsecase;
    private final UserPreferenceUsecase userPreferenceUsecase;
    private final MatchingPreferenceUsecase matchingPreferenceUsecase;
    private final ComparePreferenceUsecase comparePreferenceUsecase;

    /**
     * 선호도 객체 추가
     * @param preferenceDto (데이터)
     * @return SavePreferenceDto
     */
    @Operation(summary = "Create preference", description = "선호도 데이터를 생성한다.")
    @PostMapping("/preference")
    public SavePreferenceDto createPreference(@RequestBody SavePreferenceDto preferenceDto) {
        return savePreferenceUsecase.createPreference(preferenceDto);
    }

    /**
     * 사용자 선호도 설정
     * @param userPreferenceDto (데이터)
     * @return UserPreferenceDto
     */
    @Operation(summary = "Connect user preference", description = "사용자 선호도를 설정한다.")
    @PostMapping("/user-preference")
    public UserPreferenceDto createUserPreference(@RequestBody UserPreferenceDto userPreferenceDto) {
        return userPreferenceUsecase.createUserPreference(userPreferenceDto);
    }

    /**
     * 매칭 선호도 설정
     * @param matchingPreferenceDto (데이터)
     * @return MatchingPreferenceDto
     */
    @Operation(summary = "Connect matching preference", description = "매칭 선호도를 설정한다.")
    @PostMapping("/matching-preference")
    public MatchingPreferenceDto createMatchingPreference(@RequestBody MatchingPreferenceDto matchingPreferenceDto) {
        return matchingPreferenceUsecase.createMatchingPreference(matchingPreferenceDto);
    }

    /**
     * 사용자 선호도 조회
     * @param userId (ID)
     * @return ComparePreferenceDto
     */
    @Operation(summary = "Get user preference", description = "사용자 선호도를 조회한다.")
    @GetMapping("/user/{userId}/preference")
    public ComparePreferenceDto findUserPreference(@PathVariable Long userId) {
        return comparePreferenceUsecase.getUserPreference(userId);
    }

    /**
     * 매칭 선호도 조회
     * @param matchingId (ID)
     * @return ComparePreferenceDto
     */
    @Operation(summary = "Get matching preference", description = "매칭 선호도를 조회한다.")
    @GetMapping("/matching/{matchingId}/preference")
    public ComparePreferenceDto findMatchingPreference(@PathVariable Long matchingId) {
        return comparePreferenceUsecase.getMatchingPreference(matchingId);
    }

    /**
     * 사용자 선호도 변경
     * @param userId (ID)
     * @param savePreferenceDto (데이터)
     * @return SavePreferenceDto
     */
    @Operation(summary = "Update user preference", description = "사용자 선호도를 변경한다.")
    @PatchMapping("/user/{userId}/preference")
    public SavePreferenceDto updateUserPreference(@PathVariable Long userId, @RequestBody SavePreferenceDto savePreferenceDto) {
        return userPreferenceUsecase.updateUserPreference(userId, savePreferenceDto);
    }

    /**
     * 매칭 선호도 변경
     * @param matchingId (ID)
     * @param savePreferenceDto (데이터)
     * @return SavePreferenceDto
     */
    @Operation(summary = "Update matching preference", description = "매칭 선호도를 변경한다.")
    @PatchMapping("/matching/{matchingId}/preference")
    public SavePreferenceDto updateMatchingPreference(@PathVariable Long matchingId, @RequestBody SavePreferenceDto savePreferenceDto) {
        return  matchingPreferenceUsecase.updateMatchingPreference(matchingId, savePreferenceDto);
    }
}