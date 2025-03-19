package com.mabadamo.techintcap.getproducts.usecase;

import com.mabadamo.techintcap.common.domain.product.getproducts.GetProductsFilter;
import com.mabadamo.techintcap.common.domain.product.getproducts.GetProductsList;

public interface GetProductsUseCase {
    GetProductsList execute(GetProductsFilter filter);
}
