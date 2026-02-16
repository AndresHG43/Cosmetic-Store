package com.store.cosmetics.service.contract;

import com.store.cosmetics.entity.Roles;
import com.store.cosmetics.entity.request.RolesRequestDto;
import com.store.cosmetics.entity.response.RolesResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import java.util.HashMap;

public interface IRolesService {
    RolesResponseDto createRole(RolesRequestDto rolesRequestDto, Authentication authentication) throws RuntimeException;
    Page<RolesResponseDto> findAllRoles(Pageable pageable, HashMap<String, Object> params) throws RuntimeException;
    Roles findRoleById(Long idRol) throws RuntimeException;
    RolesResponseDto findRoleByIdEndPoint(Long idRol) throws RuntimeException;
    RolesResponseDto updateRol(Long idRol, RolesRequestDto rolesRequestDto, Authentication authentication) throws RuntimeException;
    void deleteRol(Long idRol, Authentication authentication) throws RuntimeException;
    RolesResponseDto reactivateRol(Long idRol, Authentication authentication) throws RuntimeException;
}
