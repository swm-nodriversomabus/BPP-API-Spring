package com.example.api.matching.adapter.in.rest;

import com.example.api.auth.domain.SecurityUser;
import com.example.api.common.exception.CustomException;
import com.example.api.common.type.ApplicationStateEnum;
import com.example.api.common.type.ErrorCodeEnum;
import com.example.api.common.utils.AuthenticationUtils;
import com.example.api.matching.adapter.out.persistence.MatchingMapperInterface;
import com.example.api.matching.application.port.in.*;
import com.example.api.matching.domain.MatchingApplication;
import com.example.api.matching.dto.*;
import com.example.api.matching.type.MatchingTypeEnum;
import com.example.api.user.application.port.in.FindUserUsecase;
import com.example.api.user.dto.FindUserInfoDto;
import com.example.api.user.dto.UserAuthorityCheckDto;
import com.example.api.user.type.UserRoleEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@RestController
@RequiredArgsConstructor
@EnableWebMvc
@Slf4j
@Tag(name = "Matching", description = "Matching API")
public class MatchingController {
    private final FindUserUsecase findUserUsecase;
    private final SaveMatchingUsecase saveMatchingUsecase;
    private final FindMatchingUsecase findMatchingUsecase;
    private final DeleteMatchingUsecase deleteMatchingUsecase;
    private final MatchingApplicationUsecase matchingApplicationUsecase;
    private final AccommodationUsecase accommodationUsecase;
    private final LikeUsecase likeUsecase;
    private final MatchingMapperInterface matchingMapper;

    /**
     * 새 매칭 생성
     * @param saveMatchingDto (데이터)
     * @return matching data
     */
    @Operation(summary = "Create matching", description = "새로운 매칭을 생성한다.")
    @PostMapping("/matching")
    public FindMatchingDto createMatching(@Valid @RequestBody SaveMatchingDto saveMatchingDto, @Valid @RequestBody AccommodationDto accommodationDto) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("MatchingController::createMatching: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        FindMatchingDto findMatchingDto = saveMatchingUsecase.createMatching(securityUser.getUserId(), saveMatchingDto);
        
        SaveMatchingApplicationDto saveMatchingApplicationDto = SaveMatchingApplicationDto.builder()
                .userId(securityUser.getUserId())
                .matchingId(findMatchingDto.getMatchingId())
                .state(ApplicationStateEnum.Owner)
                .isActive(true)
                .build();
        matchingApplicationUsecase.createMatchingApplicationData(securityUser.getUserId(), saveMatchingApplicationDto);
        
        if (saveMatchingDto.getType().equals(MatchingTypeEnum.Accommodation)) {
            if (accommodationDto == null) {
                log.error("MatchingController::createMatching: Accommodation data not found");
                throw new CustomException(ErrorCodeEnum.ACCOMMODATION_NOT_FOUND);
            }
            accommodationUsecase.createAccommodation(findMatchingDto.getMatchingId(), accommodationDto);
        }
        
        return findMatchingDto;
    }

    /**
     * 새 매칭 신청 생성
     * @param matchingApplicationDto (데이터)
     */
    @Operation(summary = "Create matching application", description = "새로운 매칭 신청을 생성한다.")
    @PostMapping("/matching/application")
    public void createMatchingApplication(@Valid @RequestBody SaveMatchingApplicationDto matchingApplicationDto) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("MatchingController::createMatchingApplication: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        if (securityUser.getUserId().equals(matchingApplicationDto.getUserId())) {
            log.error("MatchingController::createMatchingApplication: WriterId equals to applicantId");
            throw new CustomException(ErrorCodeEnum.INVALID_PERMISSION);
        }
        FindMatchingDto matchingDto = findMatchingUsecase.getMatchingById(matchingApplicationDto.getMatchingId());
        if (matchingDto == null) {
            log.error("MatchingController::createMatchingApplication: No such matching");
            throw new CustomException(ErrorCodeEnum.MATCHING_NOT_FOUND);
        }
        
