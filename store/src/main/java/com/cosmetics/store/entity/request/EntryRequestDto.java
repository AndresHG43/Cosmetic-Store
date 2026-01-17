package com.cosmetics.store.entity.request;

import com.cosmetics.store.entity.Entry;
import com.cosmetics.store.entity.EntryDetail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntryRequestDto {
    @NotBlank
    @NotNull
    private Entry entry;
    @NotBlank
    @NotNull
    private Boolean isInitial;

    @NotNull
    private EntryDetail[] entryDetails;

    public Entry toEntity() {
        entry.setIsInitial(isInitial);
        return entry;
    }

    public EntryDetail[] entryDetailsList() {
        return entryDetails;
    }
}
