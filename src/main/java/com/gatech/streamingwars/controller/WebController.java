package com.gatech.streamingwars.controller;

import com.gatech.streamingwars.maindb.model.*;
import com.gatech.streamingwars.maindb.repository.*;
import com.gatech.streamingwars.service.MainDBService;
import com.gatech.streamingwars.service.UserService;
import com.gatech.streamingwars.ui.EventOfferData;
import com.gatech.streamingwars.ui.FormData;
import com.gatech.streamingwars.ui.StreamTransactionSummary;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;


@Controller
public class WebController {

    @Autowired
    MainDBService mainDBService;

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String none(Model model) {
        return "login.xhtml";
    }

//    @RequestMapping("/error")
//    public String error(Model model) {
//        return "error.xhtml";
//    }

    @RequestMapping("/index")
    public String index(Model model) {
        return "index.xhtml";
    }

    @RequestMapping("/login")
    public String login(Model model, @RequestParam(required = false) Boolean error) {
        if (error != null && error) {
            model.addAttribute("errormessage", "Login Failed. Check the Credentials and Try Again.");
        }
        return "login.xhtml";
    }

    @RequestMapping("/registration")
    public String registration(Model model) {
        clearModelAttributes(model);
        User user = new User();
        model.addAttribute(user);
        return "registration.xhtml";
    }

    @RequestMapping("/createevent")
    public String createEvent(Model model) {
        clearModelAttributes(model);
        Event event = new Event();
        List<Studio> studios = mainDBService.findAllStudios();
        model.addAttribute("studios", studios);
        model.addAttribute("event", event);
        return "createevent.xhtml";
    }

    @PostMapping("/createevent")
    public String createEventSubmit(@ModelAttribute Event event, Model model) {

        // The most important thing about this is that, the Event must have a valid studio,
        //
        // Additionally, we'll need to create other validations around
        // the fields we enter-

        Event eventByName = mainDBService.findEventByNameAndYear(event.getName(),event.getYear());
        if(eventByName!=null)
        {
            model.addAttribute("errormessage", String.format("Event Allready Exists. Please create a new Event"));
            model.addAttribute("event", event);
            return "createevent.xhtml";
        }

        Boolean isValid = true;
        String reasonForFailure = "";
        Studio studioLookup = mainDBService.lookupStudioByShortName(event.getStudioShortName());
        if (studioLookup == null) {
            isValid = false;
            reasonForFailure += "Studio not found";
        }

        String eventType = event.getEventType();
        if (!(eventType.equalsIgnoreCase("movie") || eventType.equalsIgnoreCase("ppv"))) {
            isValid = false;
            reasonForFailure += "Event type is not movie or ppv";
        }

        Event saved = null;
        if (isValid) {
            System.out.println("passed validation steps");
            saved = mainDBService.saveEvent(event);
            //saved = eventRepository.save(event);
        }

        if (saved != null) {
            model.addAttribute("successmessage", "Event Saved Successfully!");
            return "index.xhtml";
        } else {
            model.addAttribute("errormessage", String.format("Event save Failed for the following reasons: %s, Please try again", reasonForFailure));
            model.addAttribute("event", event);
            return "createevent.xhtml";
        }
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute User user, Model model) {
        User userByUserName = userService.findUserByUserName(user.getName());
        if (userByUserName != null) {
            model.addAttribute("errormessage", "User Exists");
            model.addAttribute("user", userByUserName);
            return "registration.xhtml";
        } else {
            User saveUser = userService.saveUser(user);
            model.addAttribute("successmessage", "User Created Successfully!");
            return "login.xhtml";
        }
    }

    @RequestMapping("/createdemo")
    public String createDemo(Model model) {
        clearModelAttributes(model);
        if (!userService.isAuthenticated()) {
            return "/";
        }
        DemographicGroup dGroup = new DemographicGroup();
        model.addAttribute("group", dGroup);
        return "createdemo.xhtml";
    }

    @PostMapping("/createdemo")
    public String creatingDemo(@ModelAttribute DemographicGroup group, Model model) {
        List<DemographicGroup> saved = null;
        try {
            group.setArchived(false);
            //group.setCreatedAt(getCreateDate(group.getCurrentMonthYear()));
            List<DemographicGroup> groups = new ArrayList<DemographicGroup>();
            groups.add(group);
            saved = mainDBService.saveDemographicGroup(groups);
        } catch (SQLIntegrityConstraintViolationException | DataIntegrityViolationException exception) {
            exception.printStackTrace();
            System.out.println("Exception Occured during Saving " + exception.getMessage());
            model.addAttribute("errormessage", "Demographic Group Creation Failed,Entry With Same Name Possible Exist.Please Verify and try again");
            model.addAttribute("group", group);
            return "createdemo.xhtml";
        }
        if (saved != null && saved.size() > 0) {
            model.addAttribute("successmessage", "Demographic Group Saved Successfully!");
            return "index.xhtml";
        } else {
            model.addAttribute("errormessage", "Demographic Group Failed, Please try again");
            model.addAttribute("group", group);
            return "createdemo.xhtml";
        }
    }

