package com.cosmetics.store.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean active;

    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private LocalDateTime dateDeleted;

    private UsersAssociatedResponseDto usersCreated;
    private UsersAssociatedResponseDto usersUpdated;
    private UsersAssociatedResponseDto usersDeleted;
}
