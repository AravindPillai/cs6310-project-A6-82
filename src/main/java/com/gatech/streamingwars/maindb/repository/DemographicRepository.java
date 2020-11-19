package com.gatech.streamingwars.maindb.repository;

import com.gatech.streamingwars.maindb.model.DemographicGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface DemographicRepository extends JpaRepository<DemographicGroup,Long> {

    @Query("SELECT d FROM DemographicGroup d WHERE d.createdAt< :createddate and d.archived=false")
    public List<DemographicGroup> getDemogrphicGroupLessThanCreatedDate(@Param("createddate") LocalDateTime createdDate);

    @Query("SELECT d FROM DemographicGroup d WHERE d.createdAt>=:startDate and d.createdAt< :endDate")
    List<DemographicGroup> getDemographicGroupBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
