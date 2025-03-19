package com.mabadamo.techintcap.common.adapter.repository.product;

import com.mabadamo.techintcap.common.adapter.repository.product.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Page<ProductEntity> findAllByCategory_Name(String category, Pageable pageable);
    List<ProductEntity> findAllByCategory_Name(String category, Sort sort);
}