package com.eduardoinacio.spring_scheduler_microserv.service;

import com.eduardoinacio.spring_scheduler_microserv.controller.dto.ScheduleNotificationDTO;
import com.eduardoinacio.spring_scheduler_microserv.entity.Enum.StatusValues;
import com.eduardoinacio.spring_scheduler_microserv.entity.Notification;
import com.eduardoinacio.spring_scheduler_microserv.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public NotificationService(NotificationRepository notificationRepository, EmailService emailService) {
        this.notificationRepository = notificationRepository;
        this.emailService = emailService;
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

    public void checkAndSendNotifications(LocalDateTime dateTime){
        var listOfNotifications = notificationRepository.findByStatusInAndSendDateBefore(
                List.of(StatusValues.PENDING.toStatus(), StatusValues.ERROR.toStatus()),
                dateTime
        );

        listOfNotifications.forEach(this::sendNotification);
    }

    @Async("notificationExecutor")
    public void sendNotification(Notification notification){
        try{
            logger.info(Thread.currentThread().getName() + "- Sending notification to: " + notification.getDestination());
            emailService.sendEmail(notification.getDestination(), "Scheduler", notification.getMessage());
            notification.setStatus(StatusValues.SUCCESS.toStatus());
            notificationRepository.save(notification);
            logger.info(Thread.currentThread().getName() + "- Notification sent successfully");
        }catch (Exception e){
            logger.error(Thread.currentThread().getName() + "- Error sending notification: " + e.getMessage());
            notification.setStatus(StatusValues.ERROR.toStatus());
            notificationRepository.save(notification);
        }
    }
}
