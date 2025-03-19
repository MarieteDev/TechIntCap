package com.mabadamo.techintcap.common.adapter.repository.product;

import com.mabadamo.techintcap.common.adapter.repository.product.entity.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ProductRepositoryIT {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldFindAllByCategoryName_WithPagination() {
        Page<ProductEntity> page = productRepository.findAllByCategory_Name("Electronics", PageRequest.of(0, 2));

        assertThat(page.getTotalElements()).isEqualTo(10);
        assertEquals("SKU0001", page.getContent().getFirst().getSku());
        assertEquals("SKU0002", page.getContent().get(1).getSku());
    }

    @Test
    void shouldFindAllByCategoryName_WithSorting() {
        List<ProductEntity> products = productRepository.findAllByCategory_Name("Electronics", Sort.by("price").descending());

        assertThat(products).hasSize(10);
        assertThat(products.get(0).getSku()).isEqualTo("SKU0002"); // Laptop (1000) debería estar primero
    }
}
