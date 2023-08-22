package com.sourav.tasklet.config;

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
public class TaskletJob {

    @Autowired
    private FirstTasklet firstTask;

    @Autowired
    private SecondTasklet secondTask;

    @Autowired
    private FirstJobListener firstJobListener;

    @Autowired
    private FirstStepListener firstStepListener;

    @Bean
    public Job firstJob(JobRepository jobRepository, @Qualifier("firstStep") Step firstStep, @Qualifier("secondStep") Step secondStep) {
        return new JobBuilder("First Job", jobRepository)
                .start(firstStep)
                .next(secondStep)
                .listener(firstJobListener)
                .build();
    }

    @Bean
    public Step firstStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("First Step", jobRepository)
                .tasklet(firstTask, transactionManager)
                .listener(firstStepListener)
                .build();
    }

    //@Bean
    public Tasklet firstTask() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("This is the first Tasklet step");
                return RepeatStatus.FINISHED;
            }
        };
    }

    @Bean
    public Step secondStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("Second Step", jobRepository)
                .tasklet(secondTask, transactionManager)
                .build();
    }

    //@Bean
    public Tasklet secondTask() {
        return new Tasklet() {

            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("This is the Second Tasklet step");
                return RepeatStatus.FINISHED;
            }
        };
    }
}
