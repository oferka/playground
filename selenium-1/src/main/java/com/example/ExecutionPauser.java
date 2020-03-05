package com.example;

import java.time.Duration;

public interface ExecutionPauser {

    void pause();

    void pause(Duration duration);
}
