package com.rafael.purchaseapi.model;

import com.rafael.purchaseapi.dto.PurchaseDTO;
import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @Column()
    private String productCode;

    public static Purchase convert(PurchaseDTO dto) {
        Purchase purchase = new Purchase();
        purchase.setDate(dto.getDate());
        purchase.setCpf(dto.getCpf());
        purchase.setAmount(dto.getAmmount());
        purchase.setProductCode(dto.getProductCode());
        return purchase;
    }
}
