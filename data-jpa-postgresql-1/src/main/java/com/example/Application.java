package com.example;

import com.example.data.sample.SampleDataLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {

    private SampleDataLoader sampleDataLoader;

    public Application(SampleDataLoader sampleDataLoader) {
        this.sampleDataLoader = sampleDataLoader;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        sampleDataLoader.clean();
        sampleDataLoader.load();
    }
}
