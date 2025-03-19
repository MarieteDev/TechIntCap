package com.mabadamo.techintcap.common.domain.product.getproducts;

import com.mabadamo.techintcap.common.domain.pagination.PaginationRequest;
import lombok.Data;

@Data
public class GetProductsFilter extends PaginationRequest {
    private String category;
    private String sortBy;
    private String direction;
}
