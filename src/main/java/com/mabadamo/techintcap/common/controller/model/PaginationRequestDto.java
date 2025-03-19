package com.mabadamo.techintcap.common.controller.model;

import com.mabadamo.techintcap.common.controller.validation.ValueOfEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PaginationRequestDto {
    @Schema(description = "Indicate the number of the page to return (starts from 0)", example = "0")
    private Integer page;
    @Schema(description = "Size of each page", example = "5")
    private Integer size;
    @Schema(description = "Order of the result (desc/asc)", example = "desc")
    @ValueOfEnum(enumClass = PaginationDirectionEnumDto.class, message = "Invalid direction value")
    private String direction;
}
