package com.rafael.productapi.dto;

import com.rafael.productapi.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String code;
    private Float unityPrice;
    private Integer quantity;

    public static ProductDTO convert(Product product) {
        return new ProductDTO(product.getCode(), product.getUnityPrice(), product.getQuantity());
    }

    public static Flux<ProductDTO> convert(Flux<Product> products) {
        return products.map(ProductDTO::convert);
    }

    public static Mono<ProductDTO> convert(Mono<Product> product) {
        return product.map(ProductDTO::convert);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ProductDTO)) return false;
        ProductDTO other = (ProductDTO) o;
        if (!this.getCode().equals(other.getCode())) return false;
        if (!this.getUnityPrice().equals(other.getUnityPrice())) return false;
        if (!this.getQuantity().equals(other.getQuantity())) return false;
        return true;
    }
}
