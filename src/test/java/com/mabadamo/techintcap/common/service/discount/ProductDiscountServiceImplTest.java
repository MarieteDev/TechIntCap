package com.mabadamo.techintcap.common.service.discount;

import com.mabadamo.techintcap.common.config.properties.DiscountConfig;
import com.mabadamo.techintcap.common.domain.category.Category;
import com.mabadamo.techintcap.common.domain.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductDiscountServiceImplTest {

    @Mock
    private DiscountConfig discountConfig;

    @InjectMocks
    private ProductDiscountServiceImpl productDiscountService;

    private Product product;

    @BeforeEach
    void setUp() {
        Category category = new Category().setId(1).setName("Electronics");
        MockitoAnnotations.openMocks(this);
        product = new Product().setSku("PROD123").setPrice(BigDecimal.valueOf(100.00)).setCategory(category);
    }

    @Test
    void testNoDiscountApplied_WhenNoMatchingCategoryOrSku() {
        when(discountConfig.getCategory()).thenReturn(Map.of());
        when(discountConfig.getSku()).thenReturn(Map.of());

        Product result = productDiscountService.processProductDiscount(product);

        assertEquals(BigDecimal.valueOf(100.00), result.getPrice());
        assertNull(result.getDiscount());
    }

    @ParameterizedTest
    @CsvSource({
            "Electronics, 0.10, 90.00",
            "Furniture, 0.15, 85.00"
    })
    void testCategoryDiscountApplied(String category, double discount, double expectedPrice) {
        product.getCategory().setName(category);
        when(discountConfig.getCategory()).thenReturn(Map.of(category, discount));
        when(discountConfig.getSku()).thenReturn(Map.of());

        Product result = productDiscountService.processProductDiscount(product);

        assertEquals(BigDecimal.valueOf(expectedPrice).setScale(2), result.getPrice());
    }

    @ParameterizedTest
    @CsvSource({
            "123, 0.05, 95.00",
            "456, 0.20, 80.00"
    })
    void testSkuDiscountApplied(String endsWith, double discount, double expectedPrice) {
        DiscountConfig.SkuDetails skuDetails = new DiscountConfig.SkuDetails().setEndsWith(endsWith).setDiscount(discount);
        product.setSku("PROD" + endsWith);

        when(discountConfig.getCategory()).thenReturn(Map.of());
        when(discountConfig.getSku()).thenReturn(Map.of("PROD" + endsWith, skuDetails));

        Product result = productDiscountService.processProductDiscount(product);

        assertEquals(BigDecimal.valueOf(expectedPrice).setScale(2), result.getPrice());
    }

    @Test
    void testHighestDiscountApplied_WhenBothCategoryAndSkuMatch() {
        product.getCategory().setName("Electronics");
        DiscountConfig.SkuDetails skuDetails = new DiscountConfig.SkuDetails().setEndsWith("123").setDiscount(0.15);

        when(discountConfig.getCategory()).thenReturn(Map.of("Electronics", 0.10));
        when(discountConfig.getSku()).thenReturn(Map.of("PROD123", skuDetails));

        Product result = productDiscountService.processProductDiscount(product);

        assertEquals(BigDecimal.valueOf(85.00).setScale(2), result.getPrice());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.05, 0.10, 0.25, 0.50})
    void testFormatDiscount(double discountValue) {
        BigDecimal discount = BigDecimal.valueOf(discountValue);
        String formatted = productDiscountService.formatDiscount(discount);

        assertTrue(formatted.matches("\\d+(\\.\\d+)?%"));
    }

    @ParameterizedTest
    @NullSource
    void testNullDiscountDoesNotThrowException(BigDecimal discount) {
        InternalError error = assertThrows(InternalError.class, () -> productDiscountService.formatDiscount(discount));
        assertEquals("Null discount not allowed", error.getMessage());
    }
}