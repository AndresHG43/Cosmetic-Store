package com.store.cosmetics.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntryResponseDto {
    private Long id;
    private LocalDateTime date;
    private BigDecimal total;
    private Boolean isInitial;
    private Boolean active;

    private StatusAssociatedResponseDto status;

    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private LocalDateTime dateDeleted;

    private UsersAssociatedResponseDto usersCreated;
    private UsersAssociatedResponseDto usersUpdated;
    private UsersAssociatedResponseDto usersDeleted;
}
