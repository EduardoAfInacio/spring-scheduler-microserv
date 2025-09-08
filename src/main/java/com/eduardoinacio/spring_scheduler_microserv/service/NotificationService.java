package com.eduardoinacio.spring_scheduler_microserv.service;

import com.eduardoinacio.spring_scheduler_microserv.controller.dto.ScheduleNotificationDTO;
import com.eduardoinacio.spring_scheduler_microserv.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void scheduleNotification(ScheduleNotificationDTO dto) {
        notificationRepository.save(dto.toNotification());
    }
}
