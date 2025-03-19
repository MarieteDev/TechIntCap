package com.mabadamo.techintcap.common.facade.getproducts;

import com.mabadamo.techintcap.common.adapter.repository.product.ProductRepository;
import com.mabadamo.techintcap.common.adapter.repository.product.entity.ProductEntity;
import com.mabadamo.techintcap.common.domain.product.getproducts.GetProductsFilter;
import com.mabadamo.techintcap.common.domain.product.getproducts.GetProductsList;
import com.mabadamo.techintcap.common.facade.getproducts.mapper.GetProductsFacadeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetProductsFacadeImpl implements GetProductsFacade {

    @Value("${techintcap.pagination.defaultSize}")
    private Integer defaultPageSize;

    private final ProductRepository productRepository;
    private final GetProductsFacadeMapper mapper;

    @Override
    public GetProductsList getProducts(GetProductsFilter getProductsFilter) {
        Sort.Direction direction = Optional.ofNullable(getProductsFilter.getDirection())
                .map(String::toUpperCase).map(Sort.Direction::fromString).orElse(Sort.Direction.ASC);
        Sort sort = Sort.by(direction, Optional.ofNullable(getProductsFilter.getSortBy()).orElse("sku"));
        if (Objects.isNull(getProductsFilter.getPage()) && Objects.isNull(getProductsFilter.getSize())) {
            return mapper.mapToDomain(getAllProducts(getProductsFilter, sort));
        }
        return mapper.mapToDomain(getProductsByPage(getProductsFilter, sort));
    }


    private List<ProductEntity> getAllProducts(GetProductsFilter getProductsFilter, Sort sort) {
        if (Objects.isNull(getProductsFilter.getCategory())) {
            return productRepository.findAll(sort);
        }
        return productRepository.findAllByCategory_Name(getProductsFilter.getCategory(), sort);
    }

    private Page<ProductEntity> getProductsByPage(GetProductsFilter getProductsFilter, Sort sort) {
        Pageable pageable = PageRequest.of(Optional.ofNullable(getProductsFilter.getPage()).orElse(0), Optional.ofNullable(getProductsFilter.getSize()).orElse(defaultPageSize), sort);
        if (Objects.isNull(getProductsFilter.getCategory())) {
            return productRepository.findAll(pageable);
        }
        return productRepository.findAllByCategory_Name(getProductsFilter.getCategory(), pageable);
    }
}
