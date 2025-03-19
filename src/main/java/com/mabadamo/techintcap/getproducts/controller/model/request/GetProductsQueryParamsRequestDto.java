package com.mabadamo.techintcap.getproducts.controller.model.request;

import com.mabadamo.techintcap.common.controller.model.PaginationRequestDto;
import com.mabadamo.techintcap.common.controller.validation.ValueOfEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Query params for get products list")
@Data
public class GetProductsQueryParamsRequestDto extends PaginationRequestDto {

    @Schema(description = "Filter products by category", example = "Electronics")
    private String category;
    @Schema(description = "Sorts the product by specified field", example = "price")
    @ValueOfEnum(enumClass = GetProductsQuerySortByEnumDto.class, message = "Invalid sortBy value")
    private String sortBy;
}
