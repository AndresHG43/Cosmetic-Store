package com.cosmetics.store.repository;

import com.cosmetics.store.entity.EntryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryStatusRepository extends JpaRepository<EntryStatus, Long> {
}
