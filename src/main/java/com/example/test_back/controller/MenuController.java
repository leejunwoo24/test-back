package com.example.test_back.controller;

import com.example.test_back.common.ApiMappingPattern;
import com.example.test_back.dto.ResponseDto;
import com.example.test_back.dto.request.PostMenuRequestDto;
import com.example.test_back.dto.response.MenuResponseDto;
import com.example.test_back.dto.response.PostMenuResponseDto;
import com.example.test_back.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.MENU_API)
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<ResponseDto<PostMenuResponseDto>> createMenu(
            @PathVariable Long restaurantId,
            @RequestBody PostMenuRequestDto dto){
       ResponseDto<PostMenuResponseDto> menu = menuService.createMenu(restaurantId, dto);
       return ResponseEntity.status(HttpStatus.CREATED).body(menu);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<MenuResponseDto>>> findAllMenu(@PathVariable Long restaurantId){
        ResponseDto<List<MenuResponseDto>> menuList = menuService.findAllMenu(restaurantId);
        return ResponseEntity.status(HttpStatus.OK).body(menuList);
    }

    @GetMapping("/{menuid}")
    public ResponseEntity<ResponseDto<MenuResponseDto>> findByIdMenu(@PathVariable Long menuId){
        ResponseDto<MenuResponseDto> menu = menuService.findByIdMenu(menuId);
        return ResponseEntity.status(HttpStatus.OK).body(menu);
    }

    @PutMapping("/{menuId}")
    public ResponseEntity<ResponseDto<MenuResponseDto>> updateMenu(
            @PathVariable Long restaurantId,
            @PathVariable Long menuId,
            @RequestBody PostMenuRequestDto dto){
        ResponseDto<MenuResponseDto> updateMenu = menuService.updateMenu(restaurantId, menuId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updateMenu);
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<ResponseDto<Void>> deleteMenu(@PathVariable Long restaurantId,  @PathVariable Long menuId){
        ResponseDto<Void> response = menuService.deleteMenu(restaurantId ,menuId);
        return ResponseEntity.noContent().build();
    }
}
