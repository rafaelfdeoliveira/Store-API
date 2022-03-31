package com.rafael.storeapi.dto;

import com.rafael.storeapi.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private String code;
    private Float price;
    private Integer quantity;

    public static ProductDTO convert(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setCode(product.getCode());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());
        return dto;
    }
}
