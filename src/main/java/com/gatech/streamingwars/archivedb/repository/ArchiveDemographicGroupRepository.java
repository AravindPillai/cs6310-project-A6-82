package com.gatech.streamingwars.archivedb.repository;

import com.gatech.streamingwars.archivedb.model.ArchivedDemographicGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchiveDemographicGroupRepository extends JpaRepository<ArchivedDemographicGroup,Long> {

}
