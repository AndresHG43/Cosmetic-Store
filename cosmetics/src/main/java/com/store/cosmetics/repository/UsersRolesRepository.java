package com.store.cosmetics.repository;

import com.store.cosmetics.entity.Users;
import com.store.cosmetics.entity.UsersRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsersRolesRepository extends JpaRepository<UsersRoles, Long> {
    List<UsersRoles> findAllUsersRolesByActiveTrueAndUsersId(Users idUser);
    List<UsersRoles> findAllByUsersId(Users idUser);
}
