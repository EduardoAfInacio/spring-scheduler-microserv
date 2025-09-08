package com.eduardoinacio.spring_scheduler_microserv.repository;

import com.eduardoinacio.spring_scheduler_microserv.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
}
