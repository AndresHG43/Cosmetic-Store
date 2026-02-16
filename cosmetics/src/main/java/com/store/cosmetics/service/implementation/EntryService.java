package com.store.cosmetics.service.implementation;

import com.store.cosmetics.entity.Entry;
import com.store.cosmetics.entity.EntryDetail;
import com.store.cosmetics.entity.EntryStatus;
import com.store.cosmetics.entity.Users;
import com.store.cosmetics.entity.mapper.EntryMapper;
import com.store.cosmetics.entity.request.EntryRequestDto;
import com.store.cosmetics.entity.response.EntryResponseDto;
import com.store.cosmetics.repository.EntryRepository;
import com.store.cosmetics.repository.EntryStatusRepository;
import com.store.cosmetics.service.contract.IEntryService;
import com.store.cosmetics.specification.EntrySpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class EntryService implements IEntryService {
    private final EntryRepository entryRepository;
    private final EntryStatusRepository entryStatusRepository;

    @Override
    @Transactional
    public EntryResponseDto createEntry(Authentication authentication) throws RuntimeException {
        try {
            Users user = (Users) authentication.getPrincipal();
            EntryStatus entryStatus = entryStatusRepository.findById(1L).orElseThrow(
                    () -> new EntityNotFoundException("Entry Status not found"));

            Entry entry = new Entry();
            entry.setActive(true);
            entry.setStatus(entryStatus);
            entry.setUsersCreated(user);
            entryRepository.save(entry);

            EntryResponseDto entryResponseDto = new EntryResponseDto();
            EntryMapper.convertToResponseDto(entry, entryResponseDto);
            return entryResponseDto;
        } catch (Exception e) {
            log.error("[Entry] : Error trying to create the entry. ", e);
            throw new RuntimeException ("Error trying to create the entry");
        }
    }

    @Override
    @Transactional
    public EntryResponseDto registerEntry(EntryRequestDto entryRequestDto, Authentication authentication) throws RuntimeException {
        try {
            Users user = (Users) authentication.getPrincipal();
            EntryStatus entryStatus = entryStatusRepository.findById(2L).orElseThrow(
                    () -> new EntityNotFoundException("Entry Status not found"));

            Entry entry = entryRequestDto.toEntity();
            entry.setStatus(entryStatus);
            entry.setUsersUpdated(user);

            EntryDetail[] entryDetails = entryRequestDto.entryDetailsList();
            BigDecimal total = BigDecimal.ZERO;
            Arrays.stream(entryDetails).map(
                    entryDetail -> total.add(entryDetail.getSubtotal())
            );

            entry.setTotal(total);
            entryRepository.save(entry);

            EntryResponseDto entryResponseDto = new EntryResponseDto();
            EntryMapper.convertToResponseDto(entry, entryResponseDto);
            return entryResponseDto;
        } catch (Exception e) {
            log.error("[Entry] : Error trying to register the entry. ", e);
            throw new RuntimeException ("Error trying to register the entry");
        }
    }

    @Override
    public Page<EntryResponseDto> findAllEntrys(Pageable pageable, HashMap<String, Object> params) throws RuntimeException {
        try {
            Specification<Entry> spec = Specification.allOf(EntrySpecification.active(true));

            if (params.containsKey("starDate") && params.containsKey("endDate")) {
                if (!params.get("starDate").toString().equals("null")
                        && !params.get("endDate").toString().equals("null")) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDateTime starDate = LocalDate.parse((String) params.get("starDate"), formatter).atStartOfDay();
                    LocalDateTime endDate = LocalDate.parse((String) params.get("endDate"), formatter).atTime(23, 59, 59);
                    spec = spec.and(EntrySpecification.betweenDates(starDate, endDate));
                }
            } //TODO: Probar la especificacion fromToDates

            Page<Entry> entryPage = entryRepository.findAll(spec, pageable);

            return entryPage.map(entry -> {
                EntryResponseDto entryResponseDto = new EntryResponseDto();
                EntryMapper.convertToResponseDto(entry, entryResponseDto);
                return entryResponseDto;
            });
        } catch (Exception e) {
            log.error("[Entry] : Error trying to get entry's list. ", e);
            throw new RuntimeException ("Error trying to entry's list");
        }
    }

    @Override
    public EntryResponseDto findEntryById(Long idEntry) throws RuntimeException {
        try {
            Entry entry = entryRepository.findEntryByActiveTrueAndId(idEntry).orElseThrow(
                    () -> new EntityNotFoundException("Entry with id " + idEntry + " not found"));

            EntryResponseDto entryResponseDto = new EntryResponseDto();

            EntryMapper.convertToResponseDto(entry, entryResponseDto);
            return entryResponseDto;
        } catch (EntityNotFoundException e) {
            log.warn("[Entry] : Entry not found. ", e);
            throw e;
        } catch (Exception e) {
            log.error("[Entry] : Error trying to get entry by id. ", e);
            throw new RuntimeException ("Error trying to get entry by id");
        }
    }

    @Override
    @Transactional
    public EntryResponseDto updateEntry(Long idEntry, EntryRequestDto entryRequestDto, Authentication authentication) throws RuntimeException {
        try {
            Users user = (Users) authentication.getPrincipal();

            Entry entry = entryRepository.findEntryByActiveTrueAndId(idEntry).orElseThrow(
                    () -> new EntityNotFoundException("Entry with id " + idEntry + " not found"));

            EntryMapper.updateEntry(entryRequestDto, entry);
            entry.setUsersUpdated(user);

            EntryDetail[] entryDetails = entryRequestDto.entryDetailsList();
            BigDecimal total = BigDecimal.ZERO;
            Arrays.stream(entryDetails).map(
                    entryDetail -> total.add(entryDetail.getSubtotal())
            );

            entry.setTotal(total);
            entryRepository.save(entry);

            EntryResponseDto entryResponseDto = new EntryResponseDto();
            EntryMapper.convertToResponseDto(entry, entryResponseDto);
            return entryResponseDto;
        } catch (EntityNotFoundException e) {
            log.warn("[Entry] : Entry not found. ", e);
            throw e;
        } catch (Exception e) {
            log.error("[Entry] : Error trying to update the entry. ", e);
            throw new RuntimeException ("Error trying to update the entry");
        }
    }

    @Override
    @Transactional
    public void deleteEntry(Long idEntry, Authentication authentication) throws RuntimeException {
        try {
            LocalDateTime dateDeleted = LocalDateTime.now();

            Users user = (Users) authentication.getPrincipal();

            Entry entry = entryRepository.findEntryByActiveTrueAndId(idEntry).orElseThrow(
                    () -> new EntityNotFoundException("Entry with id " + idEntry + " not found"));

            entry.setActive(false);
            entry.setDateDeleted(dateDeleted);
            entry.setUsersDeleted(user);
            entryRepository.save(entry);
        } catch (EntityNotFoundException e) {
            log.warn("[Entry] : Entry not found. ", e);
            throw e;
        } catch (Exception e) {
            log.error("[Entry] : Error trying to delete the entry. ", e);
            throw new RuntimeException ("Error trying to delete the entry");
        }
    }

    @Override
    @Transactional
    public EntryResponseDto reactivateEntry(Long idEntry, Authentication authentication) throws RuntimeException {
        try {
            Users user = (Users) authentication.getPrincipal();

            Entry entry = entryRepository.findEntryByActiveFalseAndId(idEntry).orElseThrow(
                    () -> new EntityNotFoundException("Entry with id " + idEntry + " not found"));

            entry.setActive(true);
            entry.setDateDeleted(null);
            entry.setUsersDeleted(null);
            entry.setUsersUpdated(user);
            entryRepository.save(entry);

            EntryResponseDto entryResponseDto = new EntryResponseDto();
            EntryMapper.convertToResponseDto(entry, entryResponseDto);
            return entryResponseDto;
        } catch (EntityNotFoundException e) {
            log.warn("[Entry] : Entry not found. ", e);
            throw e;
        } catch (Exception e) {
            log.error("[Entry] : Error trying to activate the entry. ", e);
            throw new RuntimeException ("Error trying to activate the entry");
        }
    }
}
