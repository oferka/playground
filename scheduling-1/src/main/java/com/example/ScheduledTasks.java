package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTimeWithFixedRate() {
        log.info("Fixed rate: the time is {}", dateFormat.format(new Date()));
    }

    @Scheduled(fixedDelay = 6000)
    public void reportCurrentTimeWithFixedDelay() {
        log.info("Fixed Delay: the time is {}", dateFormat.format(new Date()));
    }

    @Scheduled(initialDelay = 1000, fixedDelay = 7000)
    public void reportCurrentTimeWithInitialAndFixedDelay() {
        log.info("Initial and fixed Delay: the time is {}", dateFormat.format(new Date()));
    }
}

