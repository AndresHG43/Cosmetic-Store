package com.cosmetics.store.controller;

import com.cosmetics.store.config.ApiConfig;
import com.cosmetics.store.entity.request.EntryDetailRequestDto;
import com.cosmetics.store.service.contract.IEntryDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(ApiConfig.API_BASE_PATH + "/entry-detail")
@RequiredArgsConstructor
public class EntryDetailController extends Controller {
    private final IEntryDetailService entryDetailService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN')")
    public ResponseEntity<?> createEntryDetail(@RequestBody @Valid EntryDetailRequestDto entryDetailRequestDto,
                                         Authentication authentication) {
        return ResponseEntity
                .ok(
                        message.ok(
                                entryDetailService.createEntryDetail(entryDetailRequestDto, authentication)
                        )
                );
    }

    @GetMapping(value = "/{idEntry}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN', 'USER')")
    public ResponseEntity<?> getPaginatedAllEntryDetailByIdEntry(@PathVariable Long idEntry,
                                                     @RequestParam HashMap<String, Object> params) {
        Pageable pageable = getPageable(params);
        return ResponseEntity
                .ok(
                        message.ok(
                                entryDetailService.findAllEntryDetailByIdEntry(idEntry, pageable, params)
                        )
                );
    }

    @GetMapping(value = "/{idEntryDetail}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN', 'USER')")
    public ResponseEntity<?> getEntryDetailById(@PathVariable Long idEntryDetail) {
        return ResponseEntity
                .ok(
                        message.ok(
                                entryDetailService.findEntryDetailById(idEntryDetail)
                        )
                );
    }

    @PutMapping(value = "/{idEntryDetail}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN')")
    public ResponseEntity<?> updateEntryDetail(@PathVariable Long idEntryDetail,
                                           @RequestBody @Valid EntryDetailRequestDto entryDetailRequestDto,
                                           Authentication authentication) {
        return ResponseEntity
                .ok(
                        message.ok(
                                entryDetailService.updateEntryDetail(idEntryDetail, entryDetailRequestDto, authentication)
                        )
                );
    }

    @DeleteMapping(value = "/{idEntryDetail}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN')")
    public ResponseEntity<?> deleteEntryDetail(@PathVariable Long idEntryDetail,
                                           Authentication authentication) {
        entryDetailService.deleteEntryDetail(idEntryDetail, authentication);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/reactivate/{idEntryDetail}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN')")
    public ResponseEntity<?> reactivateEntryDetail(@PathVariable Long idEntryDetail,
                                           Authentication authentication) {
        return ResponseEntity
                .ok(
                        message.ok(
                                entryDetailService.reactivateEntryDetail(idEntryDetail, authentication)
                        )
                );
    }
}
