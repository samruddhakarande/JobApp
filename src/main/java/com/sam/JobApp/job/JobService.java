package com.sam.JobApp.job;

import java.util.List;

public interface JobService {

    List<Job> findAllJobs();
    Job postNewJob(Job job);
    Job findJobById(long id);
    Boolean deleteJobById(long id);
}
