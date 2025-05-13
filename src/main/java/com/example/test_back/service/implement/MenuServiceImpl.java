package com.example.test_back.service.implement;

import com.example.test_back.dto.ResponseDto;
import com.example.test_back.dto.request.PostMenuRequestDto;
import com.example.test_back.dto.response.MenuResponseDto;
import com.example.test_back.dto.response.PostMenuResponseDto;
import com.example.test_back.entity.Menu;
import com.example.test_back.entity.Restaurant;
import com.example.test_back.repository.MenuRepository;
import com.example.test_back.repository.RestaurantRepository;
import com.example.test_back.service.MenuService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    @Override
    @Transactional
    public ResponseDto<PostMenuResponseDto> createMenu(Long restaurantId, PostMenuRequestDto dto) {
      PostMenuResponseDto response = null;

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
              .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 레스토랑입니다." + restaurantId));


      Menu newMenu = Menu.builder()
              .name(dto.getName())
              .price(dto.getPrice())
              .description(dto.getDescription())
              .build();


        restaurant.addMenu(newMenu);

        Menu savedMenu = menuRepository.save(newMenu);

        response = PostMenuResponseDto.builder()
                .id(savedMenu.getId())
                .name(savedMenu.getName())
                .price(savedMenu.getPrice())
                .description(savedMenu.getDescription())
                .restaurant(savedMenu.getRestaurant())
                .build();

        return ResponseDto.setSuccess("성공", response);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<MenuResponseDto>> findAllMenu(Long restaurantId) {
        List<MenuResponseDto> response = null;

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 레스토랑입니다." + restaurantId));

        response = restaurant.getMenus().stream()
                .map(menu -> new MenuResponseDto(
                  menu.getId(),
                  menu.getName(),
                  menu.getPrice(),
                  menu.getDescription())
                ).collect(Collectors.toList());

        return ResponseDto.setSuccess("성공", response);
    }

    @Override
    public ResponseDto<MenuResponseDto> findByIdMenu(Long menuId) {
        return null;
    }

    @Override
    @Transactional
    public ResponseDto<MenuResponseDto> updateMenu(Long restaurantId, Long menuId, PostMenuRequestDto dto) {
        MenuResponseDto response = null;

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 레스토랑입니다." + restaurantId));

        if(!menu.getRestaurant().getId().equals(restaurantId)){
            throw new IllegalArgumentException("Comment does not belong to the specified Post");
        }

        menu.setName(dto.getName());
        menu.setPrice(dto.getPrice());
        menu.setDescription(dto.getDescription());
        Menu updateMenu = menuRepository.save(menu);

        response = MenuResponseDto.builder()
                .id(updateMenu.getId())
                .name(updateMenu.getName())
                .price(updateMenu.getPrice())
                .description(updateMenu.getDescription())
                .build();

        return ResponseDto.setSuccess("성공", response);
    }

    @Override
    @Transactional
    public ResponseDto<Void> deleteMenu(Long restaurantId, Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 메뉴" + menuId));

        if(!menu.getRestaurant().getId().equals(restaurantId)){
            throw new IllegalArgumentException("Menu does not belong to the specified Restaurant");
        }

        menu.getRestaurant().removeMenu(menu);

        menuRepository.delete(menu);

        return ResponseDto.setSuccess("성공", null);
    }
}
