package com.example.api.member.adapter.in.rest;

import com.example.api.auth.domain.SecurityUser;
import com.example.api.common.exception.CustomException;
import com.example.api.common.type.ErrorCodeEnum;
import com.example.api.common.utils.AuthenticationUtils;
import com.example.api.member.application.port.in.AddMemberChatRoomUsecase;
import com.example.api.member.dto.AddMemberDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Member", description = "Member API")
public class MemberController {
    private final AddMemberChatRoomUsecase addMemberChatRoomUsecase;

    /**
     * 방 생성 후, 유저들을 초대해 들어오는 경우
     * @param addMemberDto (데이터)
     */
    @Operation(summary = "Add members", description = "채팅방에 사용자를 초대한다.")
    @PostMapping("/members")
    public void addMembers(@Valid @RequestBody AddMemberDto addMemberDto) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("MemberController::addMembers: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        addMemberChatRoomUsecase.addMembers(addMemberDto);
    }
}