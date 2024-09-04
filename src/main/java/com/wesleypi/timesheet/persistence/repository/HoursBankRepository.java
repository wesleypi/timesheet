package com.wesleypi.timesheet.persistence.repository;

import com.wesleypi.timesheet.persistence.entity.HoursBankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HoursBankRepository extends JpaRepository<HoursBankEntity, Long> {
    Optional<HoursBankEntity> findFirstByUserIdOrderByDateDesc(Long userId);
}