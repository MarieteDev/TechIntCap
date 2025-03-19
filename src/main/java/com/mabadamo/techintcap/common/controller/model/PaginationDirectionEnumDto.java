package com.mabadamo.techintcap.common.controller.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum PaginationDirectionEnumDto {
    ASC("asc"),
    DESC("desc");

    private final String value;

    PaginationDirectionEnumDto(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static PaginationDirectionEnumDto fromValue(String value) {
        return Arrays.stream(PaginationDirectionEnumDto.values())
                .filter(e -> e.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid sortBy value: " + value));
    }
}
