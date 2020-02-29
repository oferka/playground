package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Slf4j
public class DefaultExecutionPauser implements ExecutionPauser {

    @Override
    public void pause(Duration duration) {
        long delayInMillis = duration.toMillis();
        log.debug("Pause execution for {} milliseconds started", delayInMillis);
        try {
            Thread.sleep(delayInMillis);
        }
        catch (InterruptedException e) {
            log.error("Failed to pause execution. error message is {}", e.getMessage());
        }
        log.debug("Pause execution for {} milliseconds completed", delayInMillis);
    }
}
