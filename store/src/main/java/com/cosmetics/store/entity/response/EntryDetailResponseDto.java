package com.cosmetics.store.entity.response;

import com.cosmetics.store.entity.Entry;
import com.cosmetics.store.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntryDetailResponseDto {
    private Long id;
    private BigDecimal priceEntry;
    private Integer quantity;
    private Boolean active;

    private Entry entryId;
    private Product productId;

    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private LocalDateTime dateDeleted;

    private UsersAssociatedResponseDto usersCreated;
    private UsersAssociatedResponseDto usersUpdated;
    private UsersAssociatedResponseDto usersDeleted;
}
