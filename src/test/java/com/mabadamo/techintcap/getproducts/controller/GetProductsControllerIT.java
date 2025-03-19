package com.mabadamo.techintcap.getproducts.controller;

import com.mabadamo.techintcap.common.domain.product.getproducts.GetProductsFilter;
import com.mabadamo.techintcap.common.domain.product.getproducts.GetProductsList;
import com.mabadamo.techintcap.getproducts.controller.mapper.GetProductsControllerMapper;
import com.mabadamo.techintcap.getproducts.controller.model.response.GetProductsResponseDto;
import com.mabadamo.techintcap.getproducts.usecase.GetProductsUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GetProductsControllerImpl.class)
class GetProductsControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GetProductsUseCase useCase;

    @MockitoBean
    private GetProductsControllerMapper mapper;

    @Test
    void shouldReturnProductsSuccessfully() throws Exception {
        GetProductsFilter filter = new GetProductsFilter();
        GetProductsList productList = new GetProductsList().setProducts(Collections.emptyList());
        GetProductsResponseDto responseDto = new GetProductsResponseDto().setProducts(Collections.emptyList());

        Mockito.when(mapper.mapRequestToDomain(any())).thenReturn(filter);
        Mockito.when(useCase.execute(filter)).thenReturn(productList);
        Mockito.when(mapper.mapToResponse(productList)).thenReturn(responseDto);

        // Act & Assert
        mockMvc.perform(get("/v1/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products").isArray())
                .andExpect(jsonPath("$.products").isEmpty());
    }
}
