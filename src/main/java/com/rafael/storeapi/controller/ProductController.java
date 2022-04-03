package com.rafael.storeapi.controller;

import com.rafael.storeapi.dto.ProductDTO;
import com.rafael.storeapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Page<ProductDTO> listProducts(@RequestBody(required = false) List<String> codes, Pageable pageable) {
        return productService.listAll(codes, pageable);
    }

    @PostMapping
    public ProductDTO registerProduct(@RequestBody ProductDTO productDTO) {
        return productService.registerProduct(productDTO);
    }

    @DeleteMapping
    public List<ProductDTO> deleteProducts(@RequestBody List<String> codes) {
        return productService.deleteProducts(codes);
    }
}
