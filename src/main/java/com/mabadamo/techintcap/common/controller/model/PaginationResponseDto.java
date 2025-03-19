package com.mabadamo.techintcap.common.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Pagination data structure")

@Data
public class PaginationResponseDto {
    @Schema(description = "Current page position", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer page;
    @Schema(description = "Current page size, by default 10", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer size;
    @Schema(description = "Current total pages of products", example = "5", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer totalPages;
    @Schema(description = "Total number of products available", example = "100", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long totalElements;

}
