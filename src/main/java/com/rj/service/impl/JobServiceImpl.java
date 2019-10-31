package com.rj.service.impl;

import com.rj.dao.JobDAO;
import com.rj.job.JobAndTrigger;
import com.rj.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("jobService")
public class JobServiceImpl implements JobService {
    @Autowired
    private JobDAO jobDAO;

    @Override
    public List<JobAndTrigger> queryJobs() {
        return jobDAO.queryJobs();
    }

    @Override
    public JobAndTrigger queryJobByNameAndGroup(JobAndTrigger jt) {
        return null;
    }
}