    @RequestMapping("/createstream")
    public String createStreamingService(Model model) {
        clearModelAttributes(model);
        StreamingService streamingService = new StreamingService();
        model.addAttribute("streamingService", streamingService);
        return "createstream.xhtml";
    }

    @PostMapping("/createstream")
    public String createStreamingServiceSubmit(@ModelAttribute StreamingService streamingService, Model model) {

        StreamingService saved = null;
        try {
            saved = mainDBService.saveStreamingService(streamingService);
        } catch (SQLIntegrityConstraintViolationException exception) {
            exception.printStackTrace();
        }
        if (saved != null) {
            model.addAttribute("successmessage", "Streaming Service Saved Successfully!");
            return "index.xhtml";
        } else {
            model.addAttribute("errormessage", "Streaming Service Saving Failed, Please try again");
            model.addAttribute("streamingService", streamingService);
            return "createstream.xhtml";
        }
    }

    @RequestMapping("/createstudio")
    public String createStudio(Model model) {
        clearModelAttributes(model);
        Studio studio = new Studio();
        model.addAttribute("studio", studio);
        return "createstudio.xhtml";
    }

    @PostMapping("/createstudio")
    public String studioSubmit(@ModelAttribute Studio studio, Model model) {
        Studio saved = mainDBService.saveStudio(studio);
        //Studio saved = studioRepository.save(studio);
        if (saved != null) {
            model.addAttribute("successmessage", "Studio Saved Successfully!");
            return "index.xhtml";
        } else {
            model.addAttribute("errormessage", "Studio Saving Failed, Please try again");
            model.addAttribute("studio", studio);
            return "createstudio.xhtml";
        }
    }

    @RequestMapping("/watchevent")
    public String createWatchEvent(Model model) {
        clearModelAttributes(model);
        Transaction transaction = new Transaction();
        List<DemographicGroup> demographicGroupList = mainDBService.findAllDemographicGroup();
        List<Event> allEvents = mainDBService.findAllEvents();
        List<StreamingService> allServices = mainDBService.findAllServices();
        model.addAttribute("groups", demographicGroupList);
        model.addAttribute("events", allEvents);
        model.addAttribute("services", allServices);
        model.addAttribute("transaction", transaction);
        return "watchevent.xhtml";
    }

