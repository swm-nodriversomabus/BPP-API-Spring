package com.example.api.chatroom.repository;

import com.example.api.chatroom.adapter.out.persistence.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, UUID> {

}
