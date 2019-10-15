package com.example.chunks;

import com.example.model.Line;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.Jobs.Names.CHUNKS_JOB_NAME;

@Configuration
@EnableBatchProcessing
public class ChunksConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean(name = "chunksItemReader")
    public ItemReader<Line> itemReader() {
        return new LineReader();
    }

    @Bean(name = "chunksItemProcessor")
    public ItemProcessor<Line, Line> itemProcessor() {
        return new LineProcessor();
    }

    @Bean(name = "chunksItemWriter")
    public ItemWriter<Line> itemWriter() {
        return new LinesWriter();
    }

    @Bean(name = "chunksProcessLines")
    protected Step processLines(ItemReader<Line> reader, ItemProcessor<Line, Line> processor, ItemWriter<Line> writer) {
        return stepBuilderFactory
                .get("processLines")
                .<Line, Line> chunk(2)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean(name = CHUNKS_JOB_NAME)
    public Job job(ChunksJobExecutionListener listener) {
        return jobBuilderFactory
                .get("chunksJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(processLines(itemReader(), itemProcessor(), itemWriter()))
                .build();
    }
}
