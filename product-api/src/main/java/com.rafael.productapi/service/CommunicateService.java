package com.rafael.productapi.service;

import com.rafael.productapi.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@FeignClient("eureka-client-products")
public interface CommunicateService {

    @GetMapping("products")
    Flux<ProductDTO> listProducts(@RequestBody(required = false) List<String> codes);

    @PostMapping("products")
    Mono<ProductDTO> registerProduct(@RequestBody ProductDTO productDTO);

    @DeleteMapping("products")
    Flux<ProductDTO> deleteProducts(@RequestBody List<String> codes);

}
