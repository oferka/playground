package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LogGenerator {

    private final MessageGenerator messageGenerator;

    public LogGenerator(MessageGenerator messageGenerator) {
        this.messageGenerator = messageGenerator;
    }

    @Scheduled(fixedDelay=1000)
    public void generateMessage() {
        log.info(messageGenerator.generate());
    }
}
