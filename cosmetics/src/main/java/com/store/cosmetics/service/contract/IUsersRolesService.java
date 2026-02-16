package com.store.cosmetics.service.contract;

import com.store.cosmetics.entity.response.UsersRolesResponseDto;
import org.springframework.security.core.Authentication;
import java.util.List;

public interface IUsersRolesService {
    List<UsersRolesResponseDto> findUsersRolesByIdUser(Long idUser) throws RuntimeException;
    void updateUserRoles(Long idUser, List<Long> roleIds, Authentication authentication) throws RuntimeException;
}
