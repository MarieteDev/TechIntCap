package com.mabadamo.techintcap.common.domain.product.getproducts;

import com.mabadamo.techintcap.common.domain.pagination.Pagination;
import com.mabadamo.techintcap.common.domain.product.Product;
import lombok.Data;

import java.util.List;

@Data
public class GetProductsList {
    List<Product> products;
    Pagination pagination;
}
