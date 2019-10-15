package com.example.chunks;

import com.example.model.Line;
import com.example.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;

@Slf4j
public class LineReader implements ItemReader<Line>, StepExecutionListener {

    private FileUtils fu;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        fu = new FileUtils("input/tasklets-vs-chunks.csv");
        log.debug("Line Reader initialized.");
    }

    @Override
    public Line read() throws Exception {
        Line line = fu.readLine();
        if (line != null) log.debug("Read line: " + line.toString());
        return line;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        fu.closeReader();
        log.debug("Line Reader ended.");
        return ExitStatus.COMPLETED;
    }
}
