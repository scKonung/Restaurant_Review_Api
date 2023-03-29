package com.restaurantreview.api.service;

import com.restaurantreview.api.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(int restaurantId, ReviewDto reviewDto);
    List<ReviewDto> getReviewsByRestaurantId(int id);
    ReviewDto getReviewById(int reviewId, int restaurantId);
    ReviewDto updateReview(int restaurantId, int reviewId, ReviewDto reviewDto);
    void deleteReview(int restaurantId, int reviewId);
}
