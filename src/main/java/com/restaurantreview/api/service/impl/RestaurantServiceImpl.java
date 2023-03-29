package com.restaurantreview.api.service.impl;

import com.restaurantreview.api.dto.RestaurantDto;
import com.restaurantreview.api.dto.RestaurantResponse;
import com.restaurantreview.api.exceptions.RestaurantNotFoundException;
import com.restaurantreview.api.models.Restaurant;
import com.restaurantreview.api.repository.RestaurantRepository;
import com.restaurantreview.api.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public RestaurantDto createRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDto.getName());
        restaurant.setType(restaurantDto.getType());

        Restaurant newRestaurant = restaurantRepository.save(restaurant);

        RestaurantDto restaurantResponse = new RestaurantDto();
        restaurantResponse.setId(newRestaurant.getId());
        restaurantResponse.setName(newRestaurant.getName());
        restaurantResponse.setType(newRestaurant.getType());
        return restaurantResponse;
    }

    @Override
    public RestaurantResponse getAllRestaurant(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Restaurant> restaurant = restaurantRepository.findAll(pageable);
        List<Restaurant> listOfRestaurants = restaurant.getContent();
       List<RestaurantDto> content = listOfRestaurants.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        RestaurantResponse restaurantResponse = new RestaurantResponse();
        restaurantResponse.setContent(content);
        restaurantResponse.setPageNo(restaurant.getNumber());
        restaurantResponse.setPageSize(restaurant.getSize());
        restaurantResponse.setTotalElements(restaurant.getTotalElements());
        restaurantResponse.setTotalPages(restaurant.getTotalPages());
        restaurantResponse.setLast(restaurant.isLast());
        return restaurantResponse;
    }

    @Override
    public RestaurantDto getRestaurantById(int id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException("Restaurant could not be found"));
        return mapToDto(restaurant);
    }

    @Override
    public RestaurantDto updateRestaurant(RestaurantDto restaurantDto, int id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException("Restaurant could not be updated"));

        restaurant.setName(restaurantDto.getName());
        restaurant.setType(restaurantDto.getType());

        Restaurant updateRestaurant = restaurantRepository.save(restaurant);
        return mapToDto(updateRestaurant);
    }

    @Override
    public void deleteRestaurant(int id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException("Restaurant could not be delete"));
        restaurantRepository.delete(restaurant);
    }

    private RestaurantDto mapToDto(Restaurant restaurant){
            RestaurantDto restaurantDto = new RestaurantDto();
            restaurantDto.setId(restaurant.getId());
            restaurantDto.setName(restaurant.getName());
            restaurantDto.setType(restaurant.getType());
            return restaurantDto;
    }

    private Restaurant mapToEntity(RestaurantDto restaurantDto){
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDto.getName());
        restaurant.setType(restaurantDto.getType());
        return restaurant;
    }
}
