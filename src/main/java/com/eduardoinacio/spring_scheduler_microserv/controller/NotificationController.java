package com.eduardoinacio.spring_scheduler_microserv.controller;

import com.eduardoinacio.spring_scheduler_microserv.controller.dto.ScheduleNotificationDTO;
import com.eduardoinacio.spring_scheduler_microserv.entity.Notification;
import com.eduardoinacio.spring_scheduler_microserv.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<Void> registerNotification(@RequestBody @Valid ScheduleNotificationDTO dto) {
        notificationService.scheduleNotification(dto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{notificationId}")
    public ResponseEntity<Notification> getNotification(@PathVariable("notificationId") Long notificationId){
        Notification notification = notificationService.getNotification(notificationId);
        return ResponseEntity.ok(notification);
    }

    @PatchMapping("/{notificationId}/cancel")
    public ResponseEntity<Void> cancelNotification(@PathVariable("notificationId") Long notificationId){
        notificationService.cancelNotification(notificationId);
        return ResponseEntity.noContent().build();
    }
}
