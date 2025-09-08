package com.eduardoinacio.spring_scheduler_microserv.controller.dto;

import com.eduardoinacio.spring_scheduler_microserv.entity.Enum.ChannelValues;
import com.eduardoinacio.spring_scheduler_microserv.entity.Enum.StatusValues;
import com.eduardoinacio.spring_scheduler_microserv.entity.Notification;

import java.time.LocalDateTime;

public record ScheduleNotificationDTO(LocalDateTime dateTime,
                                      String destination,
                                      String message,
                                      ChannelValues channel) {

    public Notification toNotification(){
        return new Notification(
                dateTime,
                message,
                destination,
                channel.toChannel(),
                StatusValues.PENDING.toStatus()
        );
    }
}
