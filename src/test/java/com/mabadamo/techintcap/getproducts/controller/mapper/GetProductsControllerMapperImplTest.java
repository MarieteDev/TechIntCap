package com.mabadamo.techintcap.getproducts.controller.mapper;

import com.mabadamo.techintcap.common.domain.product.Product;
import com.mabadamo.techintcap.common.domain.product.getproducts.GetProductsFilter;
import com.mabadamo.techintcap.common.domain.product.getproducts.GetProductsList;
import com.mabadamo.techintcap.getproducts.controller.model.request.GetProductsQueryParamsRequestDto;
import com.mabadamo.techintcap.getproducts.controller.model.response.GetProductsResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetProductsControllerMapperImplTest {

    private final GetProductsControllerMapperImpl mapper = Mappers.getMapper(GetProductsControllerMapperImpl.class);

    @Test
    void testMapToResponse() {
        // Given
        Product product = new Product().setId(1).setSku("SKU123").setPrice(BigDecimal.valueOf(100.0)).setDescription("Test product");

        GetProductsList productsList = new GetProductsList().setProducts(List.of(product));

        // When
        GetProductsResponseDto responseDto = mapper.mapToResponse(productsList);

        // Then
        assertNotNull(responseDto);
        assertNotNull(responseDto.getProducts());
        assertEquals(1, responseDto.getProducts().size());
        assertEquals("SKU123", responseDto.getProducts().get(0).getSku());
        assertEquals(BigDecimal.valueOf(100.0), responseDto.getProducts().get(0).getPrice());
    }

    @Test
    void testMapRequestToDomain() {
        // Given
        GetProductsQueryParamsRequestDto requestDto = new GetProductsQueryParamsRequestDto().setSortBy("price");
        requestDto.setDirection("DESC").setPage(1).setSize(10);
        // When
        GetProductsFilter filter = mapper.mapRequestToDomain(requestDto);

        // Then
        assertNotNull(filter);
        assertEquals(1, filter.getPage());
        assertEquals(10, filter.getSize());
        assertEquals("price", filter.getSortBy());
        assertEquals("DESC", filter.getDirection());
    }
}
