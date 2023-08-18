package com.example.api.chat.repository;

import com.example.api.chat.adapter.out.persistence.ChatEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ChatRepository extends JpaRepository<ChatEntity, Long> {

    // entity graph를 활용해 user랑 join
    @EntityGraph(attributePaths = {"senderId"})
    Page<ChatEntity> findAllByRoomId_ChatroomId(@Param("roomId") UUID roomId, Pageable pageable);
}
