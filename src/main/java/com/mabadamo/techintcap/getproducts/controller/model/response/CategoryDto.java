package com.mabadamo.techintcap.getproducts.controller.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Category data structure")
@Data
public class CategoryDto {

    @Schema(description = "Id to identify category", example = "3", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    @Schema(description = "Name of the category", example = "Electronics", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
}