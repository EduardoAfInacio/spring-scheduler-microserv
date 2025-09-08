package com.eduardoinacio.spring_scheduler_microserv.repository;

import com.eduardoinacio.spring_scheduler_microserv.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
