package com.gatech.streamingwars.cron;

import com.gatech.streamingwars.archivedb.model.ArchivedDemographicGroup;
import com.gatech.streamingwars.maindb.model.DemographicGroup;
import com.gatech.streamingwars.service.ArchiveDBService;
import com.gatech.streamingwars.service.MainDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Archiver {

    @Autowired
    MainDBService dbService;

    @Autowired
    ArchiveDBService archiveDBService;

    @Value("${cronvalue}")
    private String cronValue;

    @Value("${numberOfMonths}")
    private int numberOfMonths;

    @Scheduled(cron = "0 * * ? * *")
    public void archive() throws SQLIntegrityConstraintViolationException {
        List<ArchivedDemographicGroup> listToArchive = new ArrayList<ArchivedDemographicGroup>();
        Date now = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(now.toInstant(), ZoneId.systemDefault());
        LocalDateTime mldt3 = ldt.minus(numberOfMonths, ChronoUnit.MONTHS);
        System.out.println("We need to update this to move the data into archive DB " + ldt + " "+mldt3);
        List<DemographicGroup> demographicGroupForArchival = dbService.findDemographicGroupForArchival(mldt3);
        System.out.println(demographicGroupForArchival);
        for(DemographicGroup group:demographicGroupForArchival)
        {
            group.setArchived(true);
            ArchivedDemographicGroup archivedDemographicGroup = new ArchivedDemographicGroup(group);
            listToArchive.add(archivedDemographicGroup);
        }
        dbService.saveDemographicGroup(demographicGroupForArchival);
        archiveDBService.saveAll(listToArchive);


    }
}
