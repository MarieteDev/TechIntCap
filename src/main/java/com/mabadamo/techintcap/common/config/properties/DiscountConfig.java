package com.mabadamo.techintcap.common.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "techintcap.discount")
public class DiscountConfig {
    private Map<String, Double> category;  // Map of category: name -> discount
    private Map<String, SkuDetails> sku;   // Map of sku: name -> detail SKU

    @Data
    public static class SkuDetails {
        private String endsWith;
        private Double discount;  // Discount associated to SKU
    }
}