    @PostMapping("/watchevent")
    public String createWatchEventSubmit(@ModelAttribute Transaction transaction, Model model) {

        Boolean isValid = true;
        String reasonForFailure = "";

        //validate demo group
        DemographicGroup demographicGroupLookup = null;
        demographicGroupLookup = mainDBService.lookupDemographicGroupByShortName(transaction.getBuyer());
        if (demographicGroupLookup == null) {
            isValid = false;
            reasonForFailure += "Demo Group Not not found";
        }
        transaction.setDemographicName(transaction.getBuyer());

        // validate streaming service
        StreamingService streamingServiceLookup = null;
        streamingServiceLookup = mainDBService.lookupStreamByShortName(transaction.getVendor());
        if (streamingServiceLookup == null) {
            isValid = false;
            reasonForFailure += "Streaming Service not found";
        }

        String eventNameYear = transaction.getEventName();
        String[] split = eventNameYear.split("-");
        transaction.setEventName(split[0]);
        transaction.setEventYear(Integer.parseInt(split[1]));

        //validate eventname x year
        Event eventLookup = mainDBService.lookupEventByNameAndYear(transaction.getEventName(), transaction.getEventYear());
        if (eventLookup == null) {
            isValid = false;
            reasonForFailure += "Event not found";
        }

        // validate that the streaming service is actually offering the event
        Transaction offerLookup = null;
        offerLookup = mainDBService.checkToSeeIfStreamingIsOfferingEvent(transaction.getVendor(), transaction.getEventName(), transaction.getEventYear());
        if (offerLookup == null) {
            isValid = false;
            reasonForFailure += "Offering for streaming service and movie not found. Please make sure they are created and Valid for the month.";
        }

        Transaction saved = null;
        if (isValid) {
            System.out.println("Passed all validation steps, proceeding with logic based on ppv or movie type");
            // Get the studio from the event and then commit the transaction

            String eventType = offerLookup.getEventType();

            if (eventType.equalsIgnoreCase("ppv")) {
                System.out.println("proceeding with ppv transaction");

                int sizeOfDemogroup = demographicGroupLookup.getNumberOfAccounts();
                int ppvCost = offerLookup.getPpvCost();
                int totalCostOfTransaction = sizeOfDemogroup * ppvCost * transaction.getPercentage();
                int totalCostOfTransactionDiv100 = totalCostOfTransaction / 100;
                transaction.setTransactionCost(totalCostOfTransactionDiv100);

            } else if (eventType.equalsIgnoreCase("movie")) {

                //check to see if theres a subscription already
                Transaction existingSubscriptionWithGreatestPercentage = mainDBService.returnSubscriptionBetweenStreamAndDemoWithLargestPercentage(streamingServiceLookup.getShortName(), demographicGroupLookup.getShortName());

                int percentageForTransaction = transaction.getPercentage();

                if (existingSubscriptionWithGreatestPercentage != null) {
                    percentageForTransaction -= existingSubscriptionWithGreatestPercentage.getPercentage();
                }

                if (percentageForTransaction < 0) {
                    percentageForTransaction = 0;
                }

                int transactionCost = percentageForTransaction * streamingServiceLookup.getSubscriptionPrice() * demographicGroupLookup.getNumberOfAccounts();
                int transactionCostDiv100 = transactionCost / 100;
                transaction.setTransactionCost(transactionCostDiv100);
            }

            // this is an "offer" type
            transaction.setTransactionType("watch");

            // set the transactionEventType
            transaction.setEventType(eventType);
            saved = mainDBService.saveTransaction(transaction);
            //saved = transactionRepository.save(transaction);

//            // this is an "offer" type
//            transaction.setTransactionType("watch");
//            saved = transactionRepository.save(transaction);
        }

        if (saved != null) {
            model.addAttribute("successmessage", "Watch Event Saved Successfully!");
            return "index.xhtml";
        } else {
            model.addAttribute("errormessage", String.format("Watch Event Creation Failed for the following reasons: %s, Please try again", reasonForFailure));
            model.addAttribute("transaction", transaction);
            return "index.xhtml";
        }
    }

//    @RequestMapping("/offerppv")
//    public  String createPpvOffer(Model model)
//    {
//        clearModelAttributes(model);
//        Transaction transaction = new Transaction();
//        List<StreamingService> allServices = mainDBService.findAllServices();
//        List<Event> allEvents = mainDBService.findAllEvents();
//        model.addAttribute("services",allServices);
//        model.addAttribute("events",allEvents);
//        model.addAttribute("transaction",transaction);
//        return "offerppv.xhtml";
//    }
//
//    @PostMapping("/offerppv")
//    public String createPpvOfferSubmit(@ModelAttribute Transaction transaction, Model model) {
//
//        // Most important thing for this is that we need to be validate that the ppv itself exists before
//        // committing the transaction
//
//        Boolean isValid = true;
//        String reasonForFailure = "";
//        Event event = lookupEventByNameAndYear(transaction.getEventName(), transaction.getEventYear());
//
//        if (event == null){
//            isValid = false;
//            reasonForFailure += "Event not found";
//        } else if (event != null) {
//            String eventType = event.getEventType();
//            if (!(eventType.equalsIgnoreCase("ppv"))) {
//                isValid = false;
//                reasonForFailure += "Event type is not ppv";
//            }
//        }
//
//        Transaction saved = null;
//        if (isValid) {
//            System.out.println("Event passed validation steps");
//            // Get the studio from the event and then commit the transaction
//            String studioShortName = event.getStudioShortName();
//
//            transaction.setVendor(studioShortName);
//            transaction.setTransactionCost(event.getEventLicensingFee());
//            transaction.setEventType("ppv");
//
//            //transaction ppv cost is passed in via the offerppv.xhtml post
//
//            // this is an "offer" type
//            transaction.setTransactionType("offer");
//
//            saved = transactionRepository.save(transaction);
//        }
//
//        if(saved!=null) {
//            model.addAttribute("successmessage", "Event Saved Successfully!");
//            return "index.xhtml";
//        }
//        else {
//            model.addAttribute("errormessage", String.format("Offering save Failed for the following reasons: %s, Please try again", reasonForFailure));
//            model.addAttribute("transaction",transaction);
//            return "offerppv.xhtml";
//        }
//    }

    @RequestMapping("/offermovie")
    public String createMovieOffer(Model model) {
        clearModelAttributes(model);
        Transaction transaction = new Transaction();
        List<StreamingService> allServices = mainDBService.findAllServices();
        List<Event> allEvents = mainDBService.findAllEvents();
        model.addAttribute("services", allServices);
        model.addAttribute("events", allEvents);
        model.addAttribute("transaction", transaction);
        return "offermovie.xhtml";
    }

