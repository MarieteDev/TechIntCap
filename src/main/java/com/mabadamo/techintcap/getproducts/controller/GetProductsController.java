package com.mabadamo.techintcap.getproducts.controller;

import com.mabadamo.techintcap.getproducts.controller.model.request.GetProductsQueryParamsRequestDto;
import com.mabadamo.techintcap.getproducts.controller.model.response.GetProductsResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;

@Tag(name = "Products")
public interface GetProductsController {

    @Operation(summary = "Get list of available products",
            responses = @ApiResponse(description = "Operation successful",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = GetProductsResponseDto.class), mediaType = MediaType.APPLICATION_JSON_VALUE)))
    GetProductsResponseDto getProducts(GetProductsQueryParamsRequestDto params);
}
