package com.example.api.matching.adapter.in.rest;

import com.example.api.chatroom.domain.ChatRoom;
import com.example.api.common.type.ApplicationStateEnum;
import com.example.api.matching.adapter.out.persistence.MatchingApplicationPK;
import com.example.api.matching.application.port.in.*;
import com.example.api.matching.domain.MatchingApplication;
import com.example.api.matching.dto.*;
import com.example.api.user.dto.FindUserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@EnableWebMvc
@Slf4j
@Tag(name = "Matching", description = "Matching API")
public class MatchingController {
    private final SaveMatchingUsecase saveMatchingUsecase;
    private final FindMatchingUsecase findMatchingUsecase;
    private final DeleteMatchingUsecase deleteMatchingUsecase;
    private final MatchingApplicationUsecase matchingApplicationUsecase;
    private final LikeUsecase likeUsecase;

    /**
     * 새 매칭 생성
     * @param matchingDto (데이터)
     * @return MatchingDto
     */
    @Operation(summary = "Create matching", description = "새로운 매칭을 생성한다.")
    @PostMapping("/matching")
    public FindMatchingDto createMatching(@RequestBody SaveMatchingDto matchingDto) {
        return saveMatchingUsecase.createMatching(matchingDto);
    }

    /**
     * 새 매칭 신청 생성
     * @param matchingApplicationDto (데이터)
     * @return ChatRoom
     */
    @Operation(summary = "Create matching application", description = "새로운 매칭 신청을 생성한다.")
    @PostMapping("/matching/application")
    public ChatRoom createMatchingApplication(@RequestBody SaveMatchingApplicationDto matchingApplicationDto) {
        if (findMatchingUsecase.getMatchingById(matchingApplicationDto.getMatchingId()).isEmpty()) {
            log.error("MatchingController::createMatchingApplication: no such matching");
            return ChatRoom.builder().build();
        }
        MatchingApplication matchingApplication = matchingApplicationUsecase.createMatchingApplicationData(matchingApplicationDto);
        ChatRoom chatRoom = matchingApplicationUsecase.createMatchingChatRoom(matchingApplication);
        return matchingApplicationUsecase.setupMatchingChatRoom(matchingApplication, chatRoom);
    }
    
    /**
     * 전체 매칭 목록 조회
     * @return List<MatchingDto>
     */
    @Operation(summary = "Get all matching", description = "모든 매칭 목록을 조회한다.")
    @GetMapping ("/matching")
    public List<FindMatchingDto> getAll() {
        return findMatchingUsecase.getAll();
    }
    
    /**
     * ID가 matchingId인 매칭 조회
     * @param matchingId (데이터)
     * @return Optional<MatchingDto>
     */
    @Operation(summary = "Get matching", description = "ID가 matchingId인 매칭을 조회한다.")
    @GetMapping("/matching/{matchingId}")
    public Optional<FindMatchingDto> getMatchingById(@PathVariable Long matchingId) {
        return findMatchingUsecase.getMatchingById(matchingId);
    }

    /**
     * ID가 matchingId인 매칭의 대기자 목록 조회
     * @param matchingId (ID)
     * @return List<UserDto>
     */
    @Operation(summary = "Get pending user list of matching", description = "매칭의 대기자 목록을 조회한다.")
    @GetMapping("/matching/{matchingId}/pending")
    public List<FindUserDto> getPendingUserList(@PathVariable Long matchingId) {
        return matchingApplicationUsecase.getByMatchingIdIsAndStateEquals(matchingId, ApplicationStateEnum.Pending);
    }

    /**
     * ID가 matchingId인 매칭의 참가자 목록 조회
     * @param matchingId (ID)
     * @return List<UserDto>
     */
    @Operation(summary = "Get approved user list of matching", description = "매칭의 참가자 목록을 조회한다.")
    @GetMapping("/matching/{matchingId}/approved")
    public List<FindUserDto> getApprovedUserList(@PathVariable Long matchingId) {
        return matchingApplicationUsecase.getByMatchingIdIsAndStateEquals(matchingId, ApplicationStateEnum.Approved);
    }
    
    @Operation(summary = "Get user status of matching", description = "매칭의 사용자 상태를 조회한다.")
    @GetMapping("/matching/{matchingId}/status")
    public String getUserStatus(@PathVariable Long matchingId) {
        return matchingApplicationUsecase.getUserStatus(matchingId);
    }

    /**
     * ID가 matchingId인 매칭의 좋아요 수 조회 (미구현)
     * @param matchingId (ID)
     * @return int
     */
    @Operation(summary = "Get like count of a matching", description = "매칭글의 좋아요 수를 반환한다.")
    @GetMapping("/matching/{matchingId}/like")
    public int getLikeCount(@PathVariable Long matchingId) {
        return likeUsecase.getLikeCount(matchingId);
    }

    /**
     * ID가 matchingId인 매칭 정보 수정
     * @param matchingId (ID)
     * @param matchingDto (데이터)
     * @return MatchingDto
     */
    @Operation(summary = "Update matching", description = "매칭 정보를 수정한다.")
    @PutMapping("/matching/{matchingId}")
    public FindMatchingDto updateMatching(@PathVariable Long matchingId, @RequestBody SaveMatchingDto matchingDto) {
        return saveMatchingUsecase.updateMatching(matchingId, matchingDto);
    }

    /**
     * 매칭의 좋아요 토글
     * @param likeDto (데이터)
     */
    @Operation(summary = "Toggle like state", description = "사용자가 매칭에 좋아요를 누른 상태를 변경한다.")
    @PutMapping("/matching/like")
    public void toggleLike(@RequestBody LikeDto likeDto) {
        likeUsecase.toggleLike(likeDto);
    }

    /**
     * 매칭 신청 수락/거절
     * @param matchingApplicationDto (데이터)
     */
    @Operation(summary = "Process matching application", description = "매칭 신청을 처리한다.")
    @PutMapping("/matching/application")
    public void processMatchingApplication(SaveMatchingApplicationDto matchingApplicationDto) {
        MatchingApplicationPK matchingApplicationPK = MatchingApplicationPK.builder()
                .userId(matchingApplicationDto.getUserId())
                .matchingId(matchingApplicationDto.getMatchingId())
                .build();
        
        
        if (matchingApplicationDto.getState() == ApplicationStateEnum.Approved) {
            matchingApplicationUsecase.approveMatchingApplication(matchingApplicationDto);
        }
        if (matchingApplicationDto.getState() == ApplicationStateEnum.Declined) {
            matchingApplicationUsecase.declineMatchingApplication(matchingApplicationDto);
        }
    }

    /**
     * 전체 매칭 삭제 (비상시 외 사용 금지)
     */
    @Operation(summary = "Delete all matching", description = "모든 매칭을 삭제한다.")
    @DeleteMapping("/matching")
    public void deleteAll() {
        deleteMatchingUsecase.deleteAll();
    }

    /**
     * ID가 matchingId인 매칭 삭제
     * @param matchingId (ID)
     */
    @Operation(summary = "Delete matching", description = "ID가 matchingId인 매칭을 삭제한다.")
    @DeleteMapping("/matching/{matchingId}")
    public void deleteMatching(@PathVariable Long matchingId) {
        deleteMatchingUsecase.deleteMatching(matchingId);
    }
}