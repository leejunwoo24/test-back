package com.example.test_back.service;

import com.example.test_back.dto.ResponseDto;
import com.example.test_back.dto.request.PostMenuRequestDto;
import com.example.test_back.dto.response.MenuResponseDto;
import com.example.test_back.dto.response.PostMenuResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService {
    ResponseDto<PostMenuResponseDto> createMenu(Long restaurantId, PostMenuRequestDto dto);

    ResponseDto<List<MenuResponseDto>> findAllMenu(Long restaurantId);

    ResponseDto<MenuResponseDto> findByIdMenu(Long menuId);

    ResponseDto<MenuResponseDto> updateMenu(Long restaurantId, Long menuId, PostMenuRequestDto dto);

    ResponseDto<Void> deleteMenu(Long restaurantId ,Long menuId);
}
