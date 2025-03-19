package com.mabadamo.techintcap.getproducts.controller;

import com.mabadamo.techintcap.getproducts.controller.mapper.GetProductsControllerMapper;
import com.mabadamo.techintcap.getproducts.controller.model.request.GetProductsQueryParamsRequestDto;
import com.mabadamo.techintcap.getproducts.controller.model.response.GetProductsResponseDto;
import com.mabadamo.techintcap.getproducts.usecase.GetProductsUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetProductsControllerImpl implements GetProductsController {
    private final GetProductsUseCase useCase;
    private final GetProductsControllerMapper mapper;

    @GetMapping("/v1/products")
    public GetProductsResponseDto getProducts(@Valid @ParameterObject GetProductsQueryParamsRequestDto queryParams) {
        return mapper.mapToResponse(useCase.execute(mapper.mapRequestToDomain(queryParams)));
    }
}
