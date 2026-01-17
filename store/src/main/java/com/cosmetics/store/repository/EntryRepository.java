package com.cosmetics.store.repository;

import com.cosmetics.store.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface EntryRepository extends JpaRepository<Entry, Long>, JpaSpecificationExecutor<Entry> {
    Optional<Entry> findEntryByActiveTrueAndId(Long id);
    Optional<Entry> findEntryByActiveFalseAndId(Long id);
}
