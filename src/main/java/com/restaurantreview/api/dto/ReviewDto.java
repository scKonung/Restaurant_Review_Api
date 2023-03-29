package com.restaurantreview.api.dto;

import lombok.Data;

@Data
public class ReviewDto {
    private int id;
    private String title;
    private String content;
    private int starts;
}
