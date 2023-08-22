package com.sourav.chunk.service;

import com.sourav.chunk.request.JobParamsRequest;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobLauncher jobLauncher;

    @Qualifier("secondJob")
    @Autowired
    private Job secondJob;

    @Qualifier("firstJob")
    @Autowired
    private Job firstJob;

    @Async
    public void startJob(String jobName, List<JobParamsRequest> jobParamsRequests) {

        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder()
                .addLocalDate("currentTime", LocalDate.now());


        jobParamsRequests.stream()
                .forEach(jobParamsRequest -> {
                    jobParametersBuilder
                            .addString(jobParamsRequest.getParamKey(),
                                    jobParamsRequest.getParamValue());
                });

        try {
            JobExecution jobExecution = null;
            if (jobName.equals("First Job"))
                jobExecution = jobLauncher.run(firstJob, jobParametersBuilder.toJobParameters());
            else if (jobName.equals("Second Job"))
                jobExecution = jobLauncher.run(firstJob, jobParametersBuilder.toJobParameters());

            System.out.println("jobExecution ID = " + jobExecution.getId());

        } catch (Exception e) {
            e.getMessage();
        }

    }
}
