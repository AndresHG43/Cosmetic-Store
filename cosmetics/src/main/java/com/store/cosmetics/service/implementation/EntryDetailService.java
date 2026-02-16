package com.store.cosmetics.service.implementation;

import com.store.cosmetics.entity.Entry;
import com.store.cosmetics.entity.EntryDetail;
import com.store.cosmetics.entity.Users;
import com.store.cosmetics.entity.mapper.EntryDetailMapper;
import com.store.cosmetics.entity.request.EntryDetailRequestDto;
import com.store.cosmetics.entity.response.EntryDetailResponseDto;
import com.store.cosmetics.repository.EntryDetailRepository;
import com.store.cosmetics.repository.EntryRepository;
import com.store.cosmetics.service.contract.IEntryDetailService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class EntryDetailService implements IEntryDetailService {
    private final EntryDetailRepository entryDetailRepository;
    private final EntryRepository entryRepository;

    @Override
    @Transactional
    public EntryDetailResponseDto createEntryDetail(EntryDetailRequestDto entryDetailRequestDto, Authentication authentication) throws RuntimeException {
        try {
            Users user = (Users) authentication.getPrincipal();

            EntryDetail entryDetail = entryDetailRequestDto.toEntity();
            entryDetail.setActive(true);

            BigDecimal subtotal = entryDetail.getPriceEntry()
                    .multiply(BigDecimal.valueOf(
                            entryDetail.getQuantity()
                    ));
            entryDetail.setSubtotal(subtotal);
            entryDetail.setUsersCreated(user);
            entryDetailRepository.save(entryDetail);

            EntryDetailResponseDto entryDetailResponseDto = new EntryDetailResponseDto();
            EntryDetailMapper.convertToResponseDto(entryDetail, entryDetailResponseDto);
            return entryDetailResponseDto;
        } catch (Exception e) {
            log.error("[EntryDetail] : Error trying to create the entry detail. ", e);
            throw new RuntimeException ("Error trying to create the entry detail");
        }
    }

    @Override
    public Page<EntryDetailResponseDto> findAllEntryDetailByIdEntry(Long idEntry, Pageable pageable, HashMap<String, Object> params) throws RuntimeException {
        try {
            Entry entry = entryRepository.findEntryByActiveTrueAndId(idEntry).orElseThrow(
                    () -> new EntityNotFoundException("Entry with id " + idEntry + " not found"));

            Page<EntryDetail> entryDetailPage = entryDetailRepository.findAllByEntryId(entry, pageable);
            return entryDetailPage.map(entryDetail -> {
                EntryDetailResponseDto entryDetailResponseDto = new EntryDetailResponseDto();
                EntryDetailMapper.convertToResponseDto(entryDetail, entryDetailResponseDto);
                return entryDetailResponseDto;
            });
        } catch (Exception e) {
            log.error("[EntryDetail] : Error trying to get entry detail's list. ", e);
            throw new RuntimeException ("Error trying to entry detail's list");
        }
    }

    @Override
    public EntryDetailResponseDto findEntryDetailById(Long idEntryDetail) throws RuntimeException {
        try {
            EntryDetail entryDetail = entryDetailRepository.findEntryDetailByActiveTrueAndId(idEntryDetail).orElseThrow(
                    () -> new EntityNotFoundException("Entry Detail with id " + idEntryDetail + " not found"));

            EntryDetailResponseDto entryDetailResponseDto = new EntryDetailResponseDto();

            EntryDetailMapper.convertToResponseDto(entryDetail, entryDetailResponseDto);
            return entryDetailResponseDto;
        } catch (EntityNotFoundException e) {
            log.warn("[EntryDetail] : Entry Detail not found. ", e);
            throw e;
        } catch (Exception e) {
            log.error("[EntryDetail] : Error trying to get Entry Detail by id. ", e);
            throw new RuntimeException ("Error trying to get Entry Detail by id");
        }
    }

    @Override
    @Transactional
    public EntryDetailResponseDto updateEntryDetail(Long idEntryDetail, EntryDetailRequestDto entryDetailRequestDto, Authentication authentication) throws RuntimeException {
        try {
            Users user = (Users) authentication.getPrincipal();

            EntryDetail entryDetail = entryDetailRepository.findEntryDetailByActiveTrueAndId(idEntryDetail).orElseThrow(
                    () -> new EntityNotFoundException("Entry Detail with id " + idEntryDetail + " not found"));

            EntryDetailMapper.updateEntryDetail(entryDetailRequestDto, entryDetail);

            BigDecimal subtotal = entryDetail.getPriceEntry()
                    .multiply(BigDecimal.valueOf(
                            entryDetail.getQuantity()
                    ));
            entryDetail.setSubtotal(subtotal);
            entryDetail.setUsersUpdated(user);
            entryDetailRepository.save(entryDetail);

            EntryDetailResponseDto entryDetailResponseDto = new EntryDetailResponseDto();
            EntryDetailMapper.convertToResponseDto(entryDetail, entryDetailResponseDto);
            return entryDetailResponseDto;
        } catch (EntityNotFoundException e) {
            log.warn("[EntryDetail] : Entry Detail not found. ", e);
            throw e;
        } catch (Exception e) {
            log.error("[EntryDetail] : Error trying to update the Entry Detail. ", e);
            throw new RuntimeException ("Error trying to update the Entry Detail");
        }
    }

    @Override
    @Transactional
    public void deleteEntryDetail(Long idEntryDetail, Authentication authentication) throws RuntimeException {
        try {
            LocalDateTime dateDeleted = LocalDateTime.now();

            Users user = (Users) authentication.getPrincipal();

            EntryDetail entryDetail = entryDetailRepository.findEntryDetailByActiveTrueAndId(idEntryDetail).orElseThrow(
                    () -> new EntityNotFoundException("Entry Detail with id " + idEntryDetail + " not found"));

            entryDetail.setActive(false);
            entryDetail.setDateDeleted(dateDeleted);
            entryDetail.setUsersDeleted(user);
            entryDetailRepository.save(entryDetail);
        } catch (EntityNotFoundException e) {
            log.warn("[EntryDetail] : Entry Detail not found. ", e);
            throw e;
        } catch (Exception e) {
            log.error("[EntryDetail] : Error trying to delete the Entry Detail. ", e);
            throw new RuntimeException ("Error trying to delete the Entry Detail");
        }
    }

    @Override
    @Transactional
    public EntryDetailResponseDto reactivateEntryDetail(Long idEntryDetail, Authentication authentication) throws RuntimeException {
        try {
            Users user = (Users) authentication.getPrincipal();

            EntryDetail entryDetail = entryDetailRepository.findEntryDetailByActiveFalseAndId(idEntryDetail).orElseThrow(
                    () -> new EntityNotFoundException("Entry Detail with id " + idEntryDetail + " not found"));

            entryDetail.setActive(true);
            entryDetail.setDateDeleted(null);
            entryDetail.setUsersDeleted(null);
            entryDetail.setUsersUpdated(user);
            entryDetailRepository.save(entryDetail);

            EntryDetailResponseDto entryDetailResponseDto = new EntryDetailResponseDto();
            EntryDetailMapper.convertToResponseDto(entryDetail, entryDetailResponseDto);
            return entryDetailResponseDto;
        } catch (EntityNotFoundException e) {
            log.warn("[EntryDetail] : Entry Detail not found. ", e);
            throw e;
        } catch (Exception e) {
            log.error("[EntryDetail] : Error trying to activate the Entry Detail. ", e);
            throw new RuntimeException ("Error trying to activate the Entry Detail");
        }
    }

}
