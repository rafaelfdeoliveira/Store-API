package com.rafael.productapi.model;

import com.rafael.productapi.dto.ProductDTO;
import com.rafael.productapi.dto.PurchaseDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Product {
    @Id
    private UUID id;
    private String code;
    private Float unityPrice;
    private Integer quantity;
    private List<PurchaseDTO> purchases;

    public static Product convert(ProductDTO dto) {
        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setCode(dto.getCode());
        product.setUnityPrice(dto.getUnityPrice());
        product.setQuantity(dto.getQuantity());
        return product;
    }

    public static Product convert(ProductDTO dto, PurchaseDTO purchase) {
        Product product = convert(dto);
        product.setPurchases(List.of(purchase));
        return product;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product)) return false;
        Product other = (Product) o;
        if (!this.getCode().equals(other.getCode())) return false;
        if (!this.getUnityPrice().equals(other.getUnityPrice())) return false;
        if (!this.getQuantity().equals(other.getQuantity())) return false;
        return true;
    }

}
