package com.wesleypi.timesheet.persistence.repository;

import com.wesleypi.timesheet.persistence.entity.EntryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EntryRepository extends JpaRepository<EntryEntity, Long> {
    Optional<EntryEntity> findByDate(LocalDate date);
    Optional<EntryEntity> findByUserIdAndDate(Long userId, LocalDate date);

    Page<EntryEntity> findByUserIdAndDateBetween(Long userId, LocalDate start, LocalDate end, Pageable pageable);
    List<EntryEntity> findByUserIdAndDateBetween(Long userId, LocalDate start, LocalDate end);
}