package com.mabadamo.techintcap.common.facade.getproducts.mapper;

import com.mabadamo.techintcap.common.adapter.repository.product.entity.ProductEntity;
import com.mabadamo.techintcap.common.domain.pagination.Pagination;
import com.mabadamo.techintcap.common.domain.product.Product;
import com.mabadamo.techintcap.common.domain.product.getproducts.GetProductsList;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface GetProductsFacadeMapperImpl extends GetProductsFacadeMapper {

    List<Product> productEntitiesToProducts(List<ProductEntity> productEntities);

    @Override
    default GetProductsList mapToDomain(List<ProductEntity> productEntities) {
        return new GetProductsList()
                .setProducts(productEntitiesToProducts(productEntities));
    }

    @Override
    default GetProductsList mapToDomain(Page<ProductEntity> productEntities) {
        return new GetProductsList()
                .setProducts(productEntitiesToProducts(productEntities.stream().toList()))
                .setPagination(mapPagination(productEntities));
    }

    default Pagination mapPagination(Page<ProductEntity> productEntities) {
        return new Pagination()
                .setPage(productEntities.getNumber())
                .setSize(productEntities.getSize())
                .setTotalElements(productEntities.getTotalElements())
                .setTotalPages(productEntities.getTotalPages());
    }
}
