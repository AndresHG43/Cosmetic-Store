package com.cosmetics.store.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "sale_status")
@Getter
@Setter
@NoArgsConstructor
public class SaleStatus implements Cloneable{
    @Id
    @SequenceGenerator(name = "sale_status_sequence", sequenceName = "sale_status_sequence", allocationSize = 1)
    @GeneratedValue(generator = "sale_status_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String status;

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
