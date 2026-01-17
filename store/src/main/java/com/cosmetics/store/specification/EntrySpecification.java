package com.cosmetics.store.specification;

import com.cosmetics.store.entity.Entry;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class EntrySpecification {
    public static Specification<Entry> active(final boolean active) {
        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("active"), active);
    }

    public static Specification<Entry> betweenDates(LocalDateTime starDate, LocalDateTime endDate) {
        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.between(root.get("dateCreated"), starDate, endDate);
    }

    public static Specification<Entry> fromToDates(LocalDateTime starDate, LocalDateTime endDate) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicateFrom = criteriaBuilder.greaterThanOrEqualTo(root.get("dateCreated"), starDate);
            Predicate predicateTo = criteriaBuilder.lessThanOrEqualTo(root.get("dateCreated"), endDate);
            return criteriaBuilder.and(predicateFrom, predicateTo);
        };
    }
}
