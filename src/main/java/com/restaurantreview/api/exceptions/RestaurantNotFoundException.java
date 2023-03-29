package com.restaurantreview.api.exceptions;

public class RestaurantNotFoundException extends RuntimeException{
    private static final long serialVerisionID = 1;

    public RestaurantNotFoundException(String message){
        super(message);
    }
}
