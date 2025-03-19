package com.mabadamo.techintcap.getproducts.controller.model.response;

import com.mabadamo.techintcap.common.controller.model.PaginationResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "Response get products data structure")
@Data
public class GetProductsResponseDto {
    @Schema(description = "List of products", requiredMode = Schema.RequiredMode.REQUIRED)
    List<ProductDto> products;
    @Schema(description = "Metadata of pagination")
    PaginationResponseDto pagination;
}