package com.eduardoinacio.spring_scheduler_microserv.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_status")
public class Status {
    @Id
    private Long statusId;

    private String description;
}
