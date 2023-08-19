package com.example.api.user.adapter.in.rest;

import com.example.api.matching.dto.MatchingDto;
import com.example.api.user.application.port.in.DeleteUserUsecase;
import com.example.api.user.application.port.in.FindUserUsecase;
import com.example.api.user.application.port.in.RecommendedMatchingUsecase;
import com.example.api.user.application.port.in.SaveUserUsecase;
import com.example.api.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final SaveUserUsecase saveUserUsecase;
    private final FindUserUsecase findUserUsecase;
    private final DeleteUserUsecase deleteUserUsecase;
    private final RecommendedMatchingUsecase recommendedMatchingUsecase;

    /**
     * 
     * @param userDto
     * @return UserDto
     */
    @PostMapping("/user")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return saveUserUsecase.createUser(userDto);
    }

    /**
     * 전체 사용자 조회
     * @return List<UserDto>
     */
    @GetMapping("/user")
    public List<UserDto> getAll() {
        return findUserUsecase.getAll();
    }

    /**
     * ID가 userId인 사용자 조회
     * @param userId
     * @return Optional<UserDto>
     */
    @GetMapping("/user/{userId}")
    public Optional<UserDto> getUserById(@PathVariable Long userId) {
        return findUserUsecase.getUserById(userId);
    }

    /**
     * ID가 userId인 사용자의 추천 매칭 리스트 조회
     * @param userId
     * @return List<MatchingDto>
     */
    @GetMapping("/user/{userId}/recommendedmatching")
    public List<MatchingDto> getRecommendedMatchingList(@PathVariable Long userId) {
        return recommendedMatchingUsecase.getRecommendedMatchingList(userId);
    }

    /**
     * ID가 userId인 사용자 정보 수정
     * @param userId
     * @param userDto
     * @return UserDto
     */
    @PatchMapping("/user/{userId}")
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        return saveUserUsecase.updateUser(userId, userDto);
    }

    /**
     * 전체 사용자 삭제 (비상시 외 사용 금지)
     */
    @DeleteMapping("/user")
    public void deleteAll() {
        deleteUserUsecase.deleteAll();
    }

    /**
     * ID가 userId인 사용자 삭제
     * @param userId
     */
    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        deleteUserUsecase.deleteUser(userId);
    }
}