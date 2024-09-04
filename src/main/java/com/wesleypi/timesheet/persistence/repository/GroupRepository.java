package com.wesleypi.timesheet.persistence.repository;

import com.wesleypi.timesheet.persistence.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
    Optional<GroupEntity> findByExternalId(String externalId);
}