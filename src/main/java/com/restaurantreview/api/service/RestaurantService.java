package com.restaurantreview.api.service;

import com.restaurantreview.api.dto.RestaurantDto;
import com.restaurantreview.api.dto.RestaurantResponse;

public interface RestaurantService {
    RestaurantDto createRestaurant(RestaurantDto restaurantDto);
    RestaurantResponse getAllRestaurant(int pageNo, int pageSize);

    RestaurantDto getRestaurantById(int id);
    RestaurantDto updateRestaurant(RestaurantDto restaurantDto, int id);
    void deleteRestaurant(int id);
}
