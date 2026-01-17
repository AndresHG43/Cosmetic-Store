package com.cosmetics.store.controller;

import com.cosmetics.store.config.ApiConfig;
import com.cosmetics.store.filter.LoginDto;
import com.cosmetics.store.filter.PasswordDto;
import com.cosmetics.store.filter.RefreshTokenDto;
import com.cosmetics.store.service.contract.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConfig.API_BASE_PATH + "/auth")
@RequiredArgsConstructor
public class AuthController extends Controller {
    private final IAuthService authService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity
                .ok(
                        message.ok(
                                authService.login(loginDto)
                        )
                );
    }

    @PostMapping(value = "/refresh", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> refresh(@RequestBody RefreshTokenDto refreshTokenDto) {
        return ResponseEntity
                .ok(
                        message.ok(
                                authService.refreshToken(refreshTokenDto)
                        )
                );
    }

    @PostMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> logout(Authentication authentication) {
        authService.logout(authentication);
        return ResponseEntity
                .ok(
                        ResponseEntity.noContent()
                );
    }

//  Use only for create the first User
    @PostMapping(value = "/generate-password", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> generatePassword(@RequestBody PasswordDto passwordDto) {
        return ResponseEntity
                .ok(
                        message.ok(
                                authService.generatePassword(passwordDto)
                        )
                );
    }
}
