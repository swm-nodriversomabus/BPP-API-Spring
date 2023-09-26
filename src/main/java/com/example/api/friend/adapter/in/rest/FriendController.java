package com.example.api.friend.adapter.in.rest;

import com.example.api.friend.application.port.in.AddFriendUsecase;
import com.example.api.friend.application.port.in.DeleteFriendUsecase;
import com.example.api.friend.application.port.in.FindFriendUsecase;
import com.example.api.friend.dto.FriendDto;
import com.example.api.user.dto.FindUserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Friend", description = "Friend API")
public class FriendController {
    private final AddFriendUsecase addFriendUsecase;
    private final FindFriendUsecase findFriendUsecase;
    private final DeleteFriendUsecase deleteFriendUsecase;

    /**
     * 친구 추가
     * @param friendDto (Data)
     * @return FriendDto
     */
    @Operation(summary = "Add friend", description = "새로운 친구를 추가한다.")
    @PostMapping("/friend")
    public FriendDto addFriend(@RequestBody FriendDto friendDto) {
        return addFriendUsecase.addFriend(friendDto);
    }

    /**
     * 친구 목록 조회
     * @param userId (ID)
     * @return List<UserDto>
     */
    @Operation(summary = "Get friend list", description = "사용자의 친구 목록을 조회한다.")
    @GetMapping("/user/{userId}/friend")
    public List<FindUserDto> getFriendList(@PathVariable Long userId) {
        return findFriendUsecase.getFriendList(userId);
    }

    /**
     * 친구 삭제
     * @param friendDto (Data)
     */
    @Operation(summary = "Delete friend", description = "친구를 삭제한다.")
    @DeleteMapping("/friend")
    public void deleteFriend(FriendDto friendDto) {
        deleteFriendUsecase.deleteFriend(friendDto);
    }
}