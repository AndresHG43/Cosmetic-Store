package com.store.cosmetics.service.implementation;

import com.store.cosmetics.entity.Roles;
import com.store.cosmetics.entity.Users;
import com.store.cosmetics.entity.UsersRoles;
import com.store.cosmetics.entity.mapper.RolesMapper;
import com.store.cosmetics.entity.request.RolesRequestDto;
import com.store.cosmetics.entity.response.RolesResponseDto;
import com.store.cosmetics.repository.RolesRepository;
import com.store.cosmetics.service.contract.IRolesService;
import com.store.cosmetics.specification.RolesSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class RolesService implements IRolesService {
    private final RolesRepository rolesRepository;

    @Override
    @Transactional
    public RolesResponseDto createRole(RolesRequestDto rolesRequestDto, Authentication authentication) throws RuntimeException {
        try {
            Users user = (Users) authentication.getPrincipal();

            String nameRole = rolesRequestDto.getName();
            nameRole = nameRole.toUpperCase();
            nameRole = nameRole.replace(" ", "_");

            Roles roles = rolesRequestDto.toEntity();
            roles.setName(nameRole);
            roles.setActive(true);
            roles.setUsersCreated(user);
            rolesRepository.save(roles);

            RolesResponseDto rolesResponseDto = new RolesResponseDto();
            RolesMapper.convertToResponseDto(roles, rolesResponseDto);
            return rolesResponseDto;
        } catch (Exception e) {
            log.error("[Roles] : Error trying to create the role. ", e);
            throw new RuntimeException ("Error trying to create the role");
        }
    }

    @Override
    public Page<RolesResponseDto> findAllRoles(Pageable pageable, HashMap<String, Object> params) throws RuntimeException {
        try {
            Specification<Roles> spec = Specification.allOf(RolesSpecification.active(true));

            if (params.containsKey("field") && params.containsKey("value")) {
                if (!params.get("field").toString().equals("null")
                        && !params.get("field").toString().equals("undefined")
                        && !params.get("field").toString().isEmpty() && !params.get("value").toString().equals("null")
                        && !params.get("value").toString().equals("undefined")
                        && !params.get("value").toString().isEmpty()) {
                    String field = params.get("field").toString();
                    String value = params.get("value").toString();
                    if (field.equals("name") || field.equals("description")) {
                        spec = spec.and(RolesSpecification.containsNormalized(value, field));
                    }
                }
            }

            Page<Roles> rolePage = rolesRepository.findAll(spec, pageable);

            return rolePage.map(roles -> {
                RolesResponseDto rolesResponseDto = new RolesResponseDto();
                RolesMapper.convertToResponseDto(roles, rolesResponseDto);
                return rolesResponseDto;
            });
        } catch (Exception e) {
            log.error("[Roles] : Error trying to get role's list. ", e);
            throw new RuntimeException ("Error trying to get role's list");
        }
    }

    @Override
    public Roles findRoleById(Long idRol) throws RuntimeException {
        try {
            return rolesRepository.findById(idRol).orElseThrow(
                    () -> new EntityNotFoundException("Roles with id " + idRol + " not found"));
        } catch (EntityNotFoundException e) {
            log.warn("[Roles] : Role not found. ", e);
            throw e;
        } catch (Exception e) {
            log.error("[Roles] : Error while trying to get role by id. ", e);
            throw new RuntimeException ("Error while trying to get role by id");
        }
    }

    @Override
    public RolesResponseDto findRoleByIdEndPoint(Long idRol) throws RuntimeException {
        try {
            Roles role = rolesRepository.findRolesByActiveTrueAndId(idRol).orElseThrow(
                    () -> new EntityNotFoundException("Role with id " + idRol + " not found"));

            RolesResponseDto rolesResponseDto = new RolesResponseDto();

            RolesMapper.convertToResponseDto(role, rolesResponseDto);
            return rolesResponseDto;
        } catch (EntityNotFoundException e) {
            log.warn("[Roles] : Role not found. ", e);
            throw e;
        } catch (Exception e) {
            log.error("[Roles] : Error trying to get role by id. ", e);
            throw new RuntimeException ("Error trying to get role by id");
        }
    }

    @Override
    @Transactional
    public RolesResponseDto updateRol(Long idRol, RolesRequestDto rolesRequestDto, Authentication authentication) throws RuntimeException {
        try {
            Users user = (Users) authentication.getPrincipal();

            if (userHasRole(user, idRol)) {
                throw new EntityNotFoundException("It is not possible to delete the role with id " + idRol + ", the logged-in user owns this role.");
            }

            Roles roles = rolesRepository.findRolesByActiveTrueAndId(idRol).orElseThrow(
                    () -> new EntityNotFoundException("Role with id " + idRol + " not found"));

            String nameRole = rolesRequestDto.getName();
            nameRole = nameRole.toUpperCase();
            nameRole = nameRole.replace(" ", "_");

            RolesMapper.updateRoles(rolesRequestDto, roles);

            roles.setName(nameRole);
            roles.setUsersUpdated(user);
            rolesRepository.save(roles);

            RolesResponseDto rolesResponseDto = new RolesResponseDto();
            RolesMapper.convertToResponseDto(roles, rolesResponseDto);
            return rolesResponseDto;
        } catch (EntityNotFoundException e) {
            log.warn("[Role] : ", e);
            throw e;
        } catch (Exception e) {
            log.error("[Role] : Error trying to update the role. ", e);
            throw new RuntimeException ("Error trying to update the role");
        }
    }

    @Override
    @Transactional
    public void deleteRol(Long idRol, Authentication authentication) throws RuntimeException {
        try {
            LocalDateTime dateDeleted = LocalDateTime.now();

            Users user = (Users) authentication.getPrincipal();

            if (userHasRole(user, idRol)) {
                throw new EntityNotFoundException("It is not possible to delete the role with id " + idRol + ", the logged-in user owns this role.");
            }

            Roles roles = rolesRepository.findRolesByActiveTrueAndId(idRol).orElseThrow(
                    () -> new EntityNotFoundException("Role with id " + idRol + " not found"));

            roles.setActive(false);
            roles.setDateDeleted(dateDeleted);
            roles.setUsersDeleted(user);
            rolesRepository.save(roles);
        } catch (EntityNotFoundException e) {
            log.warn("[Role] : ", e);
            throw e;
        } catch (Exception e)  {
            log.error("[Role] : Error trying to delete the role. ", e);
            throw new RuntimeException ("Error trying to delete the role");
        }
    }

    @Override
    @Transactional
    public RolesResponseDto reactivateRol(Long idRol, Authentication authentication) throws RuntimeException {
        try {
            Users user = (Users) authentication.getPrincipal();

            Roles roles = rolesRepository.findRolesByActiveFalseAndId(idRol).orElseThrow(
                    () -> new EntityNotFoundException("Deleted role with id " + idRol + " not found"));

            roles.setActive(true);
            roles.setDateDeleted(null);
            roles.setUsersDeleted(null);
            roles.setUsersUpdated(user);
            rolesRepository.save(roles);

            RolesResponseDto rolesResponseDto = new RolesResponseDto();
            RolesMapper.convertToResponseDto(roles, rolesResponseDto);
            return rolesResponseDto;
        } catch (EntityNotFoundException e) {
            log.warn("[Role] : Role not found. ", e);
            throw e;
        } catch (Exception e)  {
            log.error("[Role] : Error trying to activate the role. ", e);
            throw new RuntimeException ("Error trying to activate the role");
        }
    }

    private boolean userHasRole(Users user, Long idRol) {
        return user.getUsersRoles().stream()
                .filter(UsersRoles::getActive) //Active relationships only
                .map(UsersRoles::getRoleId) //Gets the Role object
                .filter(Roles::getActive) //Active roles only
                .anyMatch(role -> role.getId().equals(idRol));
    }
}
