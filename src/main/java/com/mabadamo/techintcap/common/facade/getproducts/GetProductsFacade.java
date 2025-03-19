package com.mabadamo.techintcap.common.facade.getproducts;

import com.mabadamo.techintcap.common.domain.product.getproducts.GetProductsFilter;
import com.mabadamo.techintcap.common.domain.product.getproducts.GetProductsList;

public interface GetProductsFacade {
    GetProductsList getProducts(GetProductsFilter getProductsFilter);
}
