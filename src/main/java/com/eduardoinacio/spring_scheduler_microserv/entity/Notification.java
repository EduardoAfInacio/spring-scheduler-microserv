package com.eduardoinacio.spring_scheduler_microserv.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long notificationId;

    private LocalDateTime sendDate;

    private String message;

    private String destination;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    public Notification(LocalDateTime sendDate, String message, String destination, Channel channel, Status status) {
        this.sendDate = sendDate;
        this.message = message;
        this.destination = destination;
        this.channel = channel;
        this.status = status;
    }
}
