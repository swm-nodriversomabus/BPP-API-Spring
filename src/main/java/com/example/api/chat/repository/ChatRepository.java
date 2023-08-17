package com.example.api.chat.repository;

import com.example.api.chat.adapter.out.persistence.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<ChatEntity, Long> {

}
