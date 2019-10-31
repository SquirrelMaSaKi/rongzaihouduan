package com.rj.service;

import com.rj.job.JobAndTrigger;

import java.util.List;

public interface JobService {
    List<JobAndTrigger> queryJobs();
    JobAndTrigger queryJobByNameAndGroup(JobAndTrigger jt);
}
