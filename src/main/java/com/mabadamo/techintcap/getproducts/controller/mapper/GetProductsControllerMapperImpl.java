package com.mabadamo.techintcap.getproducts.controller.mapper;

import com.mabadamo.techintcap.common.domain.product.getproducts.GetProductsFilter;
import com.mabadamo.techintcap.common.domain.product.getproducts.GetProductsList;
import com.mabadamo.techintcap.getproducts.controller.model.request.GetProductsQueryParamsRequestDto;
import com.mabadamo.techintcap.getproducts.controller.model.response.GetProductsResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GetProductsControllerMapperImpl extends GetProductsControllerMapper {
    @Override
    GetProductsResponseDto mapToResponse(GetProductsList products);

    @Override
    GetProductsFilter mapRequestToDomain(GetProductsQueryParamsRequestDto request);

}
