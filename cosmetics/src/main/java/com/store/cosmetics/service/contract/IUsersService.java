package com.store.cosmetics.service.contract;

import com.store.cosmetics.entity.request.UsersRequestDto;
import com.store.cosmetics.entity.response.UsersResponseDto;
import com.store.cosmetics.filter.UsersUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import java.util.HashMap;

public interface IUsersService {
    UsersResponseDto createUser(UsersRequestDto usersRequestDto, Authentication authentication) throws RuntimeException;
    Page<UsersResponseDto> findAllUsers(Pageable pageable, HashMap<String, Object> params) throws RuntimeException;
    UsersResponseDto findUserById(Long idUser) throws RuntimeException;
    UsersResponseDto updateUser(Long idUser, UsersUpdateRequestDto usersRequestDto, Authentication authentication) throws RuntimeException;
    void deleteUser(Long idUser, Authentication authentication) throws RuntimeException;
    UsersResponseDto reactivateUser(Long idUser, Authentication authentication) throws RuntimeException;
}