        MatchingApplication matchingApplication = matchingApplicationUsecase.createMatchingApplicationData(securityUser.getUserId(), matchingApplicationDto);
        //ChatRoom chatRoom = createChatRoomUsecase.createMatchingChatRoom(matchingApplication);
        //return addMemberChatRoomUsecase.setupMatchingChatRoom(matchingApplication, chatRoom);
    }
    
    /**
     * 전체 매칭 목록 조회
     * @return matching list
     */
    @Operation(summary = "Get all matching", description = "모든 매칭 목록을 조회한다.")
    @GetMapping ("/matching")
    public List<FindMatchingDto> getAll() {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("MatchingController::getAll: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        return findMatchingUsecase.getAll();
    }
    
    @Operation(summary = "Get all dining matching", description = "모든 식사 매칭 목록을 조회한다.")
    @GetMapping("/diningmatching")
    public List<FindMatchingDto> getDiningMatchingList() {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("MatchingController::getDiningMatchingList: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        return findMatchingUsecase.getDiningMatchingList();
    }

    /**
     * 숙소 매칭 목록 조회
     * @return accommodation matching list
     */
    @Operation(summary = "Get all accommodation matching", description = "모든 숙소 매칭 목록을 조회한다.")
    @GetMapping("/accommodationmatching")
    public List<AccommodationMatchingDto> getAccommodationMatchingList() {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("MatchingController::getAccommodationMatchingList: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        return accommodationUsecase.getAccommodationMatchingList();
    }
    
    /**
     * ID가 matchingId인 매칭 조회 (유형 범용)
     * @param matchingId (데이터)
     * @return matching data
     */
    @Operation(summary = "Get matching", description = "ID가 matchingId인 매칭을 조회한다.")
    @GetMapping("/matching/{matchingId}")
    public AccommodationMatchingDto getMatchingById(@PathVariable Long matchingId) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("MatchingController::getMatchingById: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        AccommodationMatchingDto matchingDto = matchingMapper.toDto(findMatchingUsecase.getMatchingById(matchingId));
        if (matchingDto.getType().equals(MatchingTypeEnum.Accommodation)) {
            AccommodationDto accommodationDto = accommodationUsecase.getAccommodation(matchingId);
            matchingDto.setPrice(accommodationDto.getPrice());
            matchingDto.setPricePerOne(accommodationDto.getPrice() / Math.max(matchingDto.getCurrentMember(), 1));
            matchingDto.setRoom(accommodationDto.getRoom());
        }
        return matchingDto;
    }

    /**
     * ID가 matchingId인 매칭의 대기자 목록 조회
     * @param matchingId (ID)
     * @return pending user list
     */
    @Operation(summary = "Get pending user list of matching", description = "매칭의 대기자 목록을 조회한다.")
    @GetMapping("/matching/{matchingId}/pending")
    public List<FindUserInfoDto> getPendingUserList(@PathVariable Long matchingId) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("MatchingController::getPendingUserList: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        return matchingApplicationUsecase.getByMatchingIdIsAndStateEquals(matchingId, ApplicationStateEnum.Pending);
    }

    /**
     * ID가 matchingId인 매칭의 참가자 목록 조회
     * @param matchingId (ID)
     * @return participant list
     */
    @Operation(summary = "Get approved user list of matching", description = "매칭의 참가자 목록을 조회한다.")
    @GetMapping("/matching/{matchingId}/approved")
    public List<FindUserInfoDto> getApprovedUserList(@PathVariable Long matchingId) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("MatchingController::getApprovedUserList: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        return matchingApplicationUsecase.getByMatchingIdIsAndStateEquals(matchingId, ApplicationStateEnum.Approved);
    }

    /**
     * Id가 matchingId인 매칭의 사용자 상태 조회
     * @param matchingId (ID)
     * @return user status
     */
    @Operation(summary = "Get user status of matching", description = "매칭의 사용자 상태를 조회한다.")
    @GetMapping("/matching/{matchingId}/status")
    public String getUserStatus(@PathVariable Long matchingId) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("MatchingController::getUserStatus: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        return matchingApplicationUsecase.getUserStatus(securityUser.getUserId(), matchingId);
    }

    /**
     * ID가 matchingId인 매칭의 좋아요 수 조회
     * @param matchingId (ID)
     * @return like count
     */
    @Operation(summary = "Get like count of a matching", description = "매칭글의 좋아요 수를 반환한다.")
    @GetMapping("/matching/{matchingId}/like")
    public int getLikeCount(@PathVariable Long matchingId) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("MatchingController::getLikeCount: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        return likeUsecase.getLikeCount(matchingId);
    }
    
    @Operation(summary = "Get recommended accommodation list", description = "추천 숙소 리스트를 반환한다.")
    @GetMapping("/accommodation/recommended")
    public List<AccommodationDto> getRecommendedAccommodationList() {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("MatchingController::getRecommendedAccommodationList: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        return accommodationUsecase.getRecommendedAccommodationList(securityUser.getUserId());
    }

    /**
     * ID가 matchingId인 매칭 정보 수정
     * @param matchingId (ID)
     * @param matchingDto (데이터)
     * @return matching data
     */
    @Operation(summary = "Update matching", description = "매칭 정보를 수정한다.")
    @PutMapping("/matching/{matchingId}")
    public FindMatchingDto updateMatching(@PathVariable Long matchingId, @RequestBody SaveMatchingDto matchingDto) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("MatchingController::updateMatching: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        return saveMatchingUsecase.updateMatching(matchingId, matchingDto);
    }
    
    @Operation(summary = "Update accommodation", description = "숙소 정보를 수정한다.")
    @PutMapping("/matching/{matchingId}/accommodation")
    public void updateAccommodation(@PathVariable Long matchingId, @RequestBody AccommodationDto accommodationDto) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("MatchingController::updateAccommodation: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        accommodationUsecase.updateAccommodation(matchingId, accommodationDto);
    }

    /**
     * 매칭의 좋아요 토글
     * @param likeDto (데이터)
     */
    @Operation(summary = "Toggle like state", description = "사용자가 매칭에 좋아요를 누른 상태를 변경한다.")
    @PutMapping("/matching/like")
    public void toggleLike(@RequestBody LikeDto likeDto) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("MatchingController::toggleLike: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        likeUsecase.toggleLike(likeDto);
    }

    /**
     * 매칭 신청 수락/거절
     * @param matchingApplicationDto (데이터)
     */
    @Operation(summary = "Process matching application", description = "매칭 신청을 처리한다.")
    @PutMapping("/matching/application")
    public void processMatchingApplication(@RequestBody SaveMatchingApplicationDto matchingApplicationDto) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("MatchingController::processMatchingApplication: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        matchingApplicationUsecase.processMatchingApplication(matchingApplicationDto);
    }

    /**
     * 전체 매칭 삭제 (비상시 외 사용 금지)
     */
    @Operation(summary = "Delete all matching", description = "모든 매칭을 삭제한다.")
    @DeleteMapping("/matching")
    public void deleteAll() {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("MatchingController::deleteAll: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        if (!(findUserUsecase.getAuthorityUser(securityUser.getUserId()).getRole().equals(UserRoleEnum.Admin))) {
            log.error("MatchingController::deleteAll: Admin authority is needed");
            throw new CustomException(ErrorCodeEnum.INVALID_PERMISSION);
        }
        deleteMatchingUsecase.deleteAll();
    }

    /**
     * ID가 matchingId인 매칭 삭제
     * @param matchingId (ID)
     */
    @Operation(summary = "Delete matching", description = "ID가 matchingId인 매칭을 삭제한다.")
    @DeleteMapping("/matching/{matchingId}")
    public void deleteMatching(@PathVariable Long matchingId) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("MatchingController::deleteMatching: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        UserAuthorityCheckDto userDto = findUserUsecase.getAuthorityUser(securityUser.getUserId());
        FindMatchingDto matchingDto = findMatchingUsecase.getMatchingById(matchingId);
        if (matchingDto == null) {
            log.error("MatchingController::deleteMatching: No such matching");
            throw new CustomException(ErrorCodeEnum.MATCHING_NOT_FOUND);
        }
        if (!(userDto.getRole().equals(UserRoleEnum.Admin)) && !(userDto.getUserId().equals(matchingDto.getWriterId()))) {
            log.error("MatchingController::deleteMatching: Admin or owner authority is needed");
            throw new CustomException(ErrorCodeEnum.INVALID_PERMISSION);
        }
        deleteMatchingUsecase.deleteMatching(matchingId);
    }
    
    @Operation(summary = "Reset accommodation", description = "숙소 정보를 초기화한다.")
    @DeleteMapping("/matching/{matchingId}/accommodation")
    public void deleteAccommodation(@PathVariable Long matchingId) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("MatchingController::deleteAccommodation: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        FindMatchingDto matchingDto = findMatchingUsecase.getMatchingById(matchingId);
        if (matchingDto == null) {
            log.error("MatchingController::deleteMatching: No such matching");
            throw new CustomException(ErrorCodeEnum.MATCHING_NOT_FOUND);
        }
        accommodationUsecase.deleteAccommodation(matchingId);
    }
}