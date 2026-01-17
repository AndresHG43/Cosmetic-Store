package com.cosmetics.store.repository;

import com.cosmetics.store.entity.Entry;
import com.cosmetics.store.entity.EntryDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface EntryDetailRepository extends JpaRepository<EntryDetail, Long>, JpaSpecificationExecutor<EntryDetail> {
    Page<EntryDetail> findAllByEntryId(Entry entryId, Pageable pageable);
    Optional<EntryDetail> findEntryDetailByActiveTrueAndId(Long id);
    Optional<EntryDetail> findEntryDetailByActiveFalseAndId(Long id);
}
