package com.sourav.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.stereotype.Component;

@Component
public class FirstJobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Before job "+ jobExecution.getJobInstance().getJobName());
        System.out.println("Job Params "+ jobExecution.getJobParameters());
        System.out.println("Job Exec Context "+ jobExecution.getExecutionContext());

        jobExecution.getExecutionContext().put("key", "value");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("After job "+ jobExecution.getJobInstance().getJobName());
        System.out.println("Job Params "+ jobExecution.getJobParameters());
        System.out.println("Job Exec Context "+ jobExecution.getExecutionContext());    }
}
