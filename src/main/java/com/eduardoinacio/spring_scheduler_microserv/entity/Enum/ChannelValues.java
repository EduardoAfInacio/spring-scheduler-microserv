package com.eduardoinacio.spring_scheduler_microserv.entity.Enum;

import com.eduardoinacio.spring_scheduler_microserv.entity.Channel;

public enum ChannelValues {
    EMAIL(1L, "email"),
    SMS(2L, "sms"),
    PUSH(3L, "push"),
    WHATSAPP(4L, "whatsapp");

    private Long id;
    private String name;

    ChannelValues(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Channel toChannel(){
        return new Channel(id, name);
    }
}
