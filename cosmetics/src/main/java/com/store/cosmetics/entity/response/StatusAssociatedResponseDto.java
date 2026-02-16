package com.store.cosmetics.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusAssociatedResponseDto {
    private Long id;
    private String status;
}
