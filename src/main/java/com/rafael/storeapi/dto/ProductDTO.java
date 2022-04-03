package com.rafael.storeapi.dto;

import com.rafael.storeapi.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDTO {
    private String code;
    private Float unityPrice;
    private Integer quantity;

    public static ProductDTO convert(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setCode(product.getCode());
        dto.setUnityPrice(product.getUnityPrice());
        dto.setQuantity(product.getQuantity());
        return dto;
    }

    public static List<ProductDTO> convert(List<Product> products) {
        return products
                .stream()
                .map(ProductDTO::convert)
                .toList();
    }
}
