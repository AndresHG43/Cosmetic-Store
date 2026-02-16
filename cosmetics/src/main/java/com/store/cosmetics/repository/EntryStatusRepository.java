package com.store.cosmetics.repository;

import com.store.cosmetics.entity.EntryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryStatusRepository extends JpaRepository<EntryStatus, Long> {
}
