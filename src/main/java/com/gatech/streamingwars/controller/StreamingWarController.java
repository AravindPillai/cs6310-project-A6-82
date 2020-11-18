package com.gatech.streamingwars.controller;

import com.gatech.streamingwars.maindb.model.DemographicGroup;
import com.gatech.streamingwars.service.MainDBService;
import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class StreamingWarController {

    @Autowired
    MainDBService service;

    @PostMapping("/api/updateDemoGraphicGroup")
    public boolean updateDemographicGroup(@RequestBody DemographicGroup demographicGroup)
    {
        System.out.println("Aravind"+demographicGroup);
        Optional<DemographicGroup> demoGraphicGroupWithID = service.findDemoGraphicGroupWithID(demographicGroup.getId());
        if(demoGraphicGroupWithID.isPresent())
        {
            DemographicGroup demographicGroup1 = demoGraphicGroupWithID.get();
            demographicGroup1.setDescription(demographicGroup.getDescription());
            demographicGroup1.setLongName(demographicGroup.getLongName());
            demographicGroup1.setNumberOfAccounts(demographicGroup.getNumberOfAccounts());
            List<DemographicGroup> saveList = new ArrayList<DemographicGroup>();
            saveList.add(demographicGroup1);
            try {
                List<DemographicGroup> demographicGroups = service.saveDemographicGroup(saveList);
            } catch (SQLIntegrityConstraintViolationException exception) {
                exception.printStackTrace();
                return false;
            }
            return true;
        }
        else
        {
            return false;
        }

    }
}

