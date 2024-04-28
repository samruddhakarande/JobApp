package com.sam.JobApp.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sam.JobApp.job.Job;
import com.sam.JobApp.review.Review;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    @JsonIgnore //to remove the infinite loop of recursion from job to company and then company to job
    @OneToMany(mappedBy = "company") //mappedby is used to get rid of extra table created which stores company_id and job_id. Now it will be handled by a variable company which is defined in Job class
    private List<Job> jobList;

    @OneToMany(mappedBy = "company")
    private List<Review> reviewList;

    public Company() {
    }

    public Company(long id, String name, String description, List<Job> jobList, List<Review> reviewList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.jobList = jobList;
        this.reviewList = reviewList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", jobList=" + jobList +
                ", reviewList=" + reviewList +
                '}';
    }
}