    @PostMapping("/offermovie")
    public String createMovieOfferSubmit(@ModelAttribute Transaction transaction, Model model) {

        Boolean isValid = true;
        String reasonForFailure = "";

        String eventNameYear = transaction.getEventName();
        String[] split = eventNameYear.split("-");
        transaction.setEventName(split[0]);
        transaction.setEventYear(Integer.parseInt(split[1]));

        List<EventOffer> eventOffers = mainDBService.lookupEventStreamBasedOnCurrentMonth(transaction.getEventName(),transaction.getEventYear(), transaction.getBuyer(), transaction.getCurrentMonthYear());
        if(eventOffers.size()>0)
        {
            model.addAttribute("errormessage", "Event Offering exists for the Streaming Service for the Selected Month. Please choose another Streaming Service");
            return "index.xhtml";
        }

        Event event = mainDBService.lookupEventByNameAndYear(transaction.getEventName(), transaction.getEventYear());
        StreamingService streamingService = mainDBService.findStreamingServiceByName(transaction.getBuyer());

        EventOffer eventOffer = new EventOffer();
        eventOffer.setEvent(event);
        eventOffer.setService(streamingService);
        eventOffer.setRetracted(false);
        eventOffer.setCurrentMonthYear(transaction.getCurrentMonthYear());

        Transaction saved = null;
        if (isValid) {
            System.out.println("Event passed validation steps");
            // Get the studio from the event and then commit the transaction
            String studioShortName = event.getStudioShortName();

            transaction.setVendor(studioShortName);
            transaction.setEventType(event.getEventType());
            transaction.setEventDuration(event.getDuration());
            if (event.getEventType().equals("MOVIE")) {
                transaction.setTransactionCost(event.getEventLicensingFee());
            } else {
                transaction.setTransactionCost(event.getEventLicensingFee());
                //transaction.setPpvCost(event.getEventLicensingFee());
            }
            // this is an "offer" type
            transaction.setTransactionType("offer");

            try {
                mainDBService.saveEventOffer(eventOffer);
            } catch (DataIntegrityViolationException | SQLIntegrityConstraintViolationException | ConstraintViolationException exception) {
                exception.printStackTrace();
                reasonForFailure = "Entity Offering Exists for Service and Event for the Month";
            }
            saved = mainDBService.saveTransaction(transaction);
        }

        if (saved != null) {
            model.addAttribute("successmessage", "Event Saved Successfully!");
            return "index.xhtml";
        } else {
            model.addAttribute("errormessage", String.format("Offering save Failed for the following reasons: %s, Please try again", reasonForFailure));
            model.addAttribute("transaction", transaction);
            return "index.xhtml";
        }
    }


    @RequestMapping("/displaystudio")
    public String displayStudio(Model model,@RequestParam(required = false) String Status,@RequestParam(required = false) String startDate) {
        clearModelAttributes(model);
        //Studio studio = new Studio();
        //model.addAttribute("studio", studio);
        List<Studio> allStudios = mainDBService.findAllStudios();
        List<StreamTransactionSummary> transactionSummaries = new ArrayList<StreamTransactionSummary>();
        LocalTime time = LocalTime.of(00, 00);
        LocalDate date = LocalDate.now().minusDays(LocalDate.now().getDayOfMonth() - 1);
        LocalDateTime startDate1 = date.atTime(time);
        String currentMonthYear = startDate1.getMonth().getValue()+"-"+startDate1.getYear();
        for(Studio studio:allStudios)
        {
            TransactionSummary transactionSummaryCalculated = mainDBService.calculateTransactionSummaryForStudio(studio, currentMonthYear);
            StreamTransactionSummary summary = new StreamTransactionSummary();
            summary.setId(studio.getId());
            summary.setShortName(studio.getShortName());
            summary.setLongName(studio.getLongName());
            summary.setCurrentPeriod(transactionSummaryCalculated.getCurrentPeriod());
            summary.setPreviousPeriod(transactionSummaryCalculated.getPreviousPeriod());
            summary.setTotal(transactionSummaryCalculated.getTotal());
            transactionSummaries.add(summary);
        }

        TransactionSummary transactionSummary = new TransactionSummary();
        model.addAttribute("transactionSummary", transactionSummary);
        FormData data = new FormData();
        model.addAttribute("editObject", data);
        if(transactionSummaries.size()==0)
        {
            model.addAttribute("nodata", true);
        }
        else {
            data.setStartDate(startDate1.getMonth().getValue() + "-" + startDate1.getYear());
            model.addAttribute("transactionSummaries", transactionSummaries);
        }

        if (Status != null && Status.equals("SUCCESS")) {
            model.addAttribute("successmessage", "Studio Service Update Successful!");
        } else if (Status != null && Status.equals("ERROR")) {
            model.addAttribute("errormessage", "Studio Service Update Failed!,Please try again!");
        }
        return "displaystudio.xhtml";
    }

