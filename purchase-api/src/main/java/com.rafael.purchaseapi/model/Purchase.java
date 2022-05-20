package com.rafael.purchaseapi.model;

import com.rafael.purchaseapi.dto.PurchaseDTO;
import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column()
    private LocalDateTime date;

    @Column()
    private String cpf;

    @Column()
    private Float amount;

    @ManyToMany(mappedBy = "purchases", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> products;

    public static Purchase convert(PurchaseDTO dto) {
        Purchase purchase = new Purchase();
        purchase.setDate(dto.getDate());
        purchase.setCpf(dto.getCpf());
        purchase.setAmount(0F);
        purchase.setProducts(dto.getProducts()
                .stream()
                .map(productDTO -> {
                    purchase.setAmount(purchase.getAmount() + (productDTO.getQuantity() * productDTO.getUnityPrice()));
                    return Product.convert(productDTO, purchase);
                })
                .toList());
        return purchase;
    }
}
