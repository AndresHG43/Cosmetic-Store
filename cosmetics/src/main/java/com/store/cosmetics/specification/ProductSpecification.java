package com.store.cosmetics.specification;

import com.store.cosmetics.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Product> active(final boolean active) {
        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("active"), active);
    }

    public static Specification<Product> containsNormalized(String value, String field) {
        return (root, criteriaQuery, criteriaBuilder)
        -> NormalizeSpecification.normalizedContains(root.get(field), value, criteriaBuilder);
    }
}
