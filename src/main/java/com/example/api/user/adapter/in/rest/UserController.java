package com.example.api.user.adapter.in.rest;

import com.example.api.matching.dto.MatchingDto;
import com.example.api.user.application.port.in.DeleteUserUsecase;
import com.example.api.user.application.port.in.FindUserUsecase;
import com.example.api.user.application.port.in.RecommendedMatchingUsecase;
import com.example.api.user.application.port.in.SaveUserUsecase;
import com.example.api.user.dto.CreaeUserDto;
import com.example.api.user.dto.UserDto;
import com.example.api.user.validator.CreateGenderValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Tag(name = "User", description = "User API")
public class UserController {
    private final SaveUserUsecase saveUserUsecase;
    private final FindUserUsecase findUserUsecase;
    private final DeleteUserUsecase deleteUserUsecase;
    private final RecommendedMatchingUsecase recommendedMatchingUsecase;
    private final CreateGenderValidator createGenderValidator; // enum validator

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(createGenderValidator);
    }

    /**
     * 
     * @param userDto
     * @return UserDto
     */
    @Operation(summary = "Create user", description = "새로운 사용자를 추가한다.")
    @PostMapping("/user")
    public void createUser(@RequestBody @Valid CreaeUserDto userDto) {
        saveUserUsecase.createUser(userDto);
    }

    /**
     * 전체 사용자 조회
     * @return List<UserDto>
     */
    @Operation(summary = "Get all users", description = "모든 사용자 목록을 조회한다.")
    @GetMapping("/user")
    public List<UserDto> getAll() {
        return findUserUsecase.getAll();
    }

    /**
     * ID가 userId인 사용자 조회
     * @param userId
     * @return Optional<UserDto>
     */
    @Operation(summary = "Get user", description = "ID가 userId인 사용자를 조회한다.")
    @GetMapping("/user/{userId}")
    public Optional<UserDto> getUserById(@PathVariable Long userId) {
        return findUserUsecase.getUserById(userId);
    }

    /**
     * ID가 userId인 사용자의 추천 매칭 리스트 조회
     * @param userId
     * @return List<MatchingDto>
     */
    @Operation(summary = "Get recommended matching list of a user", description = "사용자의 추천 매칭 리스트를 불러온다.")
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
    @Operation(summary = "Update user information", description = "사용자 정보를 변경한다.")
    @PatchMapping("/user/{userId}")
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        return saveUserUsecase.updateUser(userId, userDto);
    }

    /**
     * 전체 사용자 삭제 (비상시 외 사용 금지)
     */
    @Operation(summary = "Delete all users", description = "모든 사용자를 삭제한다.")
    @DeleteMapping("/user")
    public void deleteAll() {
        deleteUserUsecase.deleteAll();
    }

    /**
     * ID가 userId인 사용자 삭제
     * @param userId
     */
    @Operation(summary = "Delete user", description = "ID가 userId인 사용자를 삭제한다.")
    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        deleteUserUsecase.deleteUser(userId);
    }
}