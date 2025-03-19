package com.mabadamo.techintcap.common.domain.pagination;

import lombok.Data;

@Data
public class Pagination {
    private Integer page;
    private Integer size;
    private Integer totalPages;
    private Long totalElements;

}
