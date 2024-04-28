package com.sam.JobApp.review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(long companyId);
    Review getReviewById(long companyId, long reviewId);
    Review updateReview(long companyId, long reviewId, Review updatedReview);
    Review addReview(long companyId, Review review);
    boolean deleteReview(long companyId, long reviewId);
}
