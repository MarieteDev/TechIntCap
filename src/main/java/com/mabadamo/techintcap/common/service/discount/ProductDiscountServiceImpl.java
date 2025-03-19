package com.mabadamo.techintcap.common.service.discount;

import com.mabadamo.techintcap.common.config.properties.DiscountConfig;
import com.mabadamo.techintcap.common.domain.product.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Service
public class ProductDiscountServiceImpl implements ProductDiscountService {

    private final DiscountConfig discountConfig;

    public ProductDiscountServiceImpl(DiscountConfig discountConfig) {
        this.discountConfig = discountConfig;
    }

    @Override
    public Product processProductDiscount(Product product) {

        Set<BigDecimal> discounts = new HashSet<>();

        Map<String, Double> categoryDiscounts = discountConfig.getCategory();
        String categoryName = product.getCategory().getName();
        if (categoryDiscounts.containsKey(categoryName)) {
            discounts.add(BigDecimal.valueOf(categoryDiscounts.get(categoryName)));
        }


        Map<String, DiscountConfig.SkuDetails> skuDiscounts = discountConfig.getSku();
        for (Map.Entry<String, DiscountConfig.SkuDetails> entry : skuDiscounts.entrySet()) {
            DiscountConfig.SkuDetails skuDetails = entry.getValue();
            if (product.getSku().endsWith(skuDetails.getEndsWith())) {
                discounts.add(BigDecimal.valueOf(skuDetails.getDiscount()));
            }
        }


        if (discounts.isEmpty()) {
            return product;
        }


        BigDecimal highestDiscount = discounts.stream()
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);


        BigDecimal priceAfterDiscount = product.getPrice()
                .multiply(BigDecimal.ONE.subtract(highestDiscount))
                .setScale(2, RoundingMode.HALF_UP);


        product.setDiscount(formatDiscount(highestDiscount)).setPrice(priceAfterDiscount);

        return product;
    }

    protected String formatDiscount(BigDecimal discount) {

        if (Objects.isNull(discount)) {
            throw new InternalError("Null discount not allowed");
        }

        BigDecimal percentage = discount.multiply(new BigDecimal("100"));


        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(0);
        df.setGroupingUsed(false);


        return df.format(percentage) + "%";
    }
}
