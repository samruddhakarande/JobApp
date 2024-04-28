package com.sam.JobApp.review.impl;

import com.sam.JobApp.company.Company;
import com.sam.JobApp.company.CompanyRepository;
import com.sam.JobApp.company.CompanyService;
import com.sam.JobApp.review.Review;
import com.sam.JobApp.review.ReviewRepository;
import com.sam.JobApp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }
    @Override
    public List<Review> getAllReviews(long companyId) {
        List<Review> reviewList = reviewRepository.findByCompanyId(companyId);
        return reviewList;
    }

    @Override
    public Review getReviewById(long companyId, long reviewId) {
        List<Review> reviewList = reviewRepository.findByCompanyId(companyId);
        return reviewList.stream().filter(review -> review.getId() == reviewId).findFirst().orElse(null);
    }

    @Override
    public Review updateReview(long companyId, long reviewId, Review updatedReview) {
       if(companyService.getCompanyById(companyId) != null) {
           List<Review> reviewList = reviewRepository.findByCompanyId(companyId);
           for(Review review: reviewList) {
               if(review.getId() == reviewId) {
                   review.setTitle(updatedReview.getTitle());
                   review.setDescription(updatedReview.getDescription());
                   review.setRating(updatedReview.getRating());
                   review.setCompany(companyService.getCompanyById(companyId));
                   reviewRepository.save(review);
                   return review;
               }
           }
       }
       return null;
    }

    @Override
    public Review addReview(long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if(company != null) {
            review.setCompany(company);
            return reviewRepository.save(review);
        }
       return null;
    }

    @Override
    public boolean deleteReview(long companyId, long reviewId) {
        if(companyService.getCompanyById(companyId) != null && reviewRepository.existsById(reviewId)) {
           Review review = reviewRepository.findById(reviewId).orElse(null);
           Company company = review.getCompany();
           company.getReviewList().remove(review);
           review.setCompany(null);
           companyService.updateCompany(companyId, company);
           reviewRepository.deleteById(reviewId);
           return true;
        }
        return false;
    }
}
