package com.rafael.storeapi.model;

import com.rafael.storeapi.dto.ProductDTO;
import javax.persistence.*;
import lombok.*;

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

    @Column
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
        product.setPurchases(List.of(purchase));
        return product;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product other)) return false;

        if (!this.getCode().equals(other.getCode())) return false;
        if (!this.getUnityPrice().equals(other.getUnityPrice())) return false;
        if (!this.getQuantity().equals(other.getQuantity())) return false;
        return true;
    }

}
