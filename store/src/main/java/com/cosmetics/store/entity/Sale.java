package com.cosmetics.store.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Sale implements Cloneable {
    @Id
    @SequenceGenerator(name = "sale_sequence", sequenceName = "sale_sequence", allocationSize = 1)
    @GeneratedValue(generator = "sale_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;
    private LocalDateTime date;
    @Column(precision = 8, scale = 2)
    private BigDecimal total;
    @Column(nullable = false)
    private Boolean active;
    @JoinColumn(name = "status", nullable = false)
    @ManyToOne(optional = false)
    private SaleStatus status;

    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;
    @Column(name = "date_updated")
    private LocalDateTime dateUpdated;
    @Column(name = "date_deleted")
    private LocalDateTime dateDeleted;

    @JoinColumn(name = "user_created", nullable = false)
    @ManyToOne(optional = false)
    private Users usersCreated;
    @JoinColumn(name = "user_updated")
    @ManyToOne
    private Users usersUpdated;
    @JoinColumn(name = "user_deleted")
    @ManyToOne
    private Users usersDeleted;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
