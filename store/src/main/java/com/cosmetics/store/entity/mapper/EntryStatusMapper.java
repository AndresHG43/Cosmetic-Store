package com.cosmetics.store.entity.mapper;

import com.cosmetics.store.entity.EntryStatus;
import com.cosmetics.store.entity.response.StatusAssociatedResponseDto;

public class EntryStatusMapper {
    public static StatusAssociatedResponseDto convertToResponseAssociatedDto(EntryStatus entryStatus) {
        if (entryStatus == null) return null;

        return new StatusAssociatedResponseDto(
                entryStatus.getId(),
                entryStatus.getStatus()
        );
    }


}
