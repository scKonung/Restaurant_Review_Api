package com.restaurantreview.api.controllers;

import com.restaurantreview.api.dto.ReviewDto;
import com.restaurantreview.api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReviewController {
    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/restaurant/{restaurantId}/review")
    public ResponseEntity<ReviewDto> createReview(@PathVariable(value = "restaurantId") int restaurantId, @RequestBody ReviewDto reviewDto){
        return new ResponseEntity<>(reviewService.createReview(restaurantId,reviewDto), HttpStatus.CREATED);
    }

    @GetMapping("/restaurant/{restaurantId}/reviews")
    public List<ReviewDto> getReviewByRestaurantId(@PathVariable(value = "restaurantId") int restaurantId){
        return reviewService.getReviewsByRestaurantId(restaurantId);
    }

    @GetMapping("/restaurant/{restaurantId}/reviews/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable(value = "restaurantId") int restaurantId, @PathVariable(value = "id") int reviewId){
        ReviewDto reviewDto = reviewService.getReviewById(reviewId, restaurantId);
        return new ResponseEntity<>(reviewDto, HttpStatus.OK);
    }

    @PutMapping("/restaurant/{restaurantId}/reviews/{id}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable(value = "restaurantId") int restaurantId, @PathVariable(value = "id") int reviewId,
                                                  @RequestBody ReviewDto reviewDto)
    {
        ReviewDto updatedReviewDto = reviewService.updateReview(restaurantId, reviewId, reviewDto);
        return new ResponseEntity<>(updatedReviewDto, HttpStatus.OK);
    }

    @DeleteMapping("/restaurant/{restaurantId}/reviews/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable(value = "restaurantId") int restaurantId, @PathVariable(value = "id") int reviewId){
        reviewService.deleteReview(restaurantId,reviewId);
        return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
    }
}
