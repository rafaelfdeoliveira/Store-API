package com.rafael.storeapi.repository;

import com.rafael.storeapi.model.Product;
import com.rafael.storeapi.repository.specification.ProductSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ProductRepositoryTests {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testFindAll() {
        Pageable pageable = PageRequest.of(0, 5);
        List<Product> productsList = new ArrayList<>();
        productsList.add(new Product(null, "1", 10.0F, 100, null));
        productsList.add(new Product(null, "2", 20.0F, 50, null));
        productsList.add(new Product(null, "3", 5.0F, 20, null));

        Page<Product> productsResultsPage = productRepository.findAll(pageable);

        Assertions.assertEquals(3, productsResultsPage.getTotalElements());
        Assertions.assertEquals(productsList, productsResultsPage.getContent());
    }

    @Test
    public void testFindAllWithSpecificationByCodes() {
        Pageable pageable = PageRequest.of(0, 5);
        List<String> codes = new ArrayList<>();
        codes.add("1");
        codes.add("2");
        List<Product> productsList = new ArrayList<>();
        productsList.add(new Product(null, "1", 10.0F, 100, null));
        productsList.add(new Product(null, "2", 20.0F, 50, null));

        Page<Product> productsResultsPage = productRepository.findAll(ProductSpecification.filterByCodes(codes), pageable);

        Assertions.assertEquals(2, productsResultsPage.getTotalElements());
        Assertions.assertEquals(productsList, productsResultsPage.getContent());
    }
}
