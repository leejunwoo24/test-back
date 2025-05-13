package com.example.test_back.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class MenuResponseDto {
    private Long id;
    private String name;
    private Double price;
    private String description;
}
