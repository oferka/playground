package com.example.tasklet;

import com.example.model.Line;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
public class LinesProcessor implements Tasklet, StepExecutionListener {

    private List<Line> lines;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        for (Line line : lines) {
            long age = ChronoUnit.YEARS.between(line.getDob(), LocalDate.now());
            log.debug("Calculated age " + age + " for line " + line.toString());
            line.setAge(age);
        }
        return RepeatStatus.FINISHED;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        ExecutionContext executionContext = stepExecution
                .getJobExecution()
                .getExecutionContext();
        this.lines = (List<Line>) executionContext.get("lines");
        log.debug("Lines Processor initialized.");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.debug("Lines Processor ended.");
        return ExitStatus.COMPLETED;
    }
}
