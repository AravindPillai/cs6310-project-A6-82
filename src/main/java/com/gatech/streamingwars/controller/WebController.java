package com.gatech.streamingwars.controller;

import com.gatech.streamingwars.model.main.Account;
import com.gatech.streamingwars.model.main.DemographicGroup;
import com.gatech.streamingwars.model.main.Event;
import com.gatech.streamingwars.model.main.Studio;
import com.gatech.streamingwars.repository.AccountRepository;
import com.gatech.streamingwars.repository.DemographicRepository;
import com.gatech.streamingwars.repository.EventRepository;
import com.gatech.streamingwars.repository.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class WebController {

    @Autowired
    AccountRepository repository;

    @Autowired
    DemographicRepository demographicRepository;

    @Autowired
    StudioRepository studioRepository;

    @Autowired
    EventRepository eventRepository;

    @RequestMapping("/")
    public  String index(Model model)
    {
      return "index.xhtml";
    }

    @RequestMapping("/login")
    public  String login(Model model)
    {
       return "index.xhtml";
    }

    @RequestMapping("/createevent")
    public  String createEvent(Model model)
    {
        clearModelAttributes(model);
        Event event = new Event();
        model.addAttribute("event",event);
        return "createevent.xhtml";
    }

    @PostMapping("/createevent")
    public String createEventSubmit(@ModelAttribute Event event, Model model) {

        // The most important thing about this is that, the Event must have a valid studio,
        //
        // Additionally, we'll need to create other validations around
        // the fields we enter-

        Boolean isValid = true;
        String reasonForFailure = "";
        Studio studioLookup = lookupStudioByShortName(event.getStudioShortName());
        if (studioLookup == null){
            isValid = false;
            reasonForFailure += "Studio not found";
        }

        String eventType = event.getEventType();
        if (!(eventType.equalsIgnoreCase("movie")||eventType.equalsIgnoreCase("ppv"))){
            isValid = false;
            reasonForFailure += "Event type is not movie or ppv";
        }

        Event saved = null;
        if (isValid) {
            System.out.println("passed validation steps");
            saved = eventRepository.save(event);
        }

        if(saved!=null) {
            model.addAttribute("successmessage", "Event Saved Successfully!");
            return "index.xhtml";
        }
        else {
            model.addAttribute("errormessage", String.format("Event save Failed for the following reasons: %s, Please try again", reasonForFailure));
            model.addAttribute("event",event);
            return "createevent.xhtml";
        }
    }

    @RequestMapping("/createdemo")
    public  String createDemo(Model model)
    {
        clearModelAttributes(model);
        DemographicGroup dGroup = new DemographicGroup();
        model.addAttribute("group",dGroup);
        return "createdemo.xhtml";
    }

    @PostMapping("/createdemo")
    public String greetingSubmit(@ModelAttribute DemographicGroup group, Model model) {
        DemographicGroup saved = demographicRepository.save(group);
        if(saved!=null) {
            model.addAttribute("successmessage", "Demographic Group Saved Successfully!");
            return "index.xhtml";
        }
        else {
            model.addAttribute("errormessage", "Demographic Group Failed, Please try again");
            model.addAttribute("group",group);
            return "createdemo.xhtml";
        }
    }

    @RequestMapping("/createstudio")
    public  String createStudio(Model model)
    {
        clearModelAttributes(model);
        Studio studio = new Studio();
        model.addAttribute("studio",studio);
        return "createstudio.xhtml";
    }

    @PostMapping("/createstudio")
    public String studioSubmit(@ModelAttribute Studio studio, Model model) {

        Studio saved = studioRepository.save(studio);
        if(saved!=null) {
            model.addAttribute("successmessage", "Studio Saved Successfully!");
            return "index.xhtml";
        }
        else {
            model.addAttribute("errormessage", "Studio Saving Failed, Please try again");
            model.addAttribute("studio",studio);
            return "createstudio.xhtml";
        }
    }

    @RequestMapping("/displaystudio")
    public  String displayStudio(Model model)
    {
        clearModelAttributes(model);
        Studio studio = new Studio();
        model.addAttribute("studio", studio);
        return "displaystudio.xhtml";
    }

    @PostMapping("/displaystudio")
    public  String displayStudio(@ModelAttribute Studio studio, Model model)
    {
        // Lookup studio
        Studio studioLookup = lookupStudioByShortName(studio.getShortName());
        if(studioLookup != null){
            model.addAttribute("studio", studioLookup);
            return "displaystudio.xhtml";
        } else {
            studio = new Studio();
            model.addAttribute("studio", studio);
            model.addAttribute("errormessage", "Demogroup lookup Failed, Please try again");
            System.out.println("Couldn't find the demogroup");
            return "displaystudio.xhtml";
        }
    }

    @RequestMapping("/displaydemo")
    public String displayDemo(Model model)
    {
        clearModelAttributes(model);
        DemographicGroup demo = new DemographicGroup();
        model.addAttribute("demo",demo);
        return "displaydemo.xhtml";
    }

    @PostMapping("/displaydemo")
    public String displayDemo(@ModelAttribute DemographicGroup group, Model model) {

        //lookup demogroup
        DemographicGroup demographicGroupLookup = null;

        demographicGroupLookup = lookupDemographicGroupByShortName(group.getShortName());

        if(demographicGroupLookup != null) {
            clearModelAttributes(model);
            model.addAttribute("demo", demographicGroupLookup);
            model.addAttribute("successmessage", "Demogroup lookup succeeded");
            System.out.println("Successfully found demogroup");
            return "displaydemo.xhtml";
        } else {
            clearModelAttributes(model);
            DemographicGroup newDemoGroup = new DemographicGroup();
            model.addAttribute("demo", newDemoGroup);
            model.addAttribute("errormessage", "Demogroup lookup Failed, Please try again");
            System.out.println("Couldn't find the demogroup");
            return "displaydemo.xhtml";
        }
    }

    private DemographicGroup lookupDemographicGroupByShortName(String demographicGroupShortName){
        // looks up demographic group via the short name
        List<DemographicGroup> demographicGroupList = this.demographicRepository.findAll();
        DemographicGroup demographicGroupToSearchFor = null;
        for (DemographicGroup demographicGroup: demographicGroupList){
            if (demographicGroup.getShortName().equalsIgnoreCase(demographicGroupShortName)){
                demographicGroupToSearchFor = demographicGroup;
                return demographicGroupToSearchFor;
            }
        }
        return null;
    }

    private Studio lookupStudioByShortName(String studioShortName){
        List<Studio> studioList = this.studioRepository.findAll();
        Studio studioSearchResult = null;
        for (Studio studio: studioList){
            if (studio.getShortName().equalsIgnoreCase(studioShortName)){
                studioSearchResult = studio;
                return studioSearchResult;
            }
        }
        return null;
    }


    private void clearModelAttributes(Model model)
    {
        model.addAttribute("successmessage",null);
        model.addAttribute("errormessage",null);
    }
}
