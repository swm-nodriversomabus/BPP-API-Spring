package com.example.api.chatroom.service;

import com.example.api.auth.domain.SecurityUser;
import com.example.api.chat.config.KafkaConsumerConfig;
import com.example.api.chatroom.application.port.in.CreateChatRoomUsecase;
import com.example.api.chatroom.application.port.in.FindChatRomListUsecase;
import com.example.api.chatroom.application.port.out.CreateChatRoomPort;
import com.example.api.chatroom.application.port.out.FindChatRoomListPort;
import com.example.api.chatroom.domain.ChatRoom;
import com.example.api.chatroom.dto.CreateChatRoomDto;
import com.example.api.common.utils.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatRoomService implements CreateChatRoomUsecase, FindChatRomListUsecase {
    private final CreateChatRoomPort createChatRoomPort;
    private final FindChatRoomListPort findChatRoomListPort;
    private final KafkaConsumerConfig kafkaConsumerConfig;
    private final KafkaAdmin kafkaAdmin;

    @Override
    @Transactional
    public ChatRoom createRoom(CreateChatRoomDto createChatRoomDto) {
        ChatRoom chatRoom = ChatRoom.builder()
                .masterId(createChatRoomDto.getMasterId())
                .chatroomName(createChatRoomDto.getChatroomName())
                .type(createChatRoomDto.getType())
                .isActive(createChatRoomDto.getIsActive())
                .build();
        chatRoom = createChatRoomPort.createChatRoom(chatRoom);
        createTopic(chatRoom.getChatroomId().toString());
        kafkaConsumerConfig.createListenerContainerForRoom(chatRoom.getChatroomId().toString());
        return chatRoom;

    }

    @Override
    public List<ChatRoom> chatRoomList(Pageable pageable) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("ChatRoomService::charRoomList: Authentication is needed.");
            return new ArrayList<>();
        }
        return findChatRoomListPort.chatRoomList(pageable, securityUser.getUserId());
    }

    public void createTopic(String chatroomId){
        AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties());
        NewTopic newTopic = new NewTopic(chatroomId, 4, (short) 1);
        adminClient.createTopics(Collections.singleton(newTopic));
        adminClient.close();
    }
}