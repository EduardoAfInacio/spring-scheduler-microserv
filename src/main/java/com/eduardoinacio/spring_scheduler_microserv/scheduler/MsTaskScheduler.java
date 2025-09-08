package com.eduardoinacio.spring_scheduler_microserv.scheduler;

import com.eduardoinacio.spring_scheduler_microserv.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class MsTaskScheduler {
    private static final Logger logger = LoggerFactory.getLogger(MsTaskScheduler.class);
    private final NotificationService notificationService;

    public MsTaskScheduler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.SECONDS)
    public void checkTasks(){
        logger.info("Checking notifications...");
        var time = LocalDateTime.now();
        notificationService.checkAndSendNotifications(time);
    }
}
