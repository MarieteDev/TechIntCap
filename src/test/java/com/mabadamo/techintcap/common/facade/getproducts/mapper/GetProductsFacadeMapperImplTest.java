package com.mabadamo.techintcap.common.facade.getproducts.mapper;

import com.mabadamo.techintcap.common.adapter.repository.product.entity.ProductEntity;
import com.mabadamo.techintcap.common.domain.pagination.Pagination;
import com.mabadamo.techintcap.common.domain.product.getproducts.GetProductsList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class GetProductsFacadeMapperImplTest {

    @InjectMocks
    private GetProductsFacadeMapperImpl mapper = Mappers.getMapper(GetProductsFacadeMapperImpl.class);

    private List<ProductEntity> productEntities;

    @BeforeEach
    void setUp() {
        ProductEntity product1 = new ProductEntity().setId(1)
                .setSku("SKU1")
                .setPrice(BigDecimal.valueOf(100.0))
                .setDescription("Description 1");

        ProductEntity product2 = new ProductEntity()
                .setId(2)
                .setSku("SKU2")
                .setPrice(BigDecimal.valueOf(200.0))
                .setDescription("Description 2");

        productEntities = List.of(product1, product2);
    }

    @Test
    void testMapToDomain_WithList() {
        GetProductsList result = mapper.mapToDomain(productEntities);

        assertNotNull(result);
        assertNotNull(result.getProducts());
        assertEquals(2, result.getProducts().size());
        assertEquals("SKU1", result.getProducts().get(0).getSku());
    }

    @Test
    void testMapToDomain_WithPage() {
        Page<ProductEntity> productEntityPage = new PageImpl<>(productEntities, PageRequest.of(0, 10), 2);
        GetProductsList result = mapper.mapToDomain(productEntityPage);

        assertNotNull(result);
        assertNotNull(result.getProducts());
        assertEquals(2, result.getProducts().size());
        assertEquals("SKU2", result.getProducts().get(1).getSku());

        Pagination pagination = result.getPagination();
        assertNotNull(pagination);
        assertEquals(0, pagination.getPage());
        assertEquals(10, pagination.getSize());
        assertEquals(2, pagination.getTotalElements());
        assertEquals(1, pagination.getTotalPages());
    }
}