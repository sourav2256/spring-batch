package com.sourav.app.xml.config;

import com.sourav.app.jdbc.model.Student;
import com.sourav.app.xml.reader.XMLReader;
import com.sourav.app.xml.writer.XMLWriter;
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
public class XMLJob {

    @Autowired
    private XMLReader xmlReader;
    @Autowired
    private XMLWriter xmlWriter;
    private static Logger logger = LoggerFactory.getLogger(XMLJob.class);
    @Bean
    public Job firstJob(JobRepository jobRepository, @Qualifier("firstChunkStep") Step firstChunkStep) {
        return new JobBuilder("First Job", jobRepository)
                .start(firstChunkStep)
                .build();
    }

    @Bean
    public Step firstChunkStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        logger.info("Reading XML....");
        System.out.println("Reading XML....");
        return new StepBuilder("First Step", jobRepository)
                .<Student, Student>chunk(3, transactionManager)
                .reader(xmlReader.staxEventItemReader())
                .writer(xmlWriter)
                .build();
    }
}
