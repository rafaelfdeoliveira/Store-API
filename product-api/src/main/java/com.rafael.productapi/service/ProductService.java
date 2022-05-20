package com.rafael.productapi.service;

import com.rafael.productapi.dto.ProductDTO;
import com.rafael.productapi.model.Product;
import com.rafael.productapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Flux<ProductDTO> listAll(List<String> codes) {
        if (codes == null) return productRepository.findAll().map(ProductDTO::convert);
        return productRepository
                .findAllByCode(Flux.fromStream(codes.stream()))
                .map(ProductDTO::convert);
    }

    public Mono<ProductDTO> registerProduct(ProductDTO productDTO) {
        Mono<Product> product = productRepository.save(Product.convert(productDTO));
        return Mono.just(ProductDTO.convert(product.block()));
    }

    public Flux<ProductDTO> deleteProducts(List<String> codes) {
        Flux<Product> products = productRepository.findAllByCode(Flux.fromStream(codes.stream()));
        productRepository.deleteAll(products);
        return ProductDTO.convert(products);
    }
}
