package com.store.cosmetics.service.contract;

import com.store.cosmetics.entity.request.EntryRequestDto;
import com.store.cosmetics.entity.response.EntryResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import java.util.HashMap;

public interface IEntryService {
    EntryResponseDto createEntry(Authentication authentication) throws RuntimeException;
    EntryResponseDto registerEntry(EntryRequestDto entryRequestDto, Authentication authentication) throws RuntimeException;
    Page<EntryResponseDto> findAllEntrys(Pageable pageable, HashMap<String, Object> params) throws RuntimeException;
    EntryResponseDto findEntryById(Long idEntry) throws RuntimeException;
    EntryResponseDto updateEntry(Long idEntry, EntryRequestDto entryRequestDto, Authentication authentication) throws RuntimeException;
    void deleteEntry(Long idEntry, Authentication authentication) throws RuntimeException;
    EntryResponseDto reactivateEntry(Long idEntry, Authentication authentication) throws RuntimeException;
}
