package com.store.cosmetics.entity.mapper;

import com.store.cosmetics.entity.EntryStatus;
import com.store.cosmetics.entity.response.StatusAssociatedResponseDto;

public class EntryStatusMapper {
    public static StatusAssociatedResponseDto convertToResponseAssociatedDto(EntryStatus entryStatus) {
        if (entryStatus == null) return null;

        return new StatusAssociatedResponseDto(
                entryStatus.getId(),
                entryStatus.getStatus()
        );
    }


}
