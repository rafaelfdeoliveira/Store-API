package com.rafael.productapi.repository;

import com.rafael.productapi.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ProductRepositoryTests {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testFindAll() {
        List<Product> productsList = new ArrayList<>();
        productsList.add(new Product(null, "1", 10.0F, 100, null));
        productsList.add(new Product(null, "2", 20.0F, 50, null));
        productsList.add(new Product(null, "3", 5.0F, 20, null));

        Flux<Product> productsResults = productRepository.findAll();

        Assertions.assertEquals(3L, productsResults.count().block());
        Assertions.assertEquals(productsList, productsResults.collectList().block());
    }

    @Test
    public void testFindAllWithSpecificationByCodes() {
        List<String> codes = new ArrayList<>();
        codes.add("1");
        codes.add("2");
        List<Product> productsList = new ArrayList<>();
        productsList.add(new Product(null, "1", 10.0F, 100, null));
        productsList.add(new Product(null, "2", 20.0F, 50, null));

        Flux<Product> productsResults = productRepository.findAllByCode(Flux.fromStream(codes.stream()));

        Assertions.assertEquals(2L, productsResults.count().block());
        Assertions.assertEquals(productsList, productsResults.collectList().block());
    }
}
