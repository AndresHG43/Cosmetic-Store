package com.store.cosmetics.service.implementation;

import com.store.cosmetics.entity.Roles;
import com.store.cosmetics.entity.Users;
import com.store.cosmetics.entity.UsersRoles;
import com.store.cosmetics.entity.mapper.UsersMapper;
import com.store.cosmetics.entity.request.UsersRequestDto;
import com.store.cosmetics.entity.response.UsersResponseDto;
import com.store.cosmetics.filter.UsersUpdateRequestDto;
import com.store.cosmetics.repository.UsersRepository;
import com.store.cosmetics.repository.UsersRolesRepository;
import com.store.cosmetics.service.contract.IUsersService;
import com.store.cosmetics.specification.UsersSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersService implements IUsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolesService rolesService;
    private final UsersRolesRepository usersRolesRepository;

    @Override
    @Transactional
    public UsersResponseDto createUser(UsersRequestDto usersRequestDto, Authentication authentication) throws RuntimeException {
        try {
            Users userLogged = (Users) authentication.getPrincipal();

            Users user = usersRequestDto.toEntity();
            user.setActive(true);
            user.setPassword(passwordEncoder.encode(usersRequestDto.getPassword()));
            usersRepository.save(user);

            UsersResponseDto usersResponseDto = new UsersResponseDto();
            UsersMapper.convertToResponseDto(user, usersResponseDto);

            //Create the usersRoles relation
            List<Roles> rolesList = new ArrayList<>();
            for (Long idRole : usersRequestDto.getRoles()) {
                Roles role = rolesService.findRoleById(idRole);
                rolesList.add(role);
            }

            for (Roles role : rolesList) {
                UsersRoles usersRoles = new UsersRoles();
                usersRoles.setRoleId(role);
                usersRoles.setUsersId(user);
                usersRoles.setUsersCreated(userLogged);
                usersRoles.setActive(true);
                usersRolesRepository.save(usersRoles);
            }

            return usersResponseDto;
        } catch (EntityNotFoundException e) {
            log.warn("Entity not found. ", e);
            throw e;
        } catch (Exception e) {
            log.error("[Product] : Error trying to create the user. ", e);
            throw new RuntimeException ("Error trying to create the user");
        }
    }

    @Override
    public Page<UsersResponseDto> findAllUsers(Pageable pageable, HashMap<String, Object> params) throws RuntimeException {
        try {
            Specification<Users> spec = Specification.allOf(UsersSpecification.active(true));

            if (params.containsKey("field") && params.containsKey("value")) {
                if (!params.get("field").toString().equals("null")
                        && !params.get("field").toString().equals("undefined")
                        && !params.get("field").toString().isEmpty() && !params.get("value").toString().equals("null")
                        && !params.get("value").toString().equals("undefined")
                        && !params.get("value").toString().isEmpty()) {
                    String field = params.get("field").toString();
                    String value = params.get("value").toString();
                    if (field.equals("name") || field.equals("lastname") || field.equals("email")) {
                        spec = spec.and(UsersSpecification.containsNormalized(value, field));
                    }
                }
            }

            Page<Users> userPage = usersRepository.findAll(spec, pageable);

            return userPage.map(users -> {
                UsersResponseDto usersResponseDto = new UsersResponseDto();
                UsersMapper.convertToResponseDto(users, usersResponseDto);
                return usersResponseDto;
            });

        } catch (Exception e) {
            log.error("[USER] : Error while trying to get user's list. ", e);
            throw new RuntimeException ("Error while trying to get user's list");
        }
    }

    @Override
    public UsersResponseDto findUserById(Long idUser) throws RuntimeException {
        try {
            Users user = usersRepository.findUsersByActiveTrueAndId(idUser).orElseThrow(
                    () -> new EntityNotFoundException("User with id " + idUser + " not found"));

            UsersResponseDto usersResponseDto = new UsersResponseDto();

            UsersMapper.convertToResponseDto(user, usersResponseDto);
            return usersResponseDto;
        } catch (EntityNotFoundException e) {
            log.warn("[USER] : User not found. ", e);
            throw e;
        } catch (Exception e) {
            log.error("[USER] : Error while trying to get user by id. ", e);
            throw new RuntimeException ("Error while trying to get user by id");
        }
    }

    @Override
    @Transactional
    public UsersResponseDto updateUser(Long idUser, UsersUpdateRequestDto usersRequestDto, Authentication authentication) throws RuntimeException {
        try {
            Users user = usersRepository.findUsersByActiveTrueAndId(idUser).orElseThrow(
                    () -> new EntityNotFoundException("User with id " + idUser + " not found"));

            UsersMapper.updateUser(usersRequestDto, user);

            usersRepository.save(user);

            UsersResponseDto usersResponseDto = new UsersResponseDto();
            UsersMapper.convertToResponseDto(user, usersResponseDto);
            return usersResponseDto;
        } catch (EntityNotFoundException e) {
            log.warn("[USER] : User not found. ", e);
            throw e;
        } catch (Exception e) {
            log.error("[USER] : Error while trying to update the user. ", e);
            throw new RuntimeException ("Error while trying to update the user");
        }
    }

    @Override
    @Transactional
    public void deleteUser(Long idUser, Authentication authentication) throws RuntimeException {
        try {
            Users user = usersRepository.findUsersByActiveTrueAndId(idUser).orElseThrow(
                    () -> new EntityNotFoundException("User with id " + idUser + " not found"));

            user.setActive(false);
            user.setDateDeleted(LocalDateTime.now());
            usersRepository.save(user);
        } catch (EntityNotFoundException e) {
            log.warn("[USER] : User not found. ", e);
            throw e;
        } catch (Exception e) {
            log.error("[USER] : Error while trying to delete the user. ", e);
            throw new RuntimeException ("Error while trying to delete the user");
        }
    }

    @Override
    @Transactional
    public UsersResponseDto reactivateUser(Long idUser, Authentication authentication) throws RuntimeException {
        try {
            Users user = usersRepository.findUsersByActiveFalseAndId(idUser).orElseThrow(
                    () -> new EntityNotFoundException("User with id " + idUser + " not found"));

            user.setActive(true);
            user.setDateDeleted(null);
            usersRepository.save(user);

            UsersResponseDto usersResponseDto = new UsersResponseDto();
            UsersMapper.convertToResponseDto(user, usersResponseDto);
            return usersResponseDto;
        } catch (EntityNotFoundException e) {
            log.warn("[USER] : User not found. ", e);
            throw e;
        } catch (Exception e) {
            log.error("[USER] : Error while trying to activate the user. ", e);
            throw new RuntimeException ("Error while trying to activate the user");
        }
    }
}
