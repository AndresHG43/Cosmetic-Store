package com.cosmetics.store.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponseDto {
    private Long id;
    private String name;
    private String lastname;
    private String email;
    private String username;
    private Boolean active;

    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private LocalDateTime dateDeleted;
}
