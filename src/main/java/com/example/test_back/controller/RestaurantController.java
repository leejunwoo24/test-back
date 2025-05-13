package com.example.test_back.controller;

import com.example.test_back.common.ApiMappingPattern;
import com.example.test_back.dto.ResponseDto;
import com.example.test_back.dto.request.PostRestaurantRequestDto;
import com.example.test_back.dto.response.PostRestaurantResponseDto;
import com.example.test_back.dto.response.RestaurantResponseDto;
import com.example.test_back.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.RESTAURANT_API)
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<ResponseDto<PostRestaurantResponseDto>> createRestaurant(
            @RequestBody PostRestaurantRequestDto dto){
        ResponseDto<PostRestaurantResponseDto> restaurant = restaurantService.createRestaurant(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<RestaurantResponseDto>>> findAllRestaurant(){
        ResponseDto<List<RestaurantResponseDto>> allRestaurant = restaurantService.findAllRestaurant();
        return ResponseEntity.status(HttpStatus.OK).body(allRestaurant);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<RestaurantResponseDto>> findByIdRestaurant(@PathVariable Long id){
        ResponseDto<RestaurantResponseDto> restaurant = restaurantService.findByIdRestaurant(id);
        return ResponseEntity.status(HttpStatus.OK).body(restaurant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<RestaurantResponseDto>> updateRestaurant(
            @PathVariable Long id,
            @RequestBody PostRestaurantRequestDto dto){
        ResponseDto<RestaurantResponseDto> updateRestaurant = restaurantService.updateRestaurant(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updateRestaurant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Void>> deleteRestaurant(@PathVariable Long id){
        ResponseDto<Void> response = restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }

}
