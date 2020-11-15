package com.gatech.streamingwars.service;


import com.gatech.streamingwars.archivedb.model.ArchivedDemographicGroup;
import com.gatech.streamingwars.archivedb.repository.ArchiveDemographicGroupRepository;
import com.gatech.streamingwars.maindb.model.DemographicGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchiveDBService {

    @Autowired
    ArchiveDemographicGroupRepository archiveDemographicGroupRepository;

    public List<ArchivedDemographicGroup> saveAll(List<ArchivedDemographicGroup> archivalList)
    {
        List<ArchivedDemographicGroup> archivedDemographicGroups = archiveDemographicGroupRepository.saveAll(archivalList);
        return  archivedDemographicGroups;
    }
}
