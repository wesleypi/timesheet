package com.wesleypi.timesheet.persistence.repository;

import com.wesleypi.timesheet.persistence.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Month;
import java.time.Year;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    Optional<PaymentEntity> findByUserIdAndMonthAndYear(Long userId, Integer month, Integer year);
}