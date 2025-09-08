package com.eduardoinacio.spring_scheduler_microserv.repository;

import com.eduardoinacio.spring_scheduler_microserv.entity.Notification;
import com.eduardoinacio.spring_scheduler_microserv.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByStatusInAndSendDateBefore(List<Status> statuses, LocalDateTime sendDateBefore);
}
