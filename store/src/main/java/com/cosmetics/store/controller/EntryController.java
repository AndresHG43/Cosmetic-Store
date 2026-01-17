package com.cosmetics.store.controller;

import com.cosmetics.store.config.ApiConfig;
import com.cosmetics.store.entity.request.EntryRequestDto;
import com.cosmetics.store.service.contract.IEntryService;
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
@RequestMapping(ApiConfig.API_BASE_PATH + "/entry")
@RequiredArgsConstructor
public class EntryController extends Controller {
    private final IEntryService entryService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN')")
    public ResponseEntity<?> createEntry(Authentication authentication) {
        return ResponseEntity
                .ok(
                        message.ok(
                                entryService.createEntry(authentication)
                        )
                );
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN')")
    public ResponseEntity<?> registerEntry(@RequestBody @Valid EntryRequestDto entryRequestDto,
                                           Authentication authentication) {
        return ResponseEntity
                .ok(
                        message.ok(
                                entryService.registerEntry(entryRequestDto, authentication)
                        )
                );
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN', 'USER')")
    public ResponseEntity<?> getPaginatedAllEntry(@RequestParam HashMap<String, Object> params) {
        Pageable pageable = getPageable(params);
        return ResponseEntity
                .ok(
                        message.ok(
                                entryService.findAllEntrys(pageable, params)
                        )
                );
    }

    @GetMapping(value = "/{idEntry}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN', 'USER')")
    public ResponseEntity<?> getEntryById(@PathVariable Long idEntry) {
        return ResponseEntity
                .ok(
                        message.ok(
                                entryService.findEntryById(idEntry)
                        )
                );
    }

    @PutMapping(value = "/{idEntry}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN')")
    public ResponseEntity<?> updateEntry(@PathVariable Long idEntry,
                                           @RequestBody @Valid EntryRequestDto entryRequestDto,
                                           Authentication authentication) {
        return ResponseEntity
                .ok(
                        message.ok(
                                entryService.updateEntry(idEntry, entryRequestDto, authentication)
                        )
                );
    }

    @DeleteMapping(value = "/{idEntry}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN')")
    public ResponseEntity<?> deleteEntry(@PathVariable Long idEntry,
                                           Authentication authentication) {
        entryService.deleteEntry(idEntry, authentication);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/reactivate/{idEntry}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN')")
    public ResponseEntity<?> reactivateEntry(@PathVariable Long idEntry,
                                           Authentication authentication) {
        return ResponseEntity
                .ok(
                        message.ok(
                                entryService.reactivateEntry(idEntry, authentication)
                        )
                );
    }
}
