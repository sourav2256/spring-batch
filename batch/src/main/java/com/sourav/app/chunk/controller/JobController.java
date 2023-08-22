package com.sourav.app.chunk.controller;

import com.sourav.app.chunk.request.JobParamsRequest;
import com.sourav.app.chunk.service.JobService;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private JobOperator jobOperator;

    @PostMapping("/start/{jobName}")
    public String startJob(@PathVariable String jobName, @RequestBody List<JobParamsRequest> jobParamsRequests) {

        jobService.startJob(jobName, jobParamsRequests);
        return "Job Started......";
    }

    @GetMapping("/stop/{jobExecutionId}")
    public String stopJob(@PathVariable long jobExecutionId) {
        try {
            jobOperator.stop(jobExecutionId);

        } catch (Exception e) {

        }
        return "Job Stopped...";
    }
}
