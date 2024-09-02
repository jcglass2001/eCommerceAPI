package com.jcg.dreamshops.dto.request;

import com.jcg.dreamshops.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
public class UpdateProductRequest {
    private String name;
    private String brand;
    private String description;
    private Category category;
    private BigDecimal price;
    private int inventory;
}
