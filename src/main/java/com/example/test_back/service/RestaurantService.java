package com.example.test_back.service;

import com.example.test_back.dto.ResponseDto;
import com.example.test_back.dto.request.PostRestaurantRequestDto;
import com.example.test_back.dto.response.PostRestaurantResponseDto;
import com.example.test_back.dto.response.RestaurantResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestaurantService {
    public ResponseDto<PostRestaurantResponseDto> createRestaurant(PostRestaurantRequestDto dto);


    public ResponseDto<List<RestaurantResponseDto>> findAllRestaurant();


    public ResponseDto<RestaurantResponseDto> findByIdRestaurant(Long id);


    public ResponseDto<RestaurantResponseDto> updateRestaurant(Long id, PostRestaurantRequestDto dto);

    public ResponseDto<Void> deleteRestaurant(Long id);

}
