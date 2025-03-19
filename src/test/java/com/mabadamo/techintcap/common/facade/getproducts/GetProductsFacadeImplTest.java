package com.mabadamo.techintcap.common.facade.getproducts;

import com.mabadamo.techintcap.common.adapter.repository.product.ProductRepository;
import com.mabadamo.techintcap.common.adapter.repository.product.entity.ProductEntity;
import com.mabadamo.techintcap.common.domain.product.getproducts.GetProductsFilter;
import com.mabadamo.techintcap.common.domain.product.getproducts.GetProductsList;
import com.mabadamo.techintcap.common.facade.getproducts.mapper.GetProductsFacadeMapper;
import com.mabadamo.techintcap.common.facade.getproducts.mapper.GetProductsFacadeMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetProductsFacadeImplTest {

    @Mock
    private ProductRepository productRepository;

    @Spy
    private GetProductsFacadeMapper mapper = Mappers.getMapper(GetProductsFacadeMapperImpl.class);

    @InjectMocks
    private GetProductsFacadeImpl getProductsFacade;

    private List<ProductEntity> productEntities;

    @BeforeEach
    void setUp() {
        ProductEntity product1 = new ProductEntity()
                .setId(1)
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
    void testGetProducts_WithPagination() {
        GetProductsFilter filter = new GetProductsFilter().setSortBy("sku").setDirection("ASC");
        filter.setPage(0).setSize(10);

        Page<ProductEntity> productEntityPage = new PageImpl<>(productEntities, PageRequest.of(0, 10), 2);
        when(productRepository.findAll(any(PageRequest.class))).thenReturn(productEntityPage);

        GetProductsList result = getProductsFacade.getProducts(filter);

        assertNotNull(result);
        assertEquals(2, result.getProducts().size());
        verify(mapper).mapToDomain(productEntityPage);
    }

    @Test
    void testGetProducts_WithoutPagination() {
        GetProductsFilter filter = new GetProductsFilter()
                .setSortBy("sku")
                .setDirection("ASC");

        when(productRepository.findAll(any(Sort.class))).thenReturn(productEntities);

        GetProductsList result = getProductsFacade.getProducts(filter);

        assertNotNull(result);
        assertEquals(2, result.getProducts().size());
        verify(mapper).mapToDomain(productEntities);
    }

    @Test
    void testGetProducts_WithCategoryFilter() {
        GetProductsFilter filter = new GetProductsFilter()
                .setCategory("Electronics")
                .setSortBy("price")
                .setDirection("DESC");

        when(productRepository.findAllByCategory_Name(eq("Electronics"), any(Sort.class))).thenReturn(productEntities);

        GetProductsList result = getProductsFacade.getProducts(filter);

        assertNotNull(result);
        assertEquals(2, result.getProducts().size());
        verify(productRepository).findAllByCategory_Name(eq("Electronics"), any(Sort.class));
    }

    @Test
    void testGetProducts_WithNullValues() {
        GetProductsFilter filter = new GetProductsFilter();

        when(productRepository.findAll(any(Sort.class))).thenReturn(productEntities);

        GetProductsList result = getProductsFacade.getProducts(filter);

        assertNotNull(result);
        assertEquals(2, result.getProducts().size());
        verify(productRepository).findAll(any(Sort.class));
    }
}