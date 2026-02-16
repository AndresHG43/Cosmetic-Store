package com.store.cosmetics.service.implementation;

import com.store.cosmetics.entity.Roles;
import com.store.cosmetics.entity.Users;
import com.store.cosmetics.entity.UsersRoles;
import com.store.cosmetics.entity.mapper.UsersRolesMapper;
import com.store.cosmetics.entity.response.UsersRolesResponseDto;
import com.store.cosmetics.repository.RolesRepository;
import com.store.cosmetics.repository.UsersRepository;
import com.store.cosmetics.repository.UsersRolesRepository;
import com.store.cosmetics.service.contract.IUsersRolesService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersRolesService implements IUsersRolesService {
    private final UsersRolesRepository usersRolesRepository;
    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;

    @Override
    public List<UsersRolesResponseDto> findUsersRolesByIdUser(Long idUser) throws RuntimeException {
        try {
            Users user = usersRepository.findUsersByActiveTrueAndId(idUser).orElseThrow(
                    () -> new EntityNotFoundException("User with id " + idUser + " not found"));

            List<UsersRoles> usersRolesList = usersRolesRepository.findAllUsersRolesByActiveTrueAndUsersId(user);

            return usersRolesList.stream()
                    .map(usersRoles -> {
                        UsersRolesResponseDto usersRolesResponseDto = new UsersRolesResponseDto();
                        UsersRolesMapper.convertToResponseDto(usersRoles, usersRolesResponseDto);
                        return usersRolesResponseDto;
                    })
                    .toList();
        } catch (EntityNotFoundException e) {
            log.warn("[USER] : User not found. ", e);
            throw e;
        } catch (Exception e) {
            log.error("[USER ROLE] : Error while trying to get user role's list. ", e);
            throw new RuntimeException ("Error while trying to get user role's list");
        }
    }

    @Override
    @Transactional
    public void updateUserRoles(Long idUser, List<Long> roleIds, Authentication authentication) throws RuntimeException {
        try {
            Users userLogged = (Users) authentication.getPrincipal();

            Users user = usersRepository.findUsersByActiveTrueAndId(idUser).orElseThrow(
                    () -> new EntityNotFoundException("User with id " + idUser + " not found"));

            // Get all user roles (active and inactive)
            List<UsersRoles> allUserRoles = usersRolesRepository.findAllByUsersId(user);

            // Create maps for quick comparison
            List<Long> currentActiveRoleIds = allUserRoles.stream()
                    .filter(UsersRoles::getActive)
                    .map(usersRole -> usersRole.getRoleId().getId())
                    .toList();

            List<Long> currentInactiveRoleIds = allUserRoles.stream()
                    .filter(usersRole -> !usersRole.getActive())
                    .map(usersRole -> usersRole.getRoleId().getId())
                    .toList();

            // New roles to assign (do not exist at all)
            List<Long> rolesToAssignNew = roleIds.stream()
                    .filter(roleId -> !currentActiveRoleIds.contains(roleId) && !currentInactiveRoleIds.contains(roleId))
                    .toList();

            // Roles to reactivate (exist but are inactive)
            List<Long> rolesToReactivate = roleIds.stream()
                    .filter(currentInactiveRoleIds::contains)
                    .toList();

            // Roles to be unassigned (are active but not in the input list)
            List<Long> rolesToRemove = currentActiveRoleIds.stream()
                    .filter(roleId -> !roleIds.contains(roleId))
                    .toList();

            // Assign new roles
            for (Long roleId : rolesToAssignNew) {
                Roles role = rolesRepository.findById(roleId).orElseThrow(
                        () -> new EntityNotFoundException("Role with id " + roleId + " not found"));

                UsersRoles usersRoles = new UsersRoles();
                usersRoles.setRoleId(role);
                usersRoles.setUsersId(user);
                usersRoles.setUsersCreated(userLogged);
                usersRoles.setActive(true);
                usersRolesRepository.save(usersRoles);
            }

            // Reactivate existing roles
            for (Long roleId : rolesToReactivate) {
                UsersRoles usersRoleToReactivate = allUserRoles.stream()
                        .filter(ur -> ur.getRoleId().getId().equals(roleId) && !ur.getActive())
                        .findFirst()
                        .orElse(null);

                if (usersRoleToReactivate != null) {
                    usersRoleToReactivate.setActive(true);
                    usersRoleToReactivate.setUsersUpdated(userLogged);
                    usersRoleToReactivate.setDateDeleted(null);
                    usersRoleToReactivate.setUsersDeleted(null);
                    usersRolesRepository.save(usersRoleToReactivate);
                }
            }

            // Unassign roles (change active to false)
            LocalDateTime dateDeleted = LocalDateTime.now();
            for (Long roleId : rolesToRemove) {
                UsersRoles usersRoleToRemove = allUserRoles.stream()
                        .filter(ur -> ur.getRoleId().getId().equals(roleId) && ur.getActive())
                        .findFirst()
                        .orElse(null);

                if (usersRoleToRemove != null) {
                    usersRoleToRemove.setActive(false);
                    usersRoleToRemove.setUsersDeleted(userLogged);
                    usersRoleToRemove.setDateDeleted(dateDeleted);
                    usersRolesRepository.save(usersRoleToRemove);
                }
            }

        } catch (EntityNotFoundException e) {
            log.warn("[USER ROLE] : Entity not found. ", e);
            throw e;
        } catch (Exception e) {
            log.error("[USER ROLE] : Error while trying to update user roles. ", e);
            throw new RuntimeException("Error while trying to update user roles");
        }
    }
}
