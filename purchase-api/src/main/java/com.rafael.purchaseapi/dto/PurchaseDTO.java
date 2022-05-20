package com.rafael.purchaseapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rafael.purchaseapi.model.Purchase;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PurchaseDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;
    private String cpf;
    private List<ProductDTO> products;

    public static PurchaseDTO convert(Purchase purchase) {
        PurchaseDTO dto = new PurchaseDTO();
        dto.setDate(purchase.getDate());
        dto.setCpf(purchase.getCpf());
        dto.setProducts(purchase.getProducts()
                .stream()
                .map(ProductDTO::convert)
                .toList());
        return dto;
    }
}
