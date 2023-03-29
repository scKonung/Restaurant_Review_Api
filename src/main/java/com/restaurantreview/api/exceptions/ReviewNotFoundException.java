package com.restaurantreview.api.exceptions;

public class ReviewNotFoundException extends RuntimeException{
    private static final long serialVerisionID = 2;

    public ReviewNotFoundException(String message){
        super(message);
    }
}
