package com.cosmetics.store.specification;

import com.cosmetics.store.entity.Roles;
import org.springframework.data.jpa.domain.Specification;

public class RolesSpecification {
    public static Specification<Roles> active(final boolean active) {
        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("active"), active);
    }

    public static Specification<Roles> containsNormalized(String value, String field) {
        return (root, criteriaQuery, criteriaBuilder)
        -> NormalizeSpecification.normalizedContains(root.get(field), value, criteriaBuilder);
    }
}