    @PostMapping("/displaystudio")
    public String displayStudio(@ModelAttribute TransactionSummary transactionSummary, Model model,@ModelAttribute FormData data) {
        List<StreamTransactionSummary> transactionSummaries = new ArrayList<StreamTransactionSummary>();
        List<Studio> allStudios = mainDBService.findAllStudios();
        String currentMonthYear = data.getStartDate();
        if (allStudios != null) {
            for(Studio studio:allStudios) {
                TransactionSummary transactionSummaryCalculated = mainDBService.calculateTransactionSummaryForStudio(studio, currentMonthYear);
                StreamTransactionSummary summary = new StreamTransactionSummary();
                summary.setId(studio.getId());
                summary.setShortName(studio.getShortName());
                summary.setLongName(studio.getLongName());
                summary.setCurrentPeriod(transactionSummaryCalculated.getCurrentPeriod());
                summary.setPreviousPeriod(transactionSummaryCalculated.getPreviousPeriod());
                summary.setTotal(transactionSummaryCalculated.getTotal());
                transactionSummaries.add(summary);
            }
            model.addAttribute("transactionSummaries", transactionSummaries);
        } else {
            model.addAttribute("nodata", true);
        }
        model.addAttribute("transactionSummary", transactionSummary);
        model.addAttribute("editObject", data);
        return "displaystudio.xhtml";
    }

    @RequestMapping("/displaystream")
    public String displayStream(Model model,@RequestParam(required = false) String Status, @RequestParam(required = false) String startDate) {
        clearModelAttributes(model);
        StreamingService stream = new StreamingService();
        model.addAttribute("stream", stream);
        LocalDateTime startDate1 = null;
        if (startDate != null && startDate.length() > 0) {
            startDate1 = getCreateDate(startDate);
        }
        else {
            LocalTime time = LocalTime.of(00, 00);
            LocalDate date = LocalDate.now().minusDays(LocalDate.now().getDayOfMonth() - 1);
             startDate1 = date.atTime(time);
        }
        String currentMonthYear = startDate1.getMonth().getValue() + "-" + startDate1.getYear();
        List<StreamingService> streamingServices = mainDBService.findAllServices();
        List<StreamTransactionSummary> transactionSummaries = new ArrayList<StreamTransactionSummary>();
        for(StreamingService service:streamingServices)
        {
            TransactionSummary transactionSummaryCalculated = mainDBService.calculateTransactionSummaryForStream(service,currentMonthYear);
            StreamTransactionSummary summary = new StreamTransactionSummary();
            summary.setId(service.getId());
            summary.setShortName(service.getShortName());
            summary.setLongName(service.getLongName());
            summary.setCurrentPeriod(transactionSummaryCalculated.getCurrentPeriod());
            summary.setPreviousPeriod(transactionSummaryCalculated.getPreviousPeriod());
            summary.setSubscriptionPrice(service.getSubscriptionPrice());
            summary.setLicensing(transactionSummaryCalculated.getLicensing());
            summary.setTotal(transactionSummaryCalculated.getTotal());
            Transaction transaction = mainDBService.checkToSeeIfStreamHasBeenWatchedInTheGivenMonth(service.getShortName(), currentMonthYear);
            if(transaction!=null)
            {
                summary.setEditable(false);
            }
            else
            {
                summary.setEditable(true);
            }
            transactionSummaries.add(summary);
        }
        StreamTransactionSummary transactionSummary = new StreamTransactionSummary();
        FormData data = new FormData();
        data.setStartDate(startDate1.getMonth().getValue() + "-" + startDate1.getYear());
        if(transactionSummaries.size()!=0) {
            model.addAttribute("transactionSummaries", transactionSummaries);
            model.addAttribute("transactionSummary", transactionSummary);
            model.addAttribute("editObject", data);
        }
        else
        {
            model.addAttribute("nodata", true);
        }


        if (Status != null && Status.equals("SUCCESS")) {
            model.addAttribute("successmessage", "Streaming Service Update Successful!");
        } else if (Status != null && Status.equals("ERROR")) {
            model.addAttribute("errormessage", "Streaming Service Update Failed!,Please try again!");
        }

        return "displaystream.xhtml";
    }

    @PostMapping("/displaystream")
    public String displayStream(@ModelAttribute StreamingService stream, @ModelAttribute TransactionSummary transactionSummary, Model model, @ModelAttribute FormData data) {
        // Lookup Stream
        String currentMonthYear = data.getStartDate();
        List<StreamingService> streamingServices = mainDBService.findAllServices();
        List<StreamTransactionSummary> transactionSummaries = new ArrayList<StreamTransactionSummary>();
        for(StreamingService service:streamingServices)
        {
            TransactionSummary transactionSummaryCalculated = mainDBService.calculateTransactionSummaryForStream(service,currentMonthYear);
            StreamTransactionSummary summary = new StreamTransactionSummary();
            summary.setId(service.getId());
            summary.setShortName(service.getShortName());
            summary.setLongName(service.getLongName());
            summary.setCurrentPeriod(transactionSummaryCalculated.getCurrentPeriod());
            summary.setPreviousPeriod(transactionSummaryCalculated.getPreviousPeriod());
            summary.setSubscriptionPrice(service.getSubscriptionPrice());
            summary.setLicensing(transactionSummaryCalculated.getLicensing());
            summary.setTotal(transactionSummaryCalculated.getTotal());
            Transaction transaction = mainDBService.checkToSeeIfStreamHasBeenWatchedInTheGivenMonth(service.getShortName(), currentMonthYear);
            if(transaction!=null)
            {
                summary.setEditable(false);
            }
            else
            {
                summary.setEditable(true);
            }
            transactionSummaries.add(summary);
        }
        if(transactionSummaries.size()!=0) {
            model.addAttribute("transactionSummaries", transactionSummaries);
            model.addAttribute("transactionSummary", transactionSummary);
            model.addAttribute("editObject", data);
        }
        else
        {
            model.addAttribute("nodata", true);
        }
        return "displaystream.xhtml";
    }

