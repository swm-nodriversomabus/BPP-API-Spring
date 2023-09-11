package com.example.api.notification.adapter.in.rest;

import com.example.api.notification.application.port.in.FindNotificationUsecase;
import com.example.api.notification.application.port.in.SaveNotificationUsecase;
import com.example.api.notification.application.port.in.UserNotificationUsecase;
import com.example.api.notification.dto.NotificationDto;
import com.example.api.notification.dto.UserNotificationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@EnableWebMvc
@Tag(name = "Notification", description = "Notification API")
public class NotificationController {
    private final SaveNotificationUsecase saveNotificationUsecase;
    private final FindNotificationUsecase findNotificationUsecase;
    private final UserNotificationUsecase userNotificationUsecase;

    /**
     * 새 알림 생성
     * @param notificationDto (데이터)
     * @return NotificationDto
     */
    @Operation(summary = "Create Notification", description = "새로운 알림을 생성한다.")
    @PostMapping("/notification")
    public NotificationDto createNotification(@RequestBody NotificationDto notificationDto) {
        return saveNotificationUsecase.createNotification(notificationDto);
    }

    /**
     * 알림 - 사용자 연결
     * @param userNotificationDto (데이터)
     * @return UserNotificationDto
     */
    @Operation(summary = "Connect user and notification", description = "알림에 사용자를 할당한다.")
    @PostMapping("/usernotification")
    public UserNotificationDto createUserNotification(@RequestBody UserNotificationDto userNotificationDto) {
        return userNotificationUsecase.createUserNotification(userNotificationDto);
    }

    /**
     * 알림 상세정보 조회
     * @param notificationId (ID)
     * @return NotificationDto
     */
    @Operation(summary = "Get notification", description = "알림의 상세 정보를 조회한다.")
    @GetMapping("/notification/{notificationId}")
    public Optional<NotificationDto> getNotificationById(@PathVariable Long notificationId) {
        return findNotificationUsecase.getNotificationById(notificationId);
    }

    /**
     * 사용자의 알림 리스트 조회
     * @param userId (ID)
     * @return List<NotificationDto>
     */
    @Operation(summary = "Ger notification list of user", description = "사용자의 알림 리스트를 조회한다.")
    @GetMapping("/user/{userId}/notification")
    public List<NotificationDto> getUserNotificationList(@PathVariable Long userId) {
        return findNotificationUsecase.getUserNotificationList(userId);
    }

    /**
     * 알림의 읽음 상태 변경
     * @param userNotificationDto (Data)
     */
    @Operation(summary = "Toggle read state", description = "알림의 읽음 여부를 변경한다.")
    @PatchMapping("/usernotification")
    public void toggleReadState(@RequestBody UserNotificationDto userNotificationDto) {
        userNotificationUsecase.toggleReadState(userNotificationDto);
    }
}