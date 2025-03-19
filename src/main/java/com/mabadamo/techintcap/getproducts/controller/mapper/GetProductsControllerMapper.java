package com.mabadamo.techintcap.getproducts.controller.mapper;

import com.mabadamo.techintcap.common.domain.product.getproducts.GetProductsFilter;
import com.mabadamo.techintcap.common.domain.product.getproducts.GetProductsList;
import com.mabadamo.techintcap.getproducts.controller.model.request.GetProductsQueryParamsRequestDto;
import com.mabadamo.techintcap.getproducts.controller.model.response.GetProductsResponseDto;

public interface GetProductsControllerMapper {
    GetProductsResponseDto mapToResponse(GetProductsList products);
    GetProductsFilter mapRequestToDomain(GetProductsQueryParamsRequestDto request);
}
