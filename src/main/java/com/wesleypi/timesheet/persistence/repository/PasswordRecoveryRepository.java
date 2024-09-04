package com.wesleypi.timesheet.persistence.repository;

import com.wesleypi.timesheet.persistence.entity.PasswordRecoveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordRecoveryRepository extends JpaRepository<PasswordRecoveryEntity, Long> {
    Optional<PasswordRecoveryEntity> findByCode(String code);
}