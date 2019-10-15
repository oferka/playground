package com.example;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static com.example.Jobs.CHUNKS;
import static com.example.Jobs.TASKLETS;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        JobLauncher jobLauncher = (JobLauncher) ctx.getBean("jobLauncher");
        Job taskletsJob = (Job) ctx.getBean(CHUNKS.getName());
        Job chunksJob = (Job) ctx.getBean(TASKLETS.getName());
        jobLauncher.run(taskletsJob ,new JobParameters());
        jobLauncher.run(chunksJob,new JobParameters());
    }
}
