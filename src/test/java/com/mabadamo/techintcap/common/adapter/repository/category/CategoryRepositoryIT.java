package com.mabadamo.techintcap.common.adapter.repository.category;

import com.mabadamo.techintcap.common.adapter.repository.category.entity.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CategoryRepositoryIT {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void shouldLoadCategoriesFromFlyway() {
        // Act
        List<CategoryEntity> categories = categoryRepository.findAll();

        // Assert
        assertThat(categories).hasSize(10);
    }

    @Test
    void shouldFindCategoryByName() {
        // Act
        Optional<CategoryEntity> category = categoryRepository.findById(1L);

        // Assert
        assertThat(category).isPresent();
        assertThat(category.get().getName()).isEqualTo("Electronics");
    }
}
