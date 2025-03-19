package com.mabadamo.techintcap.common.domain.product.getproducts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum GetProductsQuerySortByEnum {
    SKU("sku"),
    PRICE("price"),
    DESCRIPTION("description"),
    CATEGORY("category");

    private final String value;

    GetProductsQuerySortByEnum(String value) {
        this.value = value;
    }

    @JsonValue // Define cómo se serializa el valor del Enum
    public String getValue() {
        return value;
    }

    @JsonCreator // Permite la conversión de String a Enum automáticamente
    public static GetProductsQuerySortByEnum fromValue(String value) {
        return Arrays.stream(GetProductsQuerySortByEnum.values())
                .filter(e -> e.value.equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}