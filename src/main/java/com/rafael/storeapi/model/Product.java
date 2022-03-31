package com.rafael.storeapi.model;

import com.rafael.storeapi.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column()
    private String code;

    @Column()
    private Float price;

    @Column()
    private Integer quantity;


    public static Product convert(ProductDTO dto) {
        Product product = new Product();
        product.setCode(dto.getCode());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        return product;
    }
}
