package com.example.test_back.dto.request;

import lombok.Getter;

@Getter
public class PostMenuRequestDto {
    private String name;
    private Double price;
    private String description;
    private Long restaurantId;
}
