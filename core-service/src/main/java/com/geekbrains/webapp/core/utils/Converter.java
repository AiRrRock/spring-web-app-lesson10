package com.geekbrains.webapp.core.utils;

import com.geekbrains.webapp.api.dtos.CategoryDto;
import com.geekbrains.webapp.api.dtos.ProductDto;
import com.geekbrains.webapp.core.model.Category;
import com.geekbrains.webapp.core.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Converter {
    public ProductDto productToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice(), product.getCategory().getTitle());
    }

    public CategoryDto categoryToDto(Category category) {
        List<ProductDto> products = category.getProducts().stream().map(p -> productToDto(p)).collect(Collectors.toList());
        return new CategoryDto(category.getId(), category.getTitle(), products);
    }

}
