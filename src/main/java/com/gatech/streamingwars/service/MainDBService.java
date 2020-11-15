package com.gatech.streamingwars.service;


import com.gatech.streamingwars.model.main.DemographicGroup;
import com.gatech.streamingwars.repository.DemographicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class MainDBService {

    @Autowired
    DemographicRepository demographicRepository;

    public DemographicGroup saveDemographicGroup(DemographicGroup group) throws DataIntegrityViolationException,SQLIntegrityConstraintViolationException
    {
        DemographicGroup save = demographicRepository.save(group);
        return save;
    }

    public List<DemographicGroup> findAllDemographicGroup()
    {
        List<DemographicGroup> demographicGroupList = this.demographicRepository.findAll();
        return demographicGroupList;
    }

}
