package com.cosmetics.store.repository;

import com.cosmetics.store.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>, JpaSpecificationExecutor<Users> {
    long countUsersByActiveTrue();
    Optional<Users> findUserByEmailAndActiveTrue(String email);

    Optional<Users> findUsersByActiveTrueAndId(Long id);
    Optional<Users> findUsersByActiveFalseAndId(Long id);
}
