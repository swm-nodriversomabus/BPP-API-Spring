package com.example.api.member.adapter.out.persistence;

import com.example.api.member.domain.Member;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemberMapper {
    /**
     * domain -> entity
     * @param member
     * @return memberentity
     */
    public MemberEntity fromDomainToEntity(Member member){
        return MemberEntity.builder()
                .chatroomId(member.getChatroomId())
                .userId(member.getUserId())
                .outAt(member.getOutAt())
                .build();
    }

    /**
     * List로 들어오는 도메인을 반환
     * @param members
     * @return List<MemberEntity>
     */
    public List<MemberEntity> fromListDomainToEntity(List<Member> members){
        List<MemberEntity> memberEntities = new ArrayList<>();
        for(Member member: members){
            memberEntities.add(
                    MemberEntity.builder()
                            .chatroomId(member.getChatroomId())
                            .userId(member.getUserId())
                            .outAt(member.getOutAt())
                            .build()
            );
        }
        return memberEntities;
    }

    public Member fromEntityToDomain(MemberEntity memberEntity){
        return Member.builder()
                .chatroomId(memberEntity.getChatroomId())
                .userId(memberEntity.getUserId())
                .outAt(memberEntity.getOutAt())
                .build();
    }
}
