package com.eduardoinacio.spring_scheduler_microserv.service;

import com.eduardoinacio.spring_scheduler_microserv.controller.dto.ScheduleNotificationDTO;
import com.eduardoinacio.spring_scheduler_microserv.entity.Enum.StatusValues;
import com.eduardoinacio.spring_scheduler_microserv.entity.Notification;
import com.eduardoinacio.spring_scheduler_microserv.repository.NotificationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void scheduleNotification(ScheduleNotificationDTO dto) {
        notificationRepository.save(dto.toNotification());
    }

    public Notification getNotification(Long notificationId){
        Optional<Notification> notification = notificationRepository.findById(notificationId);
        if(notification.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Notification not found, searched id:" + notificationId);
        }
        return notification.get();
    }

    public void cancelNotification(Long notificationId){
        Optional<Notification> notification = notificationRepository.findById(notificationId);
        if(notification.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Notification not found, searched id:" + notificationId);
        }

        notification.get().setStatus(StatusValues.CANCELED.toStatus());
        notificationRepository.save(notification.get());
    }
}
