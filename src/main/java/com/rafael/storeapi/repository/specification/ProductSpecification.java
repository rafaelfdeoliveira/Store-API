package com.rafael.storeapi.repository.specification;

import com.rafael.storeapi.model.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ProductSpecification {

    public static Specification<Product> filterByCodes(List<String> codes) {
        return (root, query, builder) -> builder.in(root.get("code")).value(codes);
    }
}
