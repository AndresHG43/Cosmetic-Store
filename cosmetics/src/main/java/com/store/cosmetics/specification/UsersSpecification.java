package com.store.cosmetics.specification;

import com.store.cosmetics.entity.Users;
import org.springframework.data.jpa.domain.Specification;

public class UsersSpecification {
    public static Specification<Users> active(final boolean active) {
        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("active"), active);
    }

    public static Specification<Users> containsNormalized(String value, String field) {
        return (root, criteriaQuery, criteriaBuilder)
        -> NormalizeSpecification.normalizedContains(root.get(field), value, criteriaBuilder);
    }
}
