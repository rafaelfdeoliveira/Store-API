package com.rafael.storeapi.dto;

import com.rafael.storeapi.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    public static List<ProductDTO> convert(List<Product> products) {
        return products
                .stream()
                .map(ProductDTO::convert)
                .toList();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ProductDTO other)) return false;

        if (!this.getCode().equals(other.getCode())) return false;
        if (!this.getUnityPrice().equals(other.getUnityPrice())) return false;
        if (!this.getQuantity().equals(other.getQuantity())) return false;
        return true;
    }
}
