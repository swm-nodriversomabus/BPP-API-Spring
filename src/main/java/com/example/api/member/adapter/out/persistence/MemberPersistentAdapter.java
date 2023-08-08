package com.example.api.member.adapter.out.persistence;

import com.example.api.member.application.port.out.AddMemberChaatRoomPort;
import com.example.api.member.domain.Member;
import com.example.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberPersistentAdapter implements AddMemberChaatRoomPort {
    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;

    @Override
    public void addMember(List<Member> members) {
        memberRepository.saveAll(memberMapper.fromListDomainToEntity(members));
    }
}