    @RequestMapping("/displaydemo")
    public String displayDemo(Model model, @RequestParam(required = false) String Status, @RequestParam(required = false) String startDate) {
        clearModelAttributes(model);
        List<DemographicGroup> allDemos = null;
        LocalDateTime startDate1 = null;
        LocalDateTime endDate1 = null;
        if (startDate != null && startDate.length() > 0) {
            startDate1 = getCreateDate(startDate);
            endDate1 = startDate1.plusMonths(1);
            allDemos = mainDBService.findAllDemographicGroup();
        } else {
            LocalTime time = LocalTime.of(00, 00);
            LocalDate date = LocalDate.now().minusDays(LocalDate.now().getDayOfMonth() - 1);
            startDate1 = date.atTime(time);
            endDate1 = date.atTime(time).plusMonths(1);
            allDemos = mainDBService.findAllDemographicGroup();
        }
        List<TransactionSummary> transactionSummaries = new ArrayList<TransactionSummary>();
        for (DemographicGroup group : allDemos) {
            LocalTime time = LocalTime.of(00, 00);
            LocalDate date = LocalDate.now().minusDays(LocalDate.now().getDayOfMonth() - 1);
            boolean demographicEditable = mainDBService.isDemographicEditable(group.getShortName(), date.atTime(time).getMonth().getValue() + "-" + date.atTime(time).getYear());
            TransactionSummary transactionSummary = mainDBService.calculateTransactionSummaryForDemo(group, group.getCurrentMonthYear());
            transactionSummaries.add(transactionSummary);
            group.setEditable(demographicEditable);
        }
        FormData data = new FormData();
        data.setStartDate(startDate1.getMonth().getValue() + "-" + startDate1.getYear());
        data.setEndDate(endDate1.getMonth().getValue() + "-" + endDate1.getYear());
        model.addAttribute("editObject", data);
        if (allDemos != null && allDemos.size() > 0) {
            model.addAttribute("demos", allDemos);
            model.addAttribute("transactionSummaries",transactionSummaries);
        } else {
            model.addAttribute("nodata", true);
        }

        if (Status != null && Status.equals("SUCCESS")) {
            model.addAttribute("successmessage", "Demographic Update Successful!");
        } else if (Status != null && Status.equals("ERROR")) {
            model.addAttribute("errormessage", "Demographic Update Failed for the Demographic Group,Please try again!");
        }

        return "displaydemo.xhtml";
    }

    @PostMapping("/displaydemo")
    public String displayDemo(Model model, @ModelAttribute FormData data) {
        clearModelAttributes(model);
        LocalDateTime startDateLDT = getCreateDate(data.getStartDate());
        LocalDateTime endDateLDT = startDateLDT.plusMonths(1);
        List<DemographicGroup> allDemos = mainDBService.findAllDemographicGroup();
        List<TransactionSummary> transactionSummaries = new ArrayList<TransactionSummary>();
        String currentMonthYear = startDateLDT.getMonth().getValue() + "-" + startDateLDT.getYear();
        if (allDemos != null && allDemos.size() > 0) {
            for (DemographicGroup group : allDemos) {
                boolean demographicEditable = mainDBService.isDemographicEditable(group.getShortName(), currentMonthYear);
                TransactionSummary transactionSummary = mainDBService.calculateTransactionSummaryForDemo(group,currentMonthYear);
                transactionSummaries.add(transactionSummary);
                group.setEditable(demographicEditable);
            }
            model.addAttribute("demos", allDemos);
            model.addAttribute("transactionSummaries",transactionSummaries);
        } else {
            model.addAttribute("nodata", true);
        }
        model.addAttribute("editObject", data);
        return "displaydemo.xhtml";
    }

    @RequestMapping("/updatedemo")
    public String updateDemo(Model model) {
        clearModelAttributes(model);
        DemographicGroup demo = new DemographicGroup();
        model.addAttribute("demo", demo);
        return "updatedemo.xhtml";
    }

