package com.eduardoinacio.spring_scheduler_microserv.config;

import com.eduardoinacio.spring_scheduler_microserv.entity.Enum.ChannelValues;
import com.eduardoinacio.spring_scheduler_microserv.entity.Enum.StatusValues;
import com.eduardoinacio.spring_scheduler_microserv.repository.ChannelRepository;
import com.eduardoinacio.spring_scheduler_microserv.repository.StatusRepository;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;

public class DataLoader implements CommandLineRunner {
    private ChannelRepository channelRepository;
    private StatusRepository statusRepository;

    public DataLoader(ChannelRepository channelRepository, StatusRepository statusRepository) {
        this.channelRepository = channelRepository;
        this.statusRepository = statusRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(ChannelValues.values())
                .map(ChannelValues::toChannel)
                .forEach(channelRepository::save);

        Arrays.stream(StatusValues.values())
                .map(StatusValues::toStatus)
                .forEach(statusRepository::save);
    }
}
