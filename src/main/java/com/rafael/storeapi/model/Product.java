package com.rafael.storeapi.model;

import com.rafael.storeapi.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @Column(unique = true)
    private String code;

    @Column()
    private Float unityPrice;

    @Column()
    private Integer quantity;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "purchase_product",
            joinColumns = { @JoinColumn(name = "id_product") },
            inverseJoinColumns = { @JoinColumn(name = "id_purchase") }
    )
    private List<Purchase> purchases;

    public static Product convert(ProductDTO dto) {
        Product product = new Product();
        product.setCode(dto.getCode());
        product.setUnityPrice(dto.getUnityPrice());
        product.setQuantity(dto.getQuantity());
        return product;
    }

    public static Product convert(ProductDTO dto, Purchase purchase) {
        Product product = convert(dto);
        List<Purchase> purchases = new ArrayList<>();
        purchases.add(purchase);
        product.setPurchases(purchases);
        return product;
    }

}
