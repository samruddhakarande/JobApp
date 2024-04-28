package com.sam.JobApp.job.impl;

import com.sam.JobApp.job.Job;
import com.sam.JobApp.job.JobRepository;
import com.sam.JobApp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    public JobRepository jobRepository;
    //private long next = 1L;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job postNewJob(Job job) {
      //  job.setId(next++);
        return jobRepository.save(job);
    }

    @Override
    public Job findJobById(long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean deleteJobById(long id) {
       try {
           jobRepository.deleteById(id);
           return true;
       }
       catch (Exception e) {
           e.printStackTrace();
           return false;
       }
    }

    @Override
    public Job updateJob(long id, Job updatedJob) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if(optionalJob.isPresent()) {
            Job job = optionalJob.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return job;
        }
        return null;
    }
}
