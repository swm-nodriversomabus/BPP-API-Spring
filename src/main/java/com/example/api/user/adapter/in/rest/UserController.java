package com.example.api.user.adapter.in.rest;

import com.example.api.auth.domain.SecurityUser;
import com.example.api.common.exception.CustomException;
import com.example.api.common.type.ApplicationStateEnum;
import com.example.api.common.type.ErrorCodeEnum;
import com.example.api.common.utils.AuthenticationUtils;
import com.example.api.matching.application.port.in.FindMatchingUsecase;
import com.example.api.matching.application.port.in.MatchingApplicationUsecase;
import com.example.api.matching.dto.FindMatchingDto;
import com.example.api.s3.application.port.in.FileDisplayUsecase;
import com.example.api.s3.application.port.in.FileUploadUsecase;
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
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

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
    private final ProfileImageUsecase profileImageUsecase;
    private final FileUploadUsecase fileUploadUsecase;
    private final FileDisplayUsecase fileDisplayUsecase;
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
        UUID userId = saveUserUsecase.createUser(userDto);
        profileImageUsecase.initializeProfileImage(userId);
    }

    /**
     * 프로필 사진 등록/수정
     * @param file (이미지)
     */
    @Operation(summary = "Save profile image", description = "프로필 사진을 등록한다.")
    @PostMapping("/user/image")
    public void saveProfileImage(@RequestBody MultipartFile file) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("UserController::saveProfileImage: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        String filename = fileUploadUsecase.upload(file);
        profileImageUsecase.saveProfileImage(securityUser.getUserId(), filename);
    }

    /**
     * 전체 사용자 조회
     * @return User list
     */
    @Operation(summary = "Get all users", description = "모든 사용자 목록을 조회한다.")
    @GetMapping("/user/all")
    public List<FindUserDto> getAll() {
        return findUserUsecase.getAll();
    }

    /**
     * 개별 사용자 조회
     * @return User data
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
     * @return Recommended matching list
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
     * @return Own matching list
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
     * @return Pending matching list
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
     * @return Approved matching list
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
     * 사용자 프로필 이미지 조히
     * @return User profile image
     */
    @Operation(summary = "Get user profile image", description = "사용자의 프로필 이미지를 불러온다.")
    @GetMapping("/user/image")
    public ResponseEntity<InputStreamResource> getProfileImage() {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("UserController::getUserProfileImage: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        String filename = profileImageUsecase.getProfileImageName(securityUser.getUserId());
        if (filename == null) {
            return null;
        }
        InputStream fileStream = fileDisplayUsecase.display(filename);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(new InputStreamResource(fileStream));
    }

    /**
     * 현재 로그인한 사용자 정보 수정
     * @param userDto (데이터)
     * @return User data
     */
    @Operation(summary = "Update user information", description = "사용자 정보를 변경한다.")
    @PutMapping("/user")
    public FindUserDto updateUser(@RequestBody UpdateUserDto userDto) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("UserController::updateUser: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        return saveUserUsecase.updateUser(securityUser.getUserId(), userDto);
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
    
    @Operation(summary = "Delete user profile image", description = "사용자의 프로필 사진을 삭제한다.")
    @DeleteMapping("/user/image")
    public void deleteProfileImage() {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("UserController::deleteProfileImage: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        profileImageUsecase.initializeProfileImage(securityUser.getUserId());
    }
}