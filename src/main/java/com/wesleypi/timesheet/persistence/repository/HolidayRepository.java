package com.wesleypi.timesheet.persistence.repository;

import com.wesleypi.timesheet.persistence.entity.HolidayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface HolidayRepository extends JpaRepository<HolidayEntity, Long> {
    Optional<HolidayEntity> findFirstByYear(Integer year);
    void deleteAllByYear(Integer year);
    List<HolidayEntity> findAllByYear(Integer year);
    Boolean existsByDate(LocalDate date);
    List<HolidayEntity> findAllByDateBetween(LocalDate start, LocalDate end);
}
