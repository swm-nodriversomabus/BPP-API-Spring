package com.example.api.chatroom.repository;

import com.example.api.chatroom.adapter.out.persistence.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, UUID> {
    @Query(value = "select DISTINCT c from ChatRoomEntity c join fetch c.members e where e.userId = :userid") // n+1 문제 떄문에 fetch join 사용
    List<ChatRoomEntity> findAllByUserId(@Param("userid") Long userid);
}
