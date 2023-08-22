package com.sourav.csv.config;

import com.sourav.csv.model.Student;
import com.sourav.csv.reader.CSVItemReader;
import com.sourav.csv.writer.CSVItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.WritableResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class CSVJob {

    @Value("file:outputFiles/students.csv")
    private WritableResource outputCSV;

    @Autowired
    private CSVItemReader csvItemReader;
    @Autowired
    private CSVItemWriter csvItemWriter;

    @Bean
    public Job firstJob(JobRepository jobRepository, @Qualifier("firstChunkStep") Step firstChunkStep) {
        return new JobBuilder("First Job", jobRepository)
                .start(firstChunkStep)
                .build();
    }

    @Bean
    public Step firstChunkStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws Exception {
        return new StepBuilder("First Step", jobRepository)
                .<Student, Student>chunk(3, transactionManager)
                .reader(csvItemReader.flatFileItemReader())
                //.writer(csvItemWriter)
                .writer(csvItemWriter.itemWriter(outputCSV))
                .build();
    }
}
