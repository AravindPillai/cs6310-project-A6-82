package com.gatech.streamingwars.service;


import com.gatech.streamingwars.maindb.model.DemographicGroup;
import com.gatech.streamingwars.maindb.repository.DemographicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MainDBService {

    @Autowired
    DemographicRepository demographicRepository;

    public List<DemographicGroup> saveDemographicGroup(List<DemographicGroup> group) throws DataIntegrityViolationException,SQLIntegrityConstraintViolationException
    {
        List<DemographicGroup> save = demographicRepository.saveAll(group);
        return save;
    }

    public List<DemographicGroup> findAllDemographicGroup()
    {
        List<DemographicGroup> demographicGroupList = this.demographicRepository.findAll();
        return demographicGroupList;
    }

    public List<DemographicGroup> findDemographicGroupForArchival(LocalDateTime localDateTime)
    {
        List<DemographicGroup> demogrphicGroupGreaterThanCreatedDate = demographicRepository.getDemogrphicGroupLessThanCreatedDate(localDateTime);
        return demogrphicGroupGreaterThanCreatedDate;
    }

    public DemographicRepository getDemographicRepository(){
        return this.demographicRepository;
    }

    public List<DemographicGroup>  getAllDemos(LocalDateTime startDate,LocalDateTime endDate)
    {
        List<DemographicGroup> demographicGroups = demographicRepository.getDemographicGroupBetweenDates(startDate,endDate);
        return demographicGroups;
    }

    public Optional<DemographicGroup> findDemoGraphicGroupWithID(Long id) {
        return demographicRepository.findById(id);
    }
}
