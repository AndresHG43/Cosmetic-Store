package com.cosmetics.store.controller;

import com.cosmetics.store.config.ApiConfig;
import com.cosmetics.store.entity.request.UsersRequestDto;
import com.cosmetics.store.filter.UsersUpdateRequestDto;
import com.cosmetics.store.service.contract.IUsersService;
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
@RequestMapping(ApiConfig.API_BASE_PATH + "/user")
@RequiredArgsConstructor
public class UsersController extends Controller{
    private final IUsersService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN')")
    public ResponseEntity<?> createUser(@RequestBody UsersRequestDto usersRequestDto,
                                        Authentication authentication) {
        return ResponseEntity
                .ok(
                        message.ok(
                                userService.createUser(usersRequestDto, authentication)
                        )
                );
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN', 'USER')")
    public ResponseEntity<?> getPaginatedAllUsers(@RequestParam HashMap<String, Object> params) {
        Pageable pageable = getPageable(params);
        return ResponseEntity
                .ok(
                        message.ok(
                                userService.findAllUsers(pageable, params)
                        )
                );
    }

    @GetMapping(value = "/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN', 'USER')")
    public ResponseEntity<?> getUserById(@PathVariable("idUser") Long idUser) {
        return ResponseEntity
                .ok(
                        message.ok(
                                userService.findUserById(idUser)
                        )
                );
    }

    @PutMapping(value = "/{idUser}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN')")
    public ResponseEntity<?> updateUser(@PathVariable Long idUser,
                                           @RequestBody @Valid UsersUpdateRequestDto usersRequestDto,
                                           Authentication authentication) {
        return ResponseEntity
                .ok(
                        message.ok(
                                userService.updateUser(idUser, usersRequestDto, authentication)
                        )
                );
    }

    @DeleteMapping(value = "/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long idUser,
                                           Authentication authentication) {
        userService.deleteUser(idUser, authentication);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/reactivate/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN')")
    public ResponseEntity<?> reactivateUser(@PathVariable Long idUser,
                                               Authentication authentication) {
        return ResponseEntity
                .ok(
                        message.ok(
                                userService.reactivateUser(idUser, authentication)
                        )
                );
    }
}
