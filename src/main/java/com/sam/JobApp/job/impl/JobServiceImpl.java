package com.sam.JobApp.job.impl;

import com.sam.JobApp.job.Job;
import com.sam.JobApp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    List<Job> jobList = new ArrayList<>();
    private long next = 1L;
    @Override
    public List<Job> findAllJobs() {
        return jobList;
    }

    @Override
    public Job postNewJob(Job job) {
        job.setId(next++);
        jobList.add(job);
        return job;
    }

    @Override
    public Job findJobById(long id) {
        for(Job job: jobList) {
            if(id == job.getId()) {
                return job;
            }
        }
        return null;
    }

    @Override
    public Boolean deleteJobById(long id) {
        Iterator<Job> iterator = jobList.iterator();
        while(iterator.hasNext()){
            Job job = iterator.next();
            if(id == job.getId()) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public Job updateJob(long id, Job job) {
        for(Job jobs : jobList) {
            if(jobs.getId() == id) {
                jobs.setTitle(job.getTitle());
                jobs.setDescription(job.getDescription());
                jobs.setMinSalary(job.getMinSalary());
                jobs.setMaxSalary(job.getMaxSalary());
                jobs.setLocation(job.getLocation());
                return jobs;
            }
        }
        return null;
    }
}
