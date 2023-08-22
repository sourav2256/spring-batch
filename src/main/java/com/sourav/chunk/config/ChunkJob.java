package com.sourav.chunk.config;

import com.sourav.chunk.processor.FirstItemProcessor;
import com.sourav.chunk.reader.FirstItemReader;
import com.sourav.chunk.writer.FirstItemWriter;
import com.sourav.tasklet.listener.FirstJobListener;
import com.sourav.tasklet.listener.FirstStepListener;
import com.sourav.tasklet.service.FirstTasklet;
import com.sourav.tasklet.service.SecondTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ChunkJob {

    @Autowired
    private FirstItemReader firstItemReader;
    @Autowired
    private FirstItemProcessor firstItemProcessor;
    @Autowired
    private FirstItemWriter firstItemWriter;
    @Bean
    public Job firstJob(JobRepository jobRepository, @Qualifier("firstChunkStep") Step firstChunkStep) {
        return new JobBuilder("First Job", jobRepository)
                .start(firstChunkStep)
                .build();
    }

    @Bean
    public Job secondJob(JobRepository jobRepository, @Qualifier("secondChunkStep") Step secondChunkStep) {
        return new JobBuilder("Second Job", jobRepository)
                .start(secondChunkStep)
                .build();
    }

    @Bean
    public Step firstChunkStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("First Step", jobRepository)
                .<Integer, Long>chunk(3, transactionManager)
                .reader(firstItemReader)
                .processor(firstItemProcessor)
                .writer(firstItemWriter)
                .build();
    }
    @Bean
    public Step secondChunkStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("First Step", jobRepository)
                .<Integer, Long>chunk(3, transactionManager)
                .reader(firstItemReader)
                .processor(firstItemProcessor)
                .writer(firstItemWriter)
                .build();
    }
}
