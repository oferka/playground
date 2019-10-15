package com.example.tasklet;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.Jobs.Names.TASKLETS_JOB_NAME;

@Configuration
@EnableBatchProcessing
public class TaskletsConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean(name = "taskletsLinesReader")
    public LinesReader linesReader() {
        return new LinesReader();
    }

    @Bean(name = "taskletsLinesProcessor")
    public LinesProcessor linesProcessor() {
        return new LinesProcessor();
    }

    @Bean(name = "taskletsLinesWriter")
    public LinesWriter linesWriter() {
        return new LinesWriter();
    }

    @Bean(name = "taskletsReadLines")
    protected Step readLines() {
        return stepBuilderFactory
                .get("readLines")
                .tasklet(linesReader())
                .build();
    }

    @Bean(name = "taskletsProcessLines")
    protected Step processLines() {
        return stepBuilderFactory
                .get("processLines")
                .tasklet(linesProcessor())
                .build();
    }

    @Bean(name = "taskletsWriteLines")
    protected Step writeLines() {
        return stepBuilderFactory
                .get("writeLines")
                .tasklet(linesWriter())
                .build();
    }

    @Bean(name = TASKLETS_JOB_NAME)
    public Job job(TaskletsJobExecutionListener listener) {
        return jobBuilderFactory
                .get("taskletsJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(readLines())
                .next(processLines())
                .next(writeLines())
                .build();
    }
}
