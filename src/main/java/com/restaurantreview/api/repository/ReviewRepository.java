package com.restaurantreview.api.repository;

import com.restaurantreview.api.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByRestaurantId(int restaurantId);

}
