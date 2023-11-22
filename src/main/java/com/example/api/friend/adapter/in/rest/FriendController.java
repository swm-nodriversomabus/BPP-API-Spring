package com.example.api.friend.adapter.in.rest;

import com.example.api.auth.domain.SecurityUser;
import com.example.api.common.exception.CustomException;
import com.example.api.common.type.ErrorCodeEnum;
import com.example.api.common.utils.AuthenticationUtils;
import com.example.api.friend.application.port.in.AddFriendUsecase;
import com.example.api.friend.application.port.in.DeleteFriendUsecase;
import com.example.api.friend.application.port.in.FindFriendUsecase;
import com.example.api.friend.dto.FriendDto;
import com.example.api.user.dto.FindUserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Friend", description = "Friend API")
public class FriendController {
    private final AddFriendUsecase addFriendUsecase;
    private final FindFriendUsecase findFriendUsecase;
    private final DeleteFriendUsecase deleteFriendUsecase;

    /**
     * 친구 추가
     * @param friendDto (데이터)
     * @return friend data
     */
    @Operation(summary = "Add friend", description = "새로운 친구를 추가한다.")
    @PostMapping("/friend")
    public FriendDto addFriend(@RequestBody FriendDto friendDto) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("FriendController::addFriend: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        friendDto.setUserId(securityUser.getUserId());
        return addFriendUsecase.addFriend(friendDto);
    }

    /**
     * 친구 목록 조회
     * @return friend list
     */
    @Operation(summary = "Get friend list", description = "사용자의 친구 목록을 조회한다.")
    @GetMapping("/user/friend")
    public List<FindUserDto> getFriendList() {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("FriendController::getFriendList: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        return findFriendUsecase.getFriendList(securityUser.getUserId());
    }

    /**
     * 친구 삭제
     * @param friendDto (데이터)
     */
    @Operation(summary = "Delete friend", description = "친구를 삭제한다.")
    @DeleteMapping("/friend")
    public void deleteFriend(FriendDto friendDto) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("FriendController::deleteFriend: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        deleteFriendUsecase.deleteFriend(friendDto);
    }

    @Operation(summary = "check friend", description = "친구 여부 확인")
    @GetMapping("/friend/{friendId}")
    public ResponseEntity<Boolean> findFriend(@PathVariable UUID friendId){
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("FriendController::findFriend: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        Boolean res = findFriendUsecase.findFriend(securityUser.getUserId(), friendId);
        return ResponseEntity.ok(res);

    }
}