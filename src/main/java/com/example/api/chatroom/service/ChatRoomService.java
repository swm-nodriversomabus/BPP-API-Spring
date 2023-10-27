package com.example.api.chatroom.service;

import com.example.api.chat.config.KafkaConsumerConfig;
import com.example.api.chatroom.adapter.out.persistence.ChatRoomMapperInterface;
import com.example.api.chatroom.application.port.in.CreateChatRoomUsecase;
import com.example.api.chatroom.application.port.in.FindChatRomListUsecase;
import com.example.api.chatroom.application.port.out.CreateChatRoomPort;
import com.example.api.chatroom.application.port.out.FindChatRoomListPort;
import com.example.api.chatroom.domain.ChatRoom;
import com.example.api.chatroom.dto.CreateChatRoomDto;
import com.example.api.chatroom.type.ChatRoomEnum;
import com.example.api.matching.domain.MatchingApplication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatRoomService implements CreateChatRoomUsecase, FindChatRomListUsecase {
    private final CreateChatRoomPort createChatRoomPort;
    private final FindChatRoomListPort findChatRoomListPort;
    private final ChatRoomMapperInterface chatRoomMapper;
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

    /**
     * createMatchingApplication Step 2
     * @param matchingApplication (데이터)
     * @return ChatRoom
     */
    @Override
    @Transactional
    public ChatRoom createMatchingChatRoom(MatchingApplication matchingApplication) {
        CreateChatRoomDto createChatRoomDto = CreateChatRoomDto.builder()
                .masterId(matchingApplication.getUserId())
                .chatroomName("매칭 신청") // 이거 바꿔야 함
                .type(ChatRoomEnum.Normal)
                .isActive(true)
                .build();
        return this.createRoom(createChatRoomDto);
    }

    @Override
    public List<ChatRoom> chatRoomList(UUID userId, Pageable pageable) {
        return findChatRoomListPort.getChatRoomList(userId, pageable).stream()
                .map(chatRoomMapper::toDomain)
                .collect(Collectors.toList());
    }

    public void createTopic(String chatroomId){
        AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties());
        NewTopic newTopic = new NewTopic(chatroomId, 4, (short) 1);
        adminClient.createTopics(Collections.singleton(newTopic));
        adminClient.close();
    }
}