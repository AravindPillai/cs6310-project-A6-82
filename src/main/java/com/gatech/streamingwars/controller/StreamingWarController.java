package com.gatech.streamingwars.controller;

import com.gatech.streamingwars.maindb.model.DemographicGroup;
import com.gatech.streamingwars.maindb.model.Event;
import com.gatech.streamingwars.maindb.model.EventOffer;
import com.gatech.streamingwars.maindb.model.StreamingService;
import com.gatech.streamingwars.service.MainDBService;
import com.gatech.streamingwars.ui.StreamTransactionSummary;
import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/api/updateEvent")
    public boolean updateDemographicGroup(@RequestBody Event event)
    {
        Optional<Event> eventByID = service.getEventByID(event.getId());
        if(eventByID.isPresent())
        {
            Event fetchedEvent = eventByID.get();
            fetchedEvent.setEventLicensingFee(event.getEventLicensingFee());
            List<Event> events = new ArrayList<Event>();
            events.add(fetchedEvent);
            try {
            List<Event> events1 = service.saveAllEvents(events);
            } catch (SQLIntegrityConstraintViolationException exception) {
                exception.printStackTrace();
                return false;
            }
            return true;
        }
        else {
            return false;
        }

    }

    @PostMapping("/api/updateStream")
    public boolean updateDemographicGroup(@RequestBody StreamTransactionSummary summary)
    {
        StreamingService streamingServiceByName = service.findStreamingServiceByName(summary.getShortName());
        if(streamingServiceByName!=null)
        {
            streamingServiceByName.setSubscriptionPrice(summary.getSubscriptionPrice());
            try {
                StreamingService streamingService = service.saveStreamingService(streamingServiceByName);
            } catch (SQLIntegrityConstraintViolationException exception) {
                exception.printStackTrace();
                return false;
            }
            return true;
        }
        else {
            return false;
        }

    }

    @GetMapping("/api/getEvents/{name}")
    public ResponseEntity<List<EventOffer>> getEventsForStreamingService(@PathVariable("name") String name)
    {
        List<EventOffer> eventOffers = service.findByServiceID(name);
        List<EventOffer> nonRetracted = new ArrayList<EventOffer>();
        for(EventOffer offer: eventOffers)
        {
            if(offer.isRetracted())
            {
                continue;
            }
            else
            {
                nonRetracted.add(offer);
            }
        }
        return new ResponseEntity<List<EventOffer>>(nonRetracted, HttpStatus.OK);
    }
}

