package com.sourav.app.rest.config;

import com.sourav.app.rest.model.StudentResponse;
import com.sourav.app.rest.reader.RESTReader;
import com.sourav.app.rest.writer.RESTWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class RESTJob {

    @Autowired
    private RESTReader reader;

    @Autowired
    private RESTWriter writer;

    private static Logger logger = LoggerFactory.getLogger(RESTJob.class);
    @Bean
    public Job firstJob(JobRepository jobRepository, @Qualifier("firstChunkStep") Step firstChunkStep) {
        return new JobBuilder("First Job", jobRepository)
                .start(firstChunkStep)
                .build();
    }

    @Bean
    public Step firstChunkStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        logger.info("Reading API....");
        System.out.println("Reading API....");
        return new StepBuilder("First Step", jobRepository)
                .<StudentResponse, StudentResponse>chunk(3, transactionManager)
                .reader(reader.itemReaderAdapter())
                .writer(writer)
                .build();
    }
}
