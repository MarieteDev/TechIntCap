package com.mabadamo.techintcap.getproducts.usecase;

import com.mabadamo.techintcap.common.domain.category.Category;
import com.mabadamo.techintcap.common.domain.product.Product;
import com.mabadamo.techintcap.common.domain.product.getproducts.GetProductsFilter;
import com.mabadamo.techintcap.common.domain.product.getproducts.GetProductsList;
import com.mabadamo.techintcap.common.facade.getproducts.GetProductsFacade;
import com.mabadamo.techintcap.common.service.discount.ProductDiscountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetProductsUseCaseImplTest {

    @Mock
    private GetProductsFacade getProductsFacade;

    @Mock
    private ProductDiscountService productDiscountService;

    @InjectMocks
    private GetProductsUseCaseImpl getProductsUseCase;

    private Product product1;
    private Product product2;
    private GetProductsFilter filter;
    private GetProductsList getProductsList;

    @BeforeEach
    void setUp() {
        Result result = getProducts();
        product1 = result.product1;
        product2 = result.product2;

        filter = new GetProductsFilter();
        getProductsList = new GetProductsList();
        getProductsList.setProducts(List.of(product1, product2));
    }

    @Test
    void testExecute() {
        when(getProductsFacade.getProducts(filter)).thenReturn(getProductsList);
        when(productDiscountService.processProductDiscount(any(Product.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        GetProductsList result = getProductsUseCase.execute(filter);

        assertNotNull(result);
        assertEquals(2, result.getProducts().size());
        verify(getProductsFacade).getProducts(filter);
        verify(productDiscountService, times(2)).processProductDiscount(any(Product.class));
    }

    @ParameterizedTest
    @MethodSource("provideSortingTestCases")
    void testSortProducts(String sortBy, String direction, List<Product> expectedOrder) {
        List<Product> sortedProducts = getProductsUseCase.sortProducts(List.of(product1, product2), sortBy, direction);
        assertEquals(expectedOrder, sortedProducts);
    }

    private static Stream<Arguments> provideSortingTestCases() {
        Result result = getProducts();
        return Stream.of(
                Arguments.of("sku", "asc", List.of(result.product1(), result.product2())),
                Arguments.of("sku", "desc", List.of(result.product2(), result.product1())),
                Arguments.of("price", "asc", List.of(result.product1(), result.product2())),
                Arguments.of("price", "desc", List.of(result.product2(), result.product1())),
                Arguments.of("description", "asc", List.of(result.product1(), result.product2())),
                Arguments.of("description", "desc", List.of(result.product2(), result.product1()))
        );
    }

    private static Result getProducts() {
        Category category = new Category().setName("Electronics");
        Category category1 = new Category().setName("Clothing");
        Product product1 = new Product().setId(1)
                .setSku("SKU1")
                .setPrice(BigDecimal.valueOf(100.0))
                .setDescription("Description 1")
                .setCategory(category)
                .setDiscount("10%");
        Product product2 = new Product()
                .setId(2)
                .setSku("SKU2")
                .setPrice(BigDecimal.valueOf(200.0))
                .setDescription("Description 2")
                .setCategory(category1)
                .setDiscount("5%");
        Result result = new Result(product1, product2);
        return result;
    }

    private record Result(Product product1, Product product2) {
    }
}