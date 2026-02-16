package com.store.cosmetics.entity.request;

import com.store.cosmetics.entity.Entry;
import com.store.cosmetics.entity.EntryDetail;
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
