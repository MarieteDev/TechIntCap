package com.mabadamo.techintcap.common.domain.pagination;

import lombok.Data;

@Data
public class PaginationRequest {
    private Integer page;
    private Integer size;
}
