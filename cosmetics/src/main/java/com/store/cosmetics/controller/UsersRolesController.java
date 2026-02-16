package com.store.cosmetics.controller;

import com.store.cosmetics.config.ApiConfig;
import com.store.cosmetics.service.contract.IUsersRolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(ApiConfig.API_BASE_PATH + "/user-role")
@RequiredArgsConstructor
public class UsersRolesController extends Controller{
    private final IUsersRolesService usersRolesService;

    @GetMapping(value = "/user/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN')")
    public ResponseEntity<?> getAllUsersRoles(@PathVariable Long idUser) {
        return ResponseEntity
                .ok(
                        message.ok(
                                usersRolesService.findUsersRolesByIdUser(idUser)
                        )
                );
    }

    @PutMapping(value = "/user/{idUser}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN')")
    public ResponseEntity<?> updateUserRoles(@PathVariable Long idUser,
                                             @RequestBody List<Long> roleIds,
                                             Authentication authentication) {
        usersRolesService.updateUserRoles(idUser, roleIds, authentication);
        return ResponseEntity.noContent().build();
    }
}
