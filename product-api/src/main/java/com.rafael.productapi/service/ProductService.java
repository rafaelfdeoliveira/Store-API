package com.rafael.productapi.service;

import com.rafael.productapi.dto.ProductDTO;
import com.rafael.productapi.model.Product;
import com.rafael.productapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Flux<ProductDTO> listAll(List<String> codes) {
        if (codes == null) return productRepository.findAll().map(ProductDTO::convert);
        Flux<Product> productsFlux = Flux.empty();
        for (String code: codes) {
            productsFlux = productsFlux.concatWith(productRepository.findAllByCode(code));
        }
        return productsFlux.map(ProductDTO::convert);
    }

    public Mono<ProductDTO> registerProduct(ProductDTO productDTO) {
        Mono<Product> product = productRepository.save(Product.convert(productDTO));
        return ProductDTO.convert(product);
    }

    public Flux<ProductDTO> deleteProducts(List<String> codes) {
        Flux<Product> productsFlux = Flux.empty();
        for (String code : codes) {
            productsFlux = productsFlux.concatWith(productRepository.findAllByCode(code));
        }
        productRepository.deleteAll(productsFlux).delaySubscription(Duration.ofSeconds(1)).subscribe();
        return ProductDTO.convert(productsFlux);
    }
}
