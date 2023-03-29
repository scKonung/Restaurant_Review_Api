package com.restaurantreview.api.dto;

import lombok.Data;

import java.util.List;
@Data
public class RestaurantResponse {
    private List<RestaurantDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
