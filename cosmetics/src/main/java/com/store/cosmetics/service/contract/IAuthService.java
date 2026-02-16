package com.store.cosmetics.service.contract;

import com.store.cosmetics.filter.LoginDto;
import com.store.cosmetics.filter.PasswordDto;
import com.store.cosmetics.filter.RefreshTokenDto;
import org.springframework.security.core.Authentication;
import java.util.HashMap;

public interface IAuthService {
    HashMap<String, Object> login(LoginDto loginDto);
    HashMap<String, Object> refreshToken(final RefreshTokenDto refreshTokenDto);
    void logout(Authentication authentication);
    String generatePassword (final PasswordDto passwordDto) throws RuntimeException;
}
