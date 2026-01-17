package com.cosmetics.store.repository;

import com.cosmetics.store.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long>, JpaSpecificationExecutor<Roles> {
    Optional<Roles> findRolesByActiveTrueAndId(Long id);
    Optional<Roles> findRolesByActiveFalseAndId(Long id);
}
