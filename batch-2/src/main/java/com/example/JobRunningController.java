package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class JobRunningController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @GetMapping("/runJob")
    public @ResponseBody String runJob() {
        log.debug("Request to run job received");
        String response = "Job executed successfully";
        try {
            jobLauncher.run(job, new JobParameters());
        }
        catch (Exception e) {
            response = String.format("Failed to execute job. Error message was: %s", e.getMessage());
        }
        log.debug(String.format("Request to run job processed. Returning response: %s", response));
        return response;
    }
}
