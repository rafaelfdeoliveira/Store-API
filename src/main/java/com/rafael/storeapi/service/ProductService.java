package com.rafael.storeapi.service;

import com.rafael.storeapi.dto.ProductDTO;
import com.rafael.storeapi.model.Product;
import com.rafael.storeapi.repository.ProductRepository;
import com.rafael.storeapi.repository.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<ProductDTO> listAll(List<String> codes, Pageable pageable) {
        if (codes == null) return productRepository.findAll(pageable).map(ProductDTO::convert);
        return productRepository
                .findAll(ProductSpecification.filterByCodes(codes), pageable)
                .map(ProductDTO::convert);
    }

    public ProductDTO registerProduct(ProductDTO productDTO) {
        Product product = productRepository.save(Product.convert(productDTO));
        return ProductDTO.convert(product);
    }

    public List<ProductDTO> deleteProducts(List<String> codes) {
        List<Product> products = productRepository.findAll(ProductSpecification.filterByCodes(codes));
        productRepository.deleteAll(products);
        return ProductDTO.convert(products);
    }
}
