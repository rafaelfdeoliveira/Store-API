package com.rafael.productapi.repository;

import com.rafael.productapi.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class ProductRepositoryTests {

    @Mock
    private ProductRepository productRepository;

    @Test
    public void testFindAll() {
        List<Product> productsList = new ArrayList<>();
        productsList.add(new Product(UUID.randomUUID(), "1", 10.0F, 100, null));
        productsList.add(new Product(UUID.randomUUID(), "2", 20.0F, 50, null));
        productsList.add(new Product(UUID.randomUUID(), "3", 5.0F, 20, null));

        Mockito.when(productRepository.findAll()).thenReturn(Flux.fromStream(productsList.stream()));

        Flux<Product> productsResultsFlux = productRepository.findAll();

        List<Product> productsResultsList = productsResultsFlux.collectList().block();
        assert productsResultsList != null;
        Assertions.assertEquals(3, productsResultsList.size());
        Assertions.assertEquals(productsList, productsResultsList);
    }

    @Test
    public void testFindAllByCode() {
        List<String> codes = new ArrayList<>();
        codes.add("1");
        List<Product> productsList = new ArrayList<>();
        productsList.add(new Product(UUID.randomUUID(), "1", 10.0F, 100, null));

        Mockito.when(productRepository.findAllByCode(Mockito.any())).thenReturn(Flux.fromStream(productsList.stream()));

        Flux<Product> productsResultsFlux = productRepository.findAllByCode(codes.get(0));

        List<Product> productResultsList = productsResultsFlux.collectList().block();
        assert productResultsList != null;
        Assertions.assertEquals(1, productResultsList.size());
        Assertions.assertEquals(productsList, productResultsList);
    }
}
