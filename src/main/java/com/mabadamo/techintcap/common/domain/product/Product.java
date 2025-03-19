package com.mabadamo.techintcap.common.domain.product;

import com.mabadamo.techintcap.common.domain.category.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private Integer id;

    private String sku;

    private BigDecimal price;

    private String description;

    private Category category;

    private String discount;
}