package com.example.api.user.adapter.in.rest;

import com.example.api.common.type.ApplicationStateEnum;
import com.example.api.matching.application.port.in.FindMatchingUsecase;
import com.example.api.matching.application.port.in.MatchingApplicationUsecase;
import com.example.api.matching.dto.FindMatchingDto;
import com.example.api.user.application.port.in.DeleteUserUsecase;
import com.example.api.user.application.port.in.FindUserUsecase;
import com.example.api.user.application.port.in.RecommendedMatchingUsecase;
import com.example.api.user.application.port.in.SaveUserUsecase;
import com.example.api.user.dto.CreateUserDto;
import com.example.api.user.dto.FindUserDto;
import com.example.api.user.dto.UpdateUserDto;
import com.example.api.user.validator.CreateGenderValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@EnableWebMvc
@Validated
@Tag(name = "User", description = "User API")
public class UserController {
    private final SaveUserUsecase saveUserUsecase;
    private final FindUserUsecase findUserUsecase;
    private final DeleteUserUsecase deleteUserUsecase;
    private final FindMatchingUsecase findMatchingUsecase;
    private final RecommendedMatchingUsecase recommendedMatchingUsecase;
    private final MatchingApplicationUsecase matchingApplicationUsecase;
    private final CreateGenderValidator createGenderValidator; // enum validator

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(createGenderValidator);
    }

    /**
     * 사용자 추가
     * @param userDto (데이터)
     */
    @Operation(summary = "Create user", description = "새로운 사용자를 추가한다.")
    @PostMapping("/user")
    public void createUser(@Valid @RequestBody CreateUserDto userDto, BindingResult bindingResult) {
//        if(bindingResult.hasErrors()){
//            List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
//
//        }
        saveUserUsecase.createUser(userDto);
    }

    /**
     * 전체 사용자 조회
     * @return List<FindUserDto>
     */
    @Operation(summary = "Get all users", description = "모든 사용자 목록을 조회한다.")
    @GetMapping("/user")
    public List<FindUserDto> getAll() {
        return findUserUsecase.getAll();
    }

    /**
     * ID가 userId인 사용자 조회
     * @param userId (ID)
     * @return Optional<FindUserDto>
     */
    @Operation(summary = "Get user", description = "ID가 userId인 사용자를 조회한다.")
    @GetMapping("/user/{userId}")
    public Optional<FindUserDto> getUserById(@PathVariable Long userId) {
        return findUserUsecase.getUserById(userId);
    }

    /**
     * ID가 userId인 사용자의 추천 매칭 목록 조회
     * @param userId (ID)
     * @return List<FindMatchingDto>
     */
    @Operation(summary = "Get recommended matching list of a user", description = "사용자의 추천 매칭 목록을 조회한다..")
    @GetMapping("/user/{userId}/recommendedmatching")
    public List<FindMatchingDto> getRecommendedMatchingList(@PathVariable Long userId) {
        return recommendedMatchingUsecase.getRecommendedMatchingList(userId);
    }

    /**
     * ID가 userId인 사용자가 작성한 매칭 목록 조회
     * @param userId (ID)
     * @return List<FindMatchingDto>
     */
    @Operation(summary = "Get own matching list of user", description = "사용자가 작성한 매칭 목록을 조회한다.")
    @GetMapping("/user/{userId}/matching/own")
    public List<FindMatchingDto> getOwnMatchingList(@PathVariable Long userId) {
        return findMatchingUsecase.getMatchingByWriterId(userId);
    }

    /**
     * ID가 userId인 사용자가 대기 중인 매칭 목록 조회
     * @param userId (ID)
     * @return List<FindMatchingDto>
     */
    @Operation(summary = "Get pending matching list of user", description = "사용자가 대기 중인 매칭 목록을 조회한다.")
    @GetMapping("/user/{userId}/pending")
    public List<FindMatchingDto> getPendingMatchingList(@PathVariable Long userId) {
        return matchingApplicationUsecase.getByUserIdIsAndStateEquals(userId, ApplicationStateEnum.Pending);
    }

    /**
     * ID가 userId인 사용자가 참가한 매칭 목록 조회
     * @param userId (ID)
     * @return List<FindMatchingDto>
     */
    @Operation(summary = "Get approved matching list of user", description = "사용자가 참가한 매칭 목록을 조회한다.")
    @GetMapping("/user/{userId}/approved")
    public List<FindMatchingDto> getApprovedMatchingList(@PathVariable Long userId) {
        return matchingApplicationUsecase.getByUserIdIsAndStateEquals(userId, ApplicationStateEnum.Approved);
    }

    /**
     * ID가 userId인 사용자 정보 수정
     * @param userId (ID)
     * @param userDto (데이터)
     * @return FindUserDto
     */
    @Operation(summary = "Update user information", description = "사용자 정보를 변경한다.")
    @PatchMapping("/user/{userId}")
    public FindUserDto updateUser(@PathVariable Long userId, @RequestBody UpdateUserDto userDto) {
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
     * @param userId (ID)
     */
    @Operation(summary = "Delete user", description = "ID가 userId인 사용자를 삭제한다.")
    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        deleteUserUsecase.deleteUser(userId);
    }
}