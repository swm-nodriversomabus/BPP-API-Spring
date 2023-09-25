package com.example.api.member.repository;

import com.example.api.member.adapter.out.persistence.MemberEntity;
import com.example.api.member.adapter.out.persistence.MemberPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, MemberPK> {

}