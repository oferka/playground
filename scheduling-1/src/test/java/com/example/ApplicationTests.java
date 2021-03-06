package com.example;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ApplicationTests {

    @Autowired
    private ScheduledTasks tasks;

    @Test
    public void contextLoads() {
        log.info("Loading context");
    }

    @Test
    public void taskShouldBeNotNull() {
        // Basic integration test that shows the context starts up properly
        assertThat(tasks).isNotNull();
    }
}