    @PostMapping("/updatedemo")
    public String updateDemo(@ModelAttribute DemographicGroup group, Model model) {

        //lookup demogroup
        DemographicGroup demographicGroupLookup = null;
        demographicGroupLookup = mainDBService.lookupDemographicGroupByShortName(group.getShortName());

        if (demographicGroupLookup != null) {
            clearModelAttributes(model);

            // ONLY IF we've found the demoGroup (which we have since we're in this if block) update the number of accounts on the demogroup
            demographicGroupLookup.setNumberOfAccounts(group.getNumberOfAccounts());
            mainDBService.getDemographicRepository().save(demographicGroupLookup);

            model.addAttribute("demo", demographicGroupLookup);
            model.addAttribute("successmessage", "Demogroup number of accounts update succeeded");
            System.out.println("Successfully updated the number of accounts");
            return "updatedemo.xhtml";
        } else {
            clearModelAttributes(model);
            DemographicGroup newDemoGroup = new DemographicGroup();
            model.addAttribute("demo", newDemoGroup);
            model.addAttribute("errormessage", "Demogroup lookup Failed, Please try again");
            System.out.println("Couldn't find the demogroup to update the number of accounts");
            return "updatedemo.xhtml";
        }
    }

    @RequestMapping("/updatestream")
    public String updateStream(Model model) {
        clearModelAttributes(model);
        StreamingService streamingService = new StreamingService();
        model.addAttribute("streamingservice", streamingService);
        return "updatestream.xhtml";
    }

    @PostMapping("/updatestream")
    public String updateEvent(@ModelAttribute StreamingService streamingService, Model model) {


        // Most important thing for this is that we need to be validate that the movie itself exists before
        // committing the transaction

        Boolean isValid = true;
        String reasonForFailure = "";

        StreamingService streamLookup = null;
        streamLookup = mainDBService.lookupStreamByShortName(streamingService.getShortName());

        if (streamLookup == null) {
            isValid = false;
            reasonForFailure += "Streaming Service not found";
        } else if (streamLookup != null) {

            String currentYearMonth = streamLookup.getCurrentMonthYear();
            // if the event has already been viewed in that month, do not proceed with the transaction
            Transaction subscribedToThatMonth = mainDBService.checkToSeeIfStreamHasBeenWatchedInTheGivenMonth(streamingService.getShortName(), streamLookup.getCurrentMonthYear());

            if (subscribedToThatMonth != null) {
                isValid = false;
                reasonForFailure += "Stream has already been subscribed to in the given month";
            }
        }

        StreamingService saved = null;
        if (isValid) {
            System.out.println("Streaming Service passed validation steps");
            // Get the studio from the event and then commit the transaction
            clearModelAttributes(model);

            streamLookup.setSubscriptionPrice(streamingService.getSubscriptionPrice());
            try {
                saved = mainDBService.saveStreamingService(streamLookup);
            } catch (SQLIntegrityConstraintViolationException exception) {
                exception.printStackTrace();
            }
            //saved = streamingServiceRepository.save(streamLookup);
        }

        if (saved != null) {
            model.addAttribute("streamingservice", streamLookup);
            model.addAttribute("successmessage", "Event Saved Successfully!");
            return "index.xhtml";
        } else {
            model.addAttribute("errormessage", String.format("Streaming Service Update Failed for the following reasons: %s, Please try again", reasonForFailure));
            model.addAttribute("streamingservice", streamingService);
            return "updatestream.xhtml";
        }
    }

    @RequestMapping("/updateevent")
    public String updateEvent(Model model) {
        clearModelAttributes(model);
        Event event = new Event();
        model.addAttribute("event", event);
        return "updateevent.xhtml";
    }

    @PostMapping("/updateevent")
    public String updateEvent(@ModelAttribute Event event, Model model) {


        // Most important thing for this is that we need to be validate that the movie itself exists before
        // committing the transaction

        Boolean isValid = true;
        String reasonForFailure = "";

        Event eventLookup = null;
        eventLookup = mainDBService.lookupEventByNameAndYear(event.getName(), event.getYear());

        if (eventLookup == null) {
            isValid = false;
            reasonForFailure += "Event not found";
        } else if (event != null) {


            String currentYearMonth = eventLookup.getCurrentMonthYear();
            // if the event has already been viewed in that month, do not proceed with the transaction
            Transaction watchedInThatMonth = mainDBService.checkToSeeIfEventHasBeenWatchedInTheGivenMonth(event.getName(), event.getYear(), eventLookup.getCurrentMonthYear());

            if (watchedInThatMonth != null) {
                isValid = false;
                reasonForFailure += "Event has already been watched in the given month";
            }
        }

        Event saved = null;
        if (isValid) {
            System.out.println("Event and dateCheck passed validation steps");
            // Get the studio from the event and then commit the transaction
            clearModelAttributes(model);

            eventLookup.setDuration(event.getDuration());
            eventLookup.setEventLicensingFee(event.getEventLicensingFee());

            saved = mainDBService.saveEvent(eventLookup);
        }

        if (saved != null) {
            model.addAttribute("event", eventLookup);
            model.addAttribute("successmessage", "Event Saved Successfully!");
            return "index.xhtml";
        } else {
            model.addAttribute("errormessage", String.format("Event save Failed for the following reasons: %s, Please try again", reasonForFailure));
            model.addAttribute("event", event);
            return "updateevent.xhtml";
        }
    }

