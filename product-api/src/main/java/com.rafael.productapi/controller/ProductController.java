package com.rafael.productapi.controller;


import com.rafael.productapi.dto.ProductDTO;
import com.rafael.productapi.service.CommunicateService;
import com.rafael.productapi.service.ProductService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController implements CommunicateService {

    private final ProductService productService;

    @Retry(name = "getProducts", fallbackMethod = "getProductsFallback")
    @CircuitBreaker(name = "getProducts", fallbackMethod = "getProductsFallback")
    @RateLimiter(name = "getProducts", fallbackMethod = "getProductsFallback")
    @GetMapping("products")
    public Flux<ProductDTO> listProducts(@RequestBody(required = false) List<String> codes) {
        return productService.listAll(codes);
    }
    @Retry(name = "registerProduct", fallbackMethod = "registerProductFallback")
    @CircuitBreaker(name = "registerProduct", fallbackMethod = "registerProductFallback")
    @RateLimiter(name = "registerProduct", fallbackMethod = "registerProductFallback")
    @PostMapping("products")
    public Mono<ProductDTO> registerProduct(@RequestBody ProductDTO productDTO) {
        return productService.registerProduct(productDTO);
    }

    @Retry(name = "deleteProducts", fallbackMethod = "deleteProductsFallback")
    @CircuitBreaker(name = "deleteProducts", fallbackMethod = "deleteProductsFallback")
    @RateLimiter(name = "deleteProducts", fallbackMethod = "deleteProductsFallback")
    @DeleteMapping("products")
    public Flux<ProductDTO> deleteProducts(@RequestBody List<String> codes) {
        return productService.deleteProducts(codes);
    }

    public Flux<ProductDTO> getProductsFallback(@RequestBody(required = false) List<String> codes, Exception e) {
        return Flux.error(new Error("Serviço indisponível. Tente novamente mais tarde."));
    }

    public Mono<ProductDTO> registerProductFallback(@RequestBody ProductDTO productDTO, Exception e) {
        return Mono.error(new Error("Serviço indisponível. Tente novamente mais tarde."));
    }

    public Flux<ProductDTO> deleteProductsFallback(@RequestBody List<String> codes, Exception e) {
        return Flux.error(new Error("Serviço indisponível. Tente novamente mais tarde."));
    }


}
