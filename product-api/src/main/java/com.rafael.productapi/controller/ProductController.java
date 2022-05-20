package com.rafael.productapi.controller;


import com.rafael.productapi.dto.ProductDTO;
import com.rafael.productapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;

    @GetMapping("product")
    public Flux<ProductDTO> listProducts(@RequestBody(required = false) List<String> codes) {
        return productService.listAll(codes);
    }

    @PostMapping("product")
    public Mono<ProductDTO> registerProduct(@RequestBody ProductDTO productDTO) {
        return productService.registerProduct(productDTO);
    }

    @DeleteMapping("product")
    public Flux<ProductDTO> deleteProducts(@RequestBody List<String> codes) {
        return productService.deleteProducts(codes);
    }
}
