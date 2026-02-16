package com.store.cosmetics.service.contract;

import com.store.cosmetics.entity.request.EntryDetailRequestDto;
import com.store.cosmetics.entity.response.EntryDetailResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import java.util.HashMap;

public interface IEntryDetailService {
    EntryDetailResponseDto createEntryDetail(EntryDetailRequestDto entryDetailRequestDto, Authentication authentication) throws RuntimeException;
    Page<EntryDetailResponseDto> findAllEntryDetailByIdEntry(Long idEntry, Pageable pageable, HashMap<String, Object> params) throws RuntimeException;
    EntryDetailResponseDto findEntryDetailById(Long idEntryDetail) throws RuntimeException;
    EntryDetailResponseDto updateEntryDetail(Long idEntryDetail, EntryDetailRequestDto entryDetailRequestDto, Authentication authentication) throws RuntimeException;
    void deleteEntryDetail(Long idEntryDetail, Authentication authentication) throws RuntimeException;
    EntryDetailResponseDto reactivateEntryDetail(Long idEntryDetail, Authentication authentication) throws RuntimeException;
}
