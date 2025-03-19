package com.mabadamo.techintcap.common.facade.getproducts.mapper;

import com.mabadamo.techintcap.common.adapter.repository.product.entity.ProductEntity;
import com.mabadamo.techintcap.common.domain.product.getproducts.GetProductsList;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GetProductsFacadeMapper {

    GetProductsList mapToDomain(List<ProductEntity> productEntities);
    GetProductsList mapToDomain(Page<ProductEntity> productEntities);
}
