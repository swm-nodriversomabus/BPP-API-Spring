package com.example.api.member.adapter.in.rest;

import com.example.api.member.application.port.in.AddMemberChatRoomUsecase;
import com.example.api.member.dto.AddMemberDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final AddMemberChatRoomUsecase addMemberChatRoomUsecase;

    /**
     * 방 생성 후, 유저들을 초대해 들어오는 경우
     * @param addMemberDto
     */
    @PostMapping(value="/members")
    public void addMembers(@RequestBody @Valid AddMemberDto addMemberDto){
        addMemberChatRoomUsecase.addMember(addMemberDto);
    }
}
