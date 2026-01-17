package com.cosmetics.store.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersAssociatedResponseDto {
    private Long id;
    private String name;
    private String lastname;
}