    @RequestMapping("/displayevents")
    public String displayEvents(Model model,@RequestParam(required = false) String Status) {
        clearModelAttributes(model);
        LocalTime time = LocalTime.of(00, 00);
        LocalDate date = LocalDate.now().minusDays(LocalDate.now().getDayOfMonth() - 1);
        LocalDateTime startDate1 = date.atTime(time);
        String currentMonthYear = startDate1.getMonth().getValue()+"-"+startDate1.getYear();
        List<Event> listOfEvents = mainDBService.getAllEvents();
        for(Event event:listOfEvents)
        {
            Transaction transaction = mainDBService.checkToSeeIfEventHasBeenWatchedInTheGivenMonth(event.getName(), event.getYear(),currentMonthYear);
            if(transaction==null)
            {
                event.setEditable(true);
            }
            else
            {
                event.setEditable(false);
            }
        }

        Event editObject = new Event();
        FormData data = new FormData();

       if(listOfEvents.size()==0)
        {
            model.addAttribute("nodata", true);
        }
        else {
           data.setStartDate(startDate1.getMonth().getValue() + "-" + startDate1.getYear());
           model.addAttribute("editObject", data);
           model.addAttribute("events", listOfEvents);
        }

        if (Status != null && Status.equals("SUCCESS")) {
            model.addAttribute("successmessage", "Event Update Successful!");
        } else if (Status != null && Status.equals("ERROR")) {
            model.addAttribute("errormessage", "Event Update Failed!,Please try again!");
        }
        return "displayevents.xhtml";
    }

    @RequestMapping("/displayoffers")
    public String displayOffers(Model model,@ModelAttribute FormData data) {
        clearModelAttributes(model);
        List<Transaction> listOfOffers = mainDBService.getAllOffers();
        if(listOfOffers.size()==0)
        {
            model.addAttribute("nodata", true);
        }
        else {
            model.addAttribute("transactions", listOfOffers);
        }
        return "displayoffers.xhtml";
    }

    @RequestMapping("/retractmovie")
    public String retractMovieOffer(Model model) {
        clearModelAttributes(model);
        Transaction transaction = new Transaction();
        model.addAttribute("transaction", transaction);
        List<EventOffer> allEventOffers = mainDBService.findAllEventOffers();
        FormData data = new FormData();
        for (EventOffer eventOffer : allEventOffers) {
            EventOfferData eventData = new EventOfferData(eventOffer.getId(),eventOffer.getEvent().getId(),eventOffer.getEvent().getName(),eventOffer.getService().getId(),eventOffer.getService().getShortName(),eventOffer.getCreatedAt(),eventOffer.isRetracted());
            data.getEventOffers().add(eventData);
        }
        model.addAttribute("eventdata", data);
        return "retractmovie.xhtml";
    }

    @PostMapping("/retractmovie")
    public String retractMovieOfferSubmit(@ModelAttribute FormData data, Model model) {
        System.out.println(data);
        List<EventOffer> allEventOffers = mainDBService.findAllEventOffers();
        for(EventOfferData eventOfferData:data.getEventOffers())
        {
            for(EventOffer eventOffer : allEventOffers)
            {
                if(eventOffer.getId()==eventOfferData.getId())
                {
                    eventOffer.setRetracted(eventOfferData.isRetracted());
                }
            }
        }
        List<EventOffer> eventOffers = mainDBService.saveAllEventOfferData(allEventOffers);
        if(eventOffers.size()>0)
        {
            model.addAttribute("successmessage", "All Movie Retractions Saved Successfully!");
            FormData formData = new FormData();
            formData.setEventOffers(data.getEventOffers());
            model.addAttribute("eventdata",formData );
            return "retractmovie.xhtml";
        }
        else
        {
            model.addAttribute("successmessage", "Movie Retractions Save Failed!. Please Try Again");
            return "index.xhtml";
        }
    }

    private void clearModelAttributes(Model model)
    {
        model.addAttribute("successmessage",null);
        model.addAttribute("errormessage",null);
    }

    private LocalDateTime getCreateDate(String currentMonthYear)
    {
        String[] split = currentMonthYear.split("-");
        LocalTime time = LocalTime.of(00, 00);
        LocalDate date = LocalDate.of(Integer.parseInt(split[1]), Month.of(Integer.parseInt(split[0])), 01);
        LocalDateTime combined = date.atTime(time);
        return combined;
    }
}
