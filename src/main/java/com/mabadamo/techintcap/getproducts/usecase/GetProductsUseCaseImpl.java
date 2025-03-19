package com.mabadamo.techintcap.getproducts.usecase;

import com.mabadamo.techintcap.common.domain.product.Product;
import com.mabadamo.techintcap.common.domain.product.getproducts.GetProductsFilter;
import com.mabadamo.techintcap.common.domain.product.getproducts.GetProductsList;
import com.mabadamo.techintcap.common.domain.product.getproducts.GetProductsQuerySortByEnum;
import com.mabadamo.techintcap.common.facade.getproducts.GetProductsFacade;
import com.mabadamo.techintcap.common.service.discount.ProductDiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class GetProductsUseCaseImpl implements GetProductsUseCase {
    private final GetProductsFacade getProductsFacade;
    private final ProductDiscountService productDiscountService;

    @Override
    public GetProductsList execute(GetProductsFilter getProductsFilter) {
        GetProductsList getProductsList = getProductsFacade.getProducts(getProductsFilter);
        getProductsList.setProducts(sortProducts(getProductsList.getProducts().stream().map(productDiscountService::processProductDiscount).toList(), getProductsFilter.getSortBy(), getProductsFilter.getDirection()));
        return getProductsList;
    }


    protected List<Product> sortProducts(List<Product> products, String sortBy, String direction) {
        List<Product> copyList = new ArrayList<>(products);
        if (Objects.isNull(sortBy)) {
            return products;
        }
        Comparator<Product> comparator = null;
        switch (GetProductsQuerySortByEnum.fromValue(sortBy)) {
            case SKU:
                comparator = Comparator.comparing(Product::getSku);
                break;
            case PRICE:
                comparator = Comparator.comparing(Product::getPrice);
                break;
            case DESCRIPTION:
                comparator = Comparator.comparing(Product::getDescription);
                break;
            case CATEGORY:
                comparator = Comparator.comparing(product -> product.getCategory().toString());
                break;
            default:
                break;
        }
        if ("desc".equalsIgnoreCase(direction)) {
            comparator = comparator.reversed();
        }

        copyList.sort(comparator);
        return copyList;
    }


}
