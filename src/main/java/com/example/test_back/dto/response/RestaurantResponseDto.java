package com.example.test_back.dto.response;

import lombok.Builder;

@Builder
public class RestaurantResponseDto {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;

}
