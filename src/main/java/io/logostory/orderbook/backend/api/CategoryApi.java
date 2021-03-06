package io.logostory.orderbook.backend.api;

import io.logostory.orderbook.backend.domain.dto.category.CategoryDto.*;
import io.logostory.orderbook.backend.domain.entity.category.Category;
import io.logostory.orderbook.backend.repository.CategoryRepository;
import io.logostory.orderbook.backend.repository.ShopRepository;
import io.logostory.orderbook.backend.service.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api
@RestController
@RequestMapping("/v1/api")
public class CategoryApi {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ShopRepository  shopRepository;

    @Autowired
    CategoryService categoryService;

    @PostMapping(path = "/shops/{shopId}/categories")
    public ResponseEntity<List<CategorySearchResultDto>> addCategoryList(
            @PathVariable Long shopId, @RequestBody List<CategoryAddDto> dtos) {

        List<Category> categories =  categoryService.addCategoryList(shopId, dtos);
        return ResponseEntity.ok( categories.stream().map(c -> new CategorySearchResultDto(c)).collect(Collectors.toList()) );
    }

    @GetMapping(path = "/shops/{shopId}/categories")
    public ResponseEntity<List<CategorySearchResultDto>> findCategoryList(@PathVariable Long shopId) {

        List<Category> categories = shopRepository.findById(shopId).get().getCategories();
        return ResponseEntity.ok( categories.stream().map(c -> new CategorySearchResultDto(c)).collect(Collectors.toList()) );
    }


}
