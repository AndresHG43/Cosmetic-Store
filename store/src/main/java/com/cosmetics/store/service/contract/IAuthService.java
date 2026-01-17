package com.cosmetics.store.service.contract;

import com.cosmetics.store.filter.LoginDto;
import com.cosmetics.store.filter.PasswordDto;
import com.cosmetics.store.filter.RefreshTokenDto;
import org.springframework.security.core.Authentication;

import java.util.HashMap;

public interface IAuthService {
    HashMap<String, Object> login(LoginDto loginDto);
    HashMap<String, Object> refreshToken(final RefreshTokenDto refreshTokenDto);
    void logout(Authentication authentication);
    String generatePassword (final PasswordDto passwordDto) throws RuntimeException;
}
