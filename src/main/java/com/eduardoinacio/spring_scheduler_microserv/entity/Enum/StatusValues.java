package com.eduardoinacio.spring_scheduler_microserv.entity.Enum;

import com.eduardoinacio.spring_scheduler_microserv.entity.Status;

public enum StatusValues {
    PENDING(1L, "pending"),
    SUCCESS(2L, "success"),
    ERROR(3L, "error"),
    CANCELED(4L, "canceled");

    private Long id;
    private String description;

    StatusValues(Long id, String description){
        this.id = id;
        this.description = description;
    }

    public Status toStatus(){
        return new Status(id, description);
    }
}
