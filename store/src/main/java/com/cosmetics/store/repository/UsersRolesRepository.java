package com.cosmetics.store.repository;

import com.cosmetics.store.entity.Users;
import com.cosmetics.store.entity.UsersRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRolesRepository extends JpaRepository<UsersRoles, Long> {
    List<UsersRoles> findAllUsersRolesByActiveTrueAndUsersId(Users idUser);
    List<UsersRoles> findAllByUsersId(Users idUser);
}
