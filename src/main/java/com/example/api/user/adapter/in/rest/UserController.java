package com.example.api.user.adapter.in.rest;

import com.example.api.auth.domain.SecurityUser;
import com.example.api.common.exception.CustomException;
import com.example.api.common.type.ApplicationStateEnum;
import com.example.api.common.type.ErrorCodeEnum;
import com.example.api.common.utils.AuthenticationUtils;
import com.example.api.matching.application.port.in.FindMatchingUsecase;
import com.example.api.matching.application.port.in.MatchingApplicationUsecase;
import com.example.api.matching.dto.FindMatchingDto;
import com.example.api.user.application.port.in.*;
import com.example.api.user.dto.CreateUserDto;
import com.example.api.user.dto.FindUserDto;
import com.example.api.user.dto.UpdateUserDto;
import com.example.api.user.type.UserRoleEnum;
import com.example.api.user.validator.CreateGenderValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@RestController
@RequiredArgsConstructor
@EnableWebMvc
@Validated
@Slf4j
@Tag(name = "User", description = "User API")
public class UserController {
    private final SaveUserUsecase saveUserUsecase;
    private final FindUserUsecase findUserUsecase;
    private final DeleteUserUsecase deleteUserUsecase;
    private final FindMatchingUsecase findMatchingUsecase;
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
        saveUserUsecase.createUser(userDto);
    }

    /**
     * 전체 사용자 조회
     * @return List<FindUserDto>
     */
    @Operation(summary = "Get all users", description = "모든 사용자 목록을 조회한다.")
    @GetMapping("/user/all")
    public List<FindUserDto> getAll() {
        return findUserUsecase.getAll();
    }

    /**
     * 개별 사용자 조회
     * @return FindUserDto
     */
    @Operation(summary = "Get user", description = "사용자를 조회한다.")
    @GetMapping("/user")
    public FindUserDto getUser() {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("UserController::getUser: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        return findUserUsecase.getUser(securityUser.getUserId());
    }

    /**
     * ID가 userId인 사용자의 추천 매칭 목록 조회
     * @return List<FindMatchingDto>
     */
    @Operation(summary = "Get recommended matching list of a user", description = "사용자의 추천 매칭 목록을 조회한다.")
    @GetMapping("/user/recommendedmatching")
    public List<FindMatchingDto> getRecommendedMatchingList() {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("UserController::getRecommendedMatchingList: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        return findMatchingUsecase.getRecommendedMatchingList(securityUser.getUserId());
    }

    /**
     * 사용자가 작성한 매칭 목록 조회
     * @return List<FindMatchingDto>
     */
    @Operation(summary = "Get own matching list of user", description = "사용자가 작성한 매칭 목록을 조회한다.")
    @GetMapping("/user/matching/own")
    public List<FindMatchingDto> getOwnMatchingList() {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("UserController::getOwnMatchingList: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        return findMatchingUsecase.getMatchingByWriterId(securityUser.getUserId());
    }

    /**
     * 사용자가 대기 중인 매칭 목록 조회
     * @return List<FindMatchingDto>
     */
    @Operation(summary = "Get pending matching list of user", description = "사용자가 대기 중인 매칭 목록을 조회한다.")
    @GetMapping("/user/pending")
    public List<FindMatchingDto> getPendingMatchingList() {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("UserController::getPendingMatchingList: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        return matchingApplicationUsecase.getByUserIdIsAndStateEquals(securityUser.getUserId(), ApplicationStateEnum.Pending);
    }

    /**
     * 사용자가 참가한 매칭 목록 조회
     * @return List<FindMatchingDto>
     */
    @Operation(summary = "Get approved matching list of user", description = "사용자가 참가한 매칭 목록을 조회한다.")
    @GetMapping("/user/approved")
    public List<FindMatchingDto> getApprovedMatchingList() {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("UserController::getApprovedMatchingList: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        return matchingApplicationUsecase.getByUserIdIsAndStateEquals(securityUser.getUserId(), ApplicationStateEnum.Approved);
    }

    /**
     * 현재 로그인한 사용자 정보 수정
     * @param userDto (데이터)
     * @return FindUserDto
     */
    @Operation(summary = "Update user information", description = "사용자 정보를 변경한다.")
    @PatchMapping("/user")
    public FindUserDto updateUser(@Valid @RequestBody UpdateUserDto userDto) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("UserController::updateUser: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        return saveUserUsecase.updateUser(userDto);
    }

    /**
     * 전체 사용자 삭제 (비상시 외 사용 금지)
     */
    @Operation(summary = "Delete all users", description = "모든 사용자를 삭제한다.")
    @DeleteMapping("/user/all")
    public void deleteAll() {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("UserController::deleteAll: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        if (!(findUserUsecase.getUser(securityUser.getUserId()).getRole().equals(UserRoleEnum.Admin))) {
            log.error("UserController::deleteAll: Admin authority is needed");
            throw new CustomException(ErrorCodeEnum.INVALID_PERMISSION);
        }
        deleteUserUsecase.deleteAll();
    }

    /**
     * 사용자 삭제 (회원탈퇴 시 사용)
     */
    @Operation(summary = "Delete user", description = "사용자를 삭제한다.")
    @DeleteMapping("/user")
    public void deleteUser() {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("UserController::deleteUser: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        if (!(findUserUsecase.getUser(securityUser.getUserId()).getRole().equals(UserRoleEnum.Admin))) {
            log.error("UserController::deleteUser: Admin authority is needed");
            throw new CustomException(ErrorCodeEnum.INVALID_PERMISSION);
        }
        deleteUserUsecase.deleteUser(securityUser.getUserId());
    }
}