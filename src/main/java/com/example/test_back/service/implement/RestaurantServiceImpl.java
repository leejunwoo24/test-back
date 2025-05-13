package com.example.test_back.service.implement;

import com.example.test_back.dto.ResponseDto;
import com.example.test_back.dto.request.PostRestaurantRequestDto;
import com.example.test_back.dto.response.PostRestaurantResponseDto;
import com.example.test_back.dto.response.RestaurantResponseDto;
import com.example.test_back.entity.Restaurant;
import com.example.test_back.repository.RestaurantRepository;
import com.example.test_back.service.RestaurantService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;



    @Override
    @Transactional
    public ResponseDto<PostRestaurantResponseDto> createRestaurant(PostRestaurantRequestDto dto) {
        PostRestaurantResponseDto response = null;

        Restaurant newRestaurant = Restaurant.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .phoneNumber(dto.getPhoneNumber())
                .build();

        Restaurant savedRestaurant = restaurantRepository.save(newRestaurant);

        response = PostRestaurantResponseDto.builder()
                .id(savedRestaurant.getId())
                .name(savedRestaurant.getName())
                .address(savedRestaurant.getAddress())
                .phoneNumber(savedRestaurant.getPhoneNumber())
                .build();

        return ResponseDto.setSuccess("Success", response);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<RestaurantResponseDto>> findAllRestaurant() {
        List<RestaurantResponseDto> response = null;

        List<Restaurant> restaurants = restaurantRepository.findAll();

        response = restaurants.stream()
                .map(restaurant -> RestaurantResponseDto.builder()
                        .id(restaurant.getId())
                        .name(restaurant.getName())
                        .address(restaurant.getAddress())
                        .phoneNumber(restaurant.getPhoneNumber())
                        .build())
                .collect(Collectors.toList());

        return ResponseDto.setSuccess("Success", response);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<RestaurantResponseDto> findByIdRestaurant(Long id) {
        RestaurantResponseDto response = null;

        Restaurant newRestaurant = restaurantRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("존재하지 않는 레스토랑입니다." + id));

        response = RestaurantResponseDto.builder()
                .id(newRestaurant.getId())
                .name(newRestaurant.getName())
                .address(newRestaurant.getAddress())
                .phoneNumber(newRestaurant.getPhoneNumber())
                .build();

        return ResponseDto.setSuccess("Success", response);
    }

    @Override
    @Transactional
    public ResponseDto<RestaurantResponseDto> updateRestaurant(Long id, PostRestaurantRequestDto dto) {
        RestaurantResponseDto response = null;

        Restaurant newRestaurant = restaurantRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("존재하지 않는 레스토랑입니다." + id));

        newRestaurant.setName(dto.getName());
        newRestaurant.setAddress(dto.getAddress());
        newRestaurant.setPhoneNumber(dto.getPhoneNumber());

        Restaurant putRestaurant =  restaurantRepository.save(newRestaurant);

        response = RestaurantResponseDto.builder()
                .id(putRestaurant.getId())
                .name(putRestaurant.getName())
                .address(putRestaurant.getAddress())
                .phoneNumber(putRestaurant.getPhoneNumber())
                .build();

        return ResponseDto.setSuccess("Success", response);
    }

    @Override
    @Transactional
    public ResponseDto<Void> deleteRestaurant(Long id) {

        Restaurant newRestaurant = restaurantRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("존재하지 않는 레스토랑입니다." + id));

        newRestaurant.getMenus().forEach(newRestaurant::removeMenu);

        restaurantRepository.deleteById(id);

        return ResponseDto.setSuccess("성공", null);
    }
}
