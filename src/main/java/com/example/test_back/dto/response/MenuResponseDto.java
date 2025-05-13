package com.example.test_back.dto.response;

import lombok.Builder;

@Builder
public class MenuResponseDto {
    private Long id;
    private String name;
    private Double price;
    private String description;
}
