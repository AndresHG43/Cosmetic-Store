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
public class SaleDetail implements Cloneable {
    @Id
    @SequenceGenerator(name = "sale_detail_sequence", sequenceName = "sale_detail_sequence", allocationSize = 1)
    @GeneratedValue(generator = "sale_detail_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "price_sale", precision = 8, scale = 2, nullable = false)
    private BigDecimal priceSale;
    @Column(nullable = false)
    private int quantity;
    @Column(precision = 8, scale = 2, nullable = false)
    private BigDecimal subtotal;
    @Column(nullable = false)
    private Boolean active;
    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;
    @Column(name = "date_updated")
    private LocalDateTime dateUpdated;
    @Column(name = "date_deleted")
    private LocalDateTime dateDeleted;

    @JoinColumn(name = "sale_id", nullable = false)
    @ManyToOne
    private Sale saleId;
    @JoinColumn(name = "product_id", nullable = false)
    @ManyToOne
    private Product productId;

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
