package com.rafael.storeapi.repository.specification;

import com.rafael.storeapi.model.Purchase;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class PurchaseSpecification {
    public static Specification<Purchase> filterByCpfs(List<String> cpfList) {
        return (root, query, builder) -> builder.in(root.get("cpf")).value(cpfList);
    }
}
