package com.mabadamo.techintcap.getproducts.controller.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "Product data structure")
@Data
public class ProductDto {

    @Schema(description = "Id to identify product", example = "3", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;
    @Schema(description = "SKU identification", example = "SKU0009", requiredMode = Schema.RequiredMode.REQUIRED)
    private String sku;
    @Schema(description = "Current price of the product (included discount)", example = "56.00", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal price;
    @Schema(description = "Product description", example = "USB-C to USB Adapter", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
    @Schema(description = "Category of the product", requiredMode = Schema.RequiredMode.REQUIRED)
    private CategoryDto category;
    @Schema(description = "Current discount applied to the product", example = "15%")
    private String discount;
}