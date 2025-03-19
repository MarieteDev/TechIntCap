package com.mabadamo.techintcap.getproducts.controller;

import com.mabadamo.techintcap.common.domain.product.Product;
import com.mabadamo.techintcap.common.domain.product.getproducts.GetProductsFilter;
import com.mabadamo.techintcap.common.domain.product.getproducts.GetProductsList;
import com.mabadamo.techintcap.getproducts.controller.mapper.GetProductsControllerMapper;
import com.mabadamo.techintcap.getproducts.controller.mapper.GetProductsControllerMapperImpl;
import com.mabadamo.techintcap.getproducts.controller.model.request.GetProductsQueryParamsRequestDto;
import com.mabadamo.techintcap.getproducts.controller.model.response.GetProductsResponseDto;
import com.mabadamo.techintcap.getproducts.usecase.GetProductsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetProductsControllerImplTest {

    @Mock
    private GetProductsUseCase useCase;

    @Spy
    private GetProductsControllerMapper mapper = Mappers.getMapper(GetProductsControllerMapperImpl.class);

    @InjectMocks
    private GetProductsControllerImpl controller;

    private GetProductsQueryParamsRequestDto queryParams;
    private GetProductsFilter filter;
    private GetProductsList productList;
    private Product product;

    @BeforeEach
    void setUp() {
        queryParams = new GetProductsQueryParamsRequestDto();
        filter = new GetProductsFilter();
        product = new Product();
        product.setSku("3");
        productList = new GetProductsList();
        productList.setProducts(List.of(product));
    }

    @Test
    void getProducts_ShouldReturnMappedResponse() {
        when(mapper.mapRequestToDomain(queryParams)).thenReturn(filter);
        when(useCase.execute(filter)).thenReturn(productList);

        GetProductsResponseDto result = controller.getProducts(queryParams);

        assertNotNull(result);
        assertEquals(product.getSku(), result.getProducts().getFirst().getSku());
        verify(mapper).mapRequestToDomain(queryParams);
        verify(useCase).execute(filter);
        verify(mapper).mapToResponse(productList);
    }
}