package com.mabadamo.techintcap.getproducts.controller.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum GetProductsQuerySortByEnumDto {
    SKU("sku"),
    PRICE("price"),
    DESCRIPTION("description"),
    CATEGORY("category");

    private final String value;

    GetProductsQuerySortByEnumDto(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static GetProductsQuerySortByEnumDto fromValue(String value) {
        return Arrays.stream(GetProductsQuerySortByEnumDto.values())
                .filter(e -> e.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid sortBy value: " + value));
    }
}