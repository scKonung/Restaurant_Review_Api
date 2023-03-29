package com.restaurantreview.api.service.impl;

import com.restaurantreview.api.dto.ReviewDto;
import com.restaurantreview.api.exceptions.RestaurantNotFoundException;
import com.restaurantreview.api.exceptions.ReviewNotFoundException;
import com.restaurantreview.api.models.Restaurant;
import com.restaurantreview.api.models.Review;
import com.restaurantreview.api.repository.RestaurantRepository;
import com.restaurantreview.api.repository.ReviewRepository;
import com.restaurantreview.api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private RestaurantRepository restaurantRepository;
    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, RestaurantRepository restaurantRepository) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
    }


    @Override
    public ReviewDto createReview(int restaurantId, ReviewDto reviewDto) {
        Review review = mapToEntity(reviewDto);
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotFoundException("Restaurant with associated review not found"));

        review.setRestaurant(restaurant);

        Review newReview = reviewRepository.save(review);
        return mapToDto(newReview);
    }

    @Override
    public List<ReviewDto> getReviewsByRestaurantId(int id) {
        List<Review> reviews = reviewRepository.findByRestaurantId(id);

        return reviews.stream().map(review -> mapToDto(review)).toList();
    }

    @Override
    public ReviewDto getReviewById(int reviewId, int restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotFoundException("Restaurant with associated review not found"));

        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review with associated restaurant not found"));

        if(review.getRestaurant().getId() != restaurant.getId()){
            throw new ReviewNotFoundException("This review does not belond to a Restaurant");
        }

        return mapToDto(review);
    }

    @Override
    public ReviewDto updateReview(int restaurantId, int reviewId, ReviewDto reviewDto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotFoundException("Restaurant with associated review not found"));

        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review with associated restaurant not found"));

        if(review.getRestaurant().getId() != restaurant.getId()){
            throw new ReviewNotFoundException("This review does not belond to a restaurant");
        }

        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStarts(reviewDto.getStarts());

        Review upadteReview = reviewRepository.save(review);

        return mapToDto(upadteReview);
    }

    @Override
    public void deleteReview(int restaurantId, int reviewId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotFoundException("Restaurant with associated review not found"));

        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review with associated restaurant not found"));

        if(review.getRestaurant().getId() != restaurant.getId()){
            throw new ReviewNotFoundException("This review does not belond to a restaurant");
        }

        reviewRepository.delete(review);
    }

    private ReviewDto mapToDto(Review review){
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());
        reviewDto.setStarts(review.getStarts());
        return reviewDto;
   }

    private Review mapToEntity(ReviewDto reviewDto){
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStarts(reviewDto.getStarts());
        return review;
    }
}
