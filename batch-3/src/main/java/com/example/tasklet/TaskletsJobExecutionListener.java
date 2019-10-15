package com.example.tasklet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TaskletsJobExecutionListener extends JobExecutionListenerSupport {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        String jobName = jobExecution.getJobInstance().getJobName();
        BatchStatus jobStatus = jobExecution.getStatus();
        log.info("Before job: {}. Job status is: {}", jobName, jobStatus.name());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        String jobName = jobExecution.getJobInstance().getJobName();
        BatchStatus jobStatus = jobExecution.getStatus();
        ExitStatus jobExitStatus = jobExecution.getExitStatus();
        log.info("After job: {}. Job status is: {}. Job exit status is: {}", jobName, jobStatus.name(), jobExitStatus.getExitCode());
    }
}
