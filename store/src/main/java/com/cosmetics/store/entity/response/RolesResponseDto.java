package com.cosmetics.store.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolesResponseDto {
    private Long id;
    private String name;
    private String description;
    private Boolean active;

    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private LocalDateTime dateDeleted;

    private UsersAssociatedResponseDto usersCreated;
    private UsersAssociatedResponseDto usersUpdated;
    private UsersAssociatedResponseDto usersDeleted;
}
