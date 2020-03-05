package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Slf4j
public class DefaultExecutionPauser implements ExecutionPauser {

    @Autowired
    private ExecutionPauserConfiguration executionPauserConfiguration;

    @Override
    public void pause() {
        pause(Duration.ofMillis(executionPauserConfiguration.getDefaultDelayInMillis()));
    }

    @Override
    public void pause(Duration duration) {
        if(executionPauserConfiguration.isEnabled()) {
            long delayInMillis = duration.toMillis();
            log.debug("Pause execution for {} milliseconds started", delayInMillis);
            try {
                Thread.sleep(delayInMillis);
            } catch (InterruptedException e) {
                log.error("Failed to pause execution. error message is {}", e.getMessage());
            }
            log.debug("Pause execution for {} milliseconds completed", delayInMillis);
        }
    }
}
