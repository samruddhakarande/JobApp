package com.sam.JobApp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable long companyId) {
        List<Review> reviewList = reviewService.getAllReviews(companyId);
        return new ResponseEntity<>(reviewList, HttpStatus.OK);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable long companyId, @PathVariable long reviewId) {
        Review review = reviewService.getReviewById(companyId, reviewId);
        if(review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<Review> addReview(@PathVariable long companyId, @RequestBody Review review) {
        Review addedReview = reviewService.addReview(companyId, review);
        if(addedReview != null) {
            return new ResponseEntity<>(addedReview, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable long companyId, @PathVariable long reviewId, @RequestBody Review review) {
        Review updatedReview = reviewService.updateReview(companyId, reviewId, review);
        if(updatedReview != null) {
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable long companyId, @PathVariable long reviewId) {
        boolean isDeleted = reviewService.deleteReview(companyId, reviewId);
        if(isDeleted) {
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review doesn't found", HttpStatus.NOT_FOUND);
    }
}

