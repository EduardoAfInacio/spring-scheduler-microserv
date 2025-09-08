package com.eduardoinacio.spring_scheduler_microserv.controller.dto;

import com.eduardoinacio.spring_scheduler_microserv.entity.Enum.ChannelValues;
import com.eduardoinacio.spring_scheduler_microserv.entity.Enum.StatusValues;
import com.eduardoinacio.spring_scheduler_microserv.entity.Notification;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import java.time.LocalDateTime;

public record ScheduleNotificationDTO(@NotNull(message = "Datetime cannot be null")
                                      @Size(min = 3, max = 255, message = "Datetime must be between 3 and 255 characters")
                                      LocalDateTime dateTime,
                                      @NotNull(message = "Destination cannot be null")
                                      @Size(min = 3, max = 255, message = "Destination must be between 3 and 255 characters")
                                      String destination,
                                      @NotNull(message = "Message cannot be null")
                                      @Size(min = 1, max = 1000, message = "Message must be between 1 and 1000 characters")
                                      String message,
                                      @NotNull(message = "Channel cannot be null")
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
