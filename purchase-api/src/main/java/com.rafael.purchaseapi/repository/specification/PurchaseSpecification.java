package com.rafael.purchaseapi.repository.specification;

import com.rafael.purchaseapi.model.Purchase;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class PurchaseSpecification {
    public static Specification<Purchase> filterByCpfs(List<String> cpfList) {
        return (root, query, builder) -> builder.in(root.get("cpf")).value(cpfList);
    }
}
