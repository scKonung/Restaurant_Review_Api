package com.restaurantreview.api.repository;

import com.restaurantreview.api.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer/*Usualy is be a Long but fore courese is Integer*/> {

}
