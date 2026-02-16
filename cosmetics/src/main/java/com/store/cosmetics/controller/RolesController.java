package com.store.cosmetics.controller;

import com.store.cosmetics.config.ApiConfig;
import com.store.cosmetics.entity.request.RolesRequestDto;
import com.store.cosmetics.service.contract.IRolesService;
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
@RequestMapping(ApiConfig.API_BASE_PATH + "/role")
@RequiredArgsConstructor
public class RolesController extends Controller {
    private final IRolesService rolesService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN')")
    public ResponseEntity<?> createRole(@RequestBody @Valid RolesRequestDto rolesRequestDto,
                                           Authentication authentication) {
        return ResponseEntity
                .ok(
                        message.ok(
                                rolesService.createRole(rolesRequestDto, authentication)
                        )
                );
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN')")
    public ResponseEntity<?> getPaginatedAllRoles(@RequestParam HashMap<String, Object> params) {
        Pageable pageable = getPageable(params);
        return ResponseEntity
                .ok(
                        message.ok(
                                rolesService.findAllRoles(pageable, params)
                        )
                );
    }

    @GetMapping(value = "/{idRol}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN')")
    public ResponseEntity<?> getRoleById(@PathVariable Long idRol) {
        return ResponseEntity
                .ok(
                        message.ok(
                                rolesService.findRoleByIdEndPoint(idRol)
                        )
                );
    }

    @PutMapping(value = "/{idRol}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN')")
    public ResponseEntity<?> updateRole(@PathVariable Long idRol,
                                           @RequestBody @Valid RolesRequestDto rolesRequestDto,
                                           Authentication authentication) {
        return ResponseEntity
                .ok(
                        message.ok(
                                rolesService.updateRol(idRol, rolesRequestDto, authentication)
                        )
                );
    }

    @DeleteMapping(value = "/{idRol}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN')")
    public ResponseEntity<?> deleteRole(@PathVariable Long idRol,
                                           Authentication authentication) {
        rolesService.deleteRol(idRol, authentication);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/reactivate/{idRol}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN')")
    public ResponseEntity<?> reactivateRole(@PathVariable Long idRol,
                                           Authentication authentication) {
        return ResponseEntity
                .ok(
                        message.ok(
                                rolesService.reactivateRol(idRol, authentication)
                        )
                );
    }
}
