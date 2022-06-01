package com.rafael.purchaseapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rafael.purchaseapi.model.Purchase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PurchaseDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;
    private String cpf;
    private Float ammount;
    private String productCode;

    public static PurchaseDTO convert(Purchase purchase) {
        PurchaseDTO dto = new PurchaseDTO();
        dto.setDate(purchase.getDate());
        dto.setCpf(purchase.getCpf());
        dto.setAmmount(purchase.getAmount());
        dto.setProductCode(purchase.getProductCode());
        return dto;
    }
}